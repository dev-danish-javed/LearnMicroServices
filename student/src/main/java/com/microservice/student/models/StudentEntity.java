package com.microservice.student.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class StudentEntity {
    @Id
    private long id;
    private String name;
    private String course;
    private long addressId;

    public StudentEntity() {
    }

    public StudentEntity(StudentDto studentDto) {
        if(studentDto!=null){
            this.id = studentDto.getId();
            this.name = studentDto.getName();
            this.course = studentDto.getCourse();
            this.addressId = studentDto.getAddress().getId();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }
}
