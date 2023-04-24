package com.microservice.address.controllers;

import com.microservice.address.models.AddressDto;
import com.microservice.address.models.AddressRequest;
import com.microservice.address.models.AddressResponse;
import com.microservice.address.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @Value("${application.profile}")
    private String profile;

    @Value("${dev.specific}")
    private String devSpecific;

    @Value("${prod.specific}")
    private String prodSpecific;

    @PostMapping
    public ResponseEntity<AddressResponse> addAddress(@RequestBody AddressRequest address) {
        AddressDto entity = new AddressDto(address);
        entity = addressService.addAddress(entity);
        AddressResponse responseBody = new AddressResponse(entity);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AddressResponse> getAddress(@PathVariable long id) {
        System.out.println("I am address-service");
        AddressDto addressDto = addressService.getAddressById(id);
        return addressDto != null ? new ResponseEntity<>(new AddressResponse(addressDto), HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/profile")
    public String getProfile() {
        return "profile : " + profile + "\n" +
                "devSpecific : " + devSpecific + "\n" +
                "prodSpecific : " + prodSpecific + "\n";
    }
}
