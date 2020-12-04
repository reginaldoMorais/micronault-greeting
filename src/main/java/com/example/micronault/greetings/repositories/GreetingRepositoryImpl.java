package com.example.micronault.greetings.repositories;

import com.example.micronault.greetings.models.Greeting;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class GreetingRepositoryImpl implements GreetingRepository {

    @Inject
    private MongoClient mongoClient;

    @Override
    public List<Greeting> findAll() {
        List<Greeting> greetings = getCollection().find().into(new ArrayList<>());
        return greetings;
    }

    @Override
    public Optional<Greeting> findbyId(final String id) {
        Bson filter = Filters.eq("_id", id);
        return Optional.ofNullable(getCollection().find(filter).first());
    }

    @Override
    public Greeting save(Greeting greeting) {
        getCollection().insertOne(greeting);
        return greeting;
    }

    public void delete(final String id) {
        Bson filter = Filters.eq("_id", id);
        getCollection().deleteOne(filter);
    }

    private MongoCollection<Greeting> getCollection() {
        return mongoClient
            .getDatabase("main")
            .getCollection("hello", Greeting.class);
    }
}
