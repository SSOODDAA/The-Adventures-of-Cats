package com.example.caveadventure.dao;

import com.example.caveadventure.entity.PlayerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerReposity extends MongoRepository<PlayerEntity,Integer> {

}
