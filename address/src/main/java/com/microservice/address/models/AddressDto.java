package com.microservice.address.models;

public class AddressDto {
    private long id;
    private String city;
    private String country;
    private long studentId;

    public AddressDto() {
    }

    public AddressDto(AddressRequest request) {
        if (request != null) {
            this.city = request.getCity();
            this.country = request.getCountry();
            this.studentId = request.getStudentId();
        }
    }

    public AddressDto(AddressEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.city = entity.getCity();
            this.country = entity.getCountry();
            this.studentId = entity.getStudentId();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}
