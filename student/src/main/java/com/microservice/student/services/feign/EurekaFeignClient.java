package com.microservice.student.services.feign;

import com.microservice.student.models.Address;
import com.microservice.student.models.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient( value = "gateway-service", path = "address-service/address/address")
public interface EurekaFeignClient {
    @PostMapping
    public ResponseEntity<AddressResponse> addAddress(@RequestBody Address address) ;

    @GetMapping("{id}")
    public ResponseEntity<AddressResponse> getAddress(@PathVariable long id);
}
