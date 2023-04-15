package com.microservice.student.services;

import com.google.gson.Gson;
import com.microservice.student.models.AddressResponse;
import com.microservice.student.models.StudentDto;
import com.microservice.student.models.StudentEntity;
import com.microservice.student.repositories.StudentRepository;
import com.microservice.student.services.feign.AddressServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class StudentService {

    @Autowired
    AddressServiceHelper addressServiceHelper;

    @Autowired
    StudentRepository repository;

    @Value("${address.service.url}")
    String studentServiceUrl;

    public StudentDto addStudent(StudentDto studentDto) {
        try {
            // Generating new student id, from
            StudentEntity lastStudent = repository.getLastStudent();
            long newStudentId = lastStudent != null ? lastStudent.getId() + 1 : 0;

            StudentEntity studentEntity = new StudentEntity(studentDto);
            //setting student id in address
            studentDto.getAddress().setStudentId(newStudentId);

            Gson gson = new Gson();
            String requestData = gson.toJson(studentDto.getAddress());

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(studentServiceUrl + "/address"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestData))
                    .header("Content-Type", "application/json").build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            AddressResponse address = gson.fromJson(response.body(), AddressResponse.class);
            //setting address id in student entity
            studentEntity.setAddressId(address.getId());
            studentEntity.setId(newStudentId);
            studentEntity = repository.save(studentEntity);
            StudentDto savedStudent = new StudentDto(studentEntity);
            //setting address in saved student
            savedStudent.setAddressResponse(address);
            return savedStudent;
        } catch (URISyntaxException | IOException | InterruptedException ignored) {
        }
        // in case any exception occurred
        return null;
    }

    public StudentDto getStudent(long id) {
        return addressServiceHelper.getStudentFromStudentService(id);
    }


}
