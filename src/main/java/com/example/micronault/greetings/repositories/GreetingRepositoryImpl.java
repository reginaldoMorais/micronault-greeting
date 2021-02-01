package com.example.micronault.greetings.repositories;

import com.example.micronault.greetings.models.Greeting;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.conversions.Bson;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Singleton
public class GreetingRepositoryImpl implements GreetingRepository {

    @Inject
    private MongoClient mongoClient;

    @Override
    public Page<Greeting> findAll(final Pageable pageable) {

        MongoCollection<Greeting> greetingCollection = getCollection();

        int skips = pageable.getSize() * (pageable.getNumber() - 1);
        long totalSize = greetingCollection.countDocuments();

        log.info("===> [{}]", sort(pageable));

        List<Greeting> greetings = greetingCollection.find()
            .skip(skips)
            .limit(pageable.getSize())
            .sort(new BsonDocument("greeting", new BsonInt64(sort(pageable))))
            .into(new ArrayList<>());

        return Page.of(greetings, pageable, totalSize);
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
            .getCollection("greeting", Greeting.class);
    }

    private int sort(final Pageable pageable) {
        if (pageable.getSort().getOrderBy().size() > 0) {
            return pageable.getSort().getOrderBy().get(0).getProperty().equals(Sort.Order.Direction.ASC.toString())
                ? 1
                : -1;
        }

        return 1;
    }
}
