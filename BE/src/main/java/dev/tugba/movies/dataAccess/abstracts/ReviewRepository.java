package dev.tugba.movies.dataAccess.abstracts;

import dev.tugba.movies.entities.concretes.Movie;
import dev.tugba.movies.entities.concretes.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
}
