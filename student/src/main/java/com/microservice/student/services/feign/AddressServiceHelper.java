package com.microservice.student.services.feign;

import com.microservice.student.models.StudentDto;
import com.microservice.student.models.StudentEntity;
import com.microservice.student.repositories.StudentRepository;
import com.microservice.student.services.StudentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceHelper {
    @Autowired
    EurekaFeignClient eurekaFeignClient;

    @Autowired
    StudentRepository repository;

    private static int count;
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    @CircuitBreaker(name = "addressService", fallbackMethod = "getDummyStudent")
    public StudentDto getStudentFromStudentService(long id) {
        Optional<StudentEntity> entity = repository.findById(id);
        count++;
        logger.info("COUNT : " + count);

        if((count >=5 && count <=13) || (count >=19) && count <=21)
        {
            logger.warn("Throwing Exception");
            throw new RuntimeException();
        }
        StudentDto studentDto = null;
        if (entity.isPresent()) {
            studentDto = new StudentDto(entity.get());
            studentDto.setAddressResponse(eurekaFeignClient.getAddress(studentDto.getAddressId()).getBody());
        }
        return studentDto;
    }

    public StudentDto getDummyStudent(long id, Throwable th) {
        logger.error("ERROR : " + th.getMessage());
        return new StudentDto();
    }
}
