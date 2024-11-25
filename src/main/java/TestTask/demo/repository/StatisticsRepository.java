package TestTask.demo.repository;

import TestTask.demo.model.Statistics;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StatisticsRepository extends MongoRepository<Statistics, ObjectId> {
    Optional<Statistics> findByParentAsin(String parentAsin);

    Optional<Statistics> findByDate(String date);

    List<Statistics> findByDateBetween(String startDate, String endDate);

    List<Statistics> findAllByParentAsin(String parentAsin);

    @Query("{'parentAsin': {'$in': ?0}}")
    List<Statistics> findByParentAsinIn(List<String> parentAsins);

    @Query(value = "{}", fields = "{'date': 0, '_id': 0}")
    List<Statistics> findAllForSummary();

    @Query(value = "{}", fields = "{'parentAsin': 1, 'salesByAsin': 1, 'trafficByAsin': 1, '_id': 0}")
    List<Statistics> findAllByAsinSummary();
}
