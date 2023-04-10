package com.microservice.address.repositories;

import com.microservice.address.models.AddressEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<AddressEntity,Long> {
    @Aggregation(pipeline = {
            "{ '$sort': { '_id':-1} }",
            "{ '$limit': 1 } "
    })
    AddressEntity getLastAddress();
}
