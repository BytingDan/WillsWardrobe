package com.willswardrobe.WillsWardrobe.wardrobe;

import org.springframework.data.mongodb.repository.MongoRepository;

//@Repository
public interface WardrobeRepository extends MongoRepository<Wardrobe, String> {
}
