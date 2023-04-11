package com.microservice.address.controllers;

import com.microservice.address.models.AddressDto;
import com.microservice.address.models.AddressRequest;
import com.microservice.address.models.AddressResponse;
import com.microservice.address.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressService addressService;

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
}
