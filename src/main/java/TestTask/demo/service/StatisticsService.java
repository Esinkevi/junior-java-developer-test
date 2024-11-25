package TestTask.demo.service;

import TestTask.demo.model.Statistics;
import TestTask.demo.repository.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@CacheConfig(cacheManager = "redisCacheManager")
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Cacheable("staticsByDateRange")
    public List<Statistics> getStatisticsByDateRange(String startDate, String endDate) {
        if (endDate == null || endDate.isEmpty()) {
            endDate = startDate;
        }
        return statisticsRepository.findByDateBetween(startDate, endDate);
    }

    @Cacheable("staticsByParentAsin")
    public List<Statistics> getAllByParentAsin(String parentAsin) {
        return statisticsRepository.findAllByParentAsin(parentAsin);
    }

    @Cacheable("staticsByParentAsinIn")
    public List<Statistics> getByParentAsinIn(List<String> parentAsins) {
        return statisticsRepository.findByParentAsinIn(parentAsins);
    }

    @Cacheable("staticsByAsin") //check
    public List<Statistics> getAllForSummary() {
        return statisticsRepository.findAllForSummary();
    }

    @Cacheable("staticsByAsinSummary")
    public List<Statistics> getAllByAsinSummary() {
        return statisticsRepository.findAllByAsinSummary();
    }


}
