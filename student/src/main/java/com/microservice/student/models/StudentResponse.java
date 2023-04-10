package com.microservice.student.models;

public class StudentResponse {
    private long id;
    private String name;
    private String course;
    private AddressResponse address;

    public StudentResponse() {
    }

    public StudentResponse(StudentDto studentDto) {
        this.id = studentDto.getId();
        this.name = studentDto.getName();
        this.course = studentDto.getCourse();
        this.address = studentDto.getAddressResponse();
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

    public AddressResponse getAddress() {
        return address;
    }

    public void setAddress(AddressResponse address) {
        this.address = address;
    }
}
