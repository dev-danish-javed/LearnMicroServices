package com.microservice.student.models;

public class StudentDto {
    private long id;
    private String name;
    private String course;
    private Address address;
    private AddressResponse addressResponse;
    private long addressId;

    public StudentDto() {
    }

    public StudentDto(StudentRequest studentRequest) {
        if (studentRequest != null) {
            this.name = studentRequest.getName();
            this.course = studentRequest.getCourse();
            this.address = studentRequest.getAddress();
        }
    }

    public StudentDto(StudentEntity studentEntity) {
        if (studentEntity != null) {
            this.id = studentEntity.getId();
            this.name = studentEntity.getName();
            this.course = studentEntity.getCourse();
            this.addressId = studentEntity.getAddressId();
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public AddressResponse getAddressResponse() {
        return addressResponse;
    }

    public void setAddressResponse(AddressResponse addressResponse) {
        this.addressResponse = addressResponse;
    }
}
