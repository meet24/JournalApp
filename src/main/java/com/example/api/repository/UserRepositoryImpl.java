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
        Criteria criteria = new Criteria();
        query.addCriteria(criteria.andOperator(
            Criteria.where("email").exists(true), 
            Criteria.where("sentimentAnalysis").exists(true)));
        return mongoTemplate.find(query, UserEntry.class);
    }
}
