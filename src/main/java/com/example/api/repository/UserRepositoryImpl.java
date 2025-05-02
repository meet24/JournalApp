package com.example.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.example.api.entity.UserEntry;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserEntry> getUserForSA() {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-z|A-Z|0-9-.]+$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        return mongoTemplate.find(query, UserEntry.class);
    }
}
