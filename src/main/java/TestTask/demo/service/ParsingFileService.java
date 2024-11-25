package TestTask.demo.service;

import TestTask.demo.model.Statistics;
import TestTask.demo.repository.StatisticsRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParsingFileService {

    private final StatisticsRepository statisticsRepository;


    @Scheduled(fixedRate = 600000)
    public void updateStatistics() throws IOException {
        File file = new ClassPathResource("test_report.json").getFile();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(file);

        List<Statistics> statisticsList = new ArrayList<>();


        JsonNode salesAndTrafficByDate = rootNode.get("salesAndTrafficByDate");
        if (salesAndTrafficByDate != null && salesAndTrafficByDate.isArray()) {
            for (JsonNode node : salesAndTrafficByDate) {
                String date = node.get("date").asText();


                Optional<Statistics> existingStatistics = statisticsRepository.findByDate(date);
                Statistics statistics = existingStatistics.orElseGet(Statistics::new);
                statistics.setDate(date);

                JsonNode salesByDate = node.get("salesByDate");
                if (salesByDate != null) {
                    statistics.setSalesByDate(salesByDate.get("orderedProductSales").get("amount").asDouble());
                    statistics.setOrderedProductSalesB2B(salesByDate.get("orderedProductSalesB2B").get("amount").asDouble());
                    statistics.setUnitsOrdered(salesByDate.get("unitsOrdered").asInt());
                    statistics.setUnitsOrderedB2B(salesByDate.get("unitsOrderedB2B").asInt());
                    statistics.setTotalOrderItems(salesByDate.get("totalOrderItems").asInt());
                    statistics.setTotalOrderItemsB2B(salesByDate.get("totalOrderItemsB2B").asInt());
                    statistics.setUnitsRefunded(salesByDate.get("unitsRefunded").asInt());
                    statistics.setRefundRate(salesByDate.get("refundRate").asDouble());
                    statistics.setShippedProductSales(salesByDate.get("shippedProductSales").get("amount").asDouble());
                    statistics.setUnitsShipped(salesByDate.get("unitsShipped").asInt());
                    statistics.setOrdersShipped(salesByDate.get("ordersShipped").asInt());
                }

                JsonNode trafficByDate = node.get("trafficByDate");
                if (trafficByDate != null) {
                    statistics.setBrowserPageViews(trafficByDate.get("browserPageViews").asInt());
                    statistics.setMobileAppPageViews(trafficByDate.get("mobileAppPageViews").asInt());
                    statistics.setPageViews(trafficByDate.get("pageViews").asInt());
                    statistics.setBrowserSessions(trafficByDate.get("browserSessions").asInt());
                    statistics.setMobileAppSessions(trafficByDate.get("mobileAppSessions").asInt());
                    statistics.setSessions(trafficByDate.get("sessions").asInt());
                    statistics.setBuyBoxPercentage(trafficByDate.get("buyBoxPercentage").asDouble());
                    statistics.setOrderItemSessionPercentage(trafficByDate.get("orderItemSessionPercentage").asDouble());
                    statistics.setUnitSessionPercentage(trafficByDate.get("unitSessionPercentage").asDouble());
                }

                statisticsList.add(statistics);
            }
        }


        JsonNode salesAndTrafficByAsin = rootNode.get("salesAndTrafficByAsin");
        if (salesAndTrafficByAsin != null && salesAndTrafficByAsin.isArray()) {
            for (JsonNode node : salesAndTrafficByAsin) {
                String parentAsin = node.get("parentAsin").asText();


                Optional<Statistics> existingStatistics = statisticsRepository.findByParentAsin(parentAsin);
                Statistics statistics = existingStatistics.orElseGet(Statistics::new);
                statistics.setParentAsin(parentAsin);

                JsonNode salesByAsin = node.get("salesByAsin");
                if (salesByAsin != null) {
                    statistics.setSalesByAsin(salesByAsin.get("orderedProductSales").get("amount").asDouble());
                    statistics.setTrafficByAsin(salesByAsin.get("unitsOrdered").asInt());
                }

                JsonNode trafficByAsin = node.get("trafficByAsin");
                if (trafficByAsin != null) {
                    statistics.setBrowserSessionPercentage(trafficByAsin.get("browserSessionPercentage").asDouble());
                    statistics.setMobileAppSessionPercentage(trafficByAsin.get("mobileAppSessionPercentage").asDouble());
                    statistics.setSessionPercentage(trafficByAsin.get("sessionPercentage").asDouble());
                    statistics.setBrowserPageViewsPercentage(trafficByAsin.get("browserPageViewsPercentage").asDouble());
                    statistics.setMobileAppPageViewsPercentage(trafficByAsin.get("mobileAppPageViewsPercentage").asDouble());
                    statistics.setPageViewsPercentage(trafficByAsin.get("pageViewsPercentage").asDouble());
                }

                statisticsList.add(statistics);
            }

        }
        statisticsRepository.saveAll(statisticsList);
    }
}
