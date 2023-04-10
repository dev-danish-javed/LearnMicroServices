package com.microservice.address.services;

import com.microservice.address.models.AddressDto;
import com.microservice.address.models.AddressEntity;
import com.microservice.address.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public AddressDto addAddress(AddressDto addressDto) {
        AddressEntity lastAddress = addressRepository.getLastAddress();
        long newAddressId = lastAddress != null ? lastAddress.getId() + 1 : 0;
        addressDto.setId(newAddressId);
        return new AddressDto(addressRepository.save(new AddressEntity(addressDto)));
    }
    public AddressDto getAddressById(long id) {
        Optional<AddressEntity> entity = addressRepository.findById(id);
        return entity.isPresent() ? new AddressDto(entity.get()) : null;
    }
}
