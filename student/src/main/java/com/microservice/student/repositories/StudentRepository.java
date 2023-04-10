package com.microservice.student.repositories;

import com.microservice.student.models.StudentEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<StudentEntity, Long> {
    @Aggregation(pipeline = {
            "{ '$sort': { '_id':-1} }",
            "{ '$limit': 1 } "
    })
    StudentEntity getLastStudent();
}
