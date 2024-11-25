package TestTask.demo.controller;

import TestTask.demo.model.Statistics;
import TestTask.demo.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;


    @GetMapping("/by-date-range")
    public ResponseEntity<?> getStatistics(@RequestParam String startDate, @RequestParam(required = false) String endDate) {
        List<Statistics> statistics = statisticsService.getStatisticsByDateRange(startDate, endDate);

        if (statistics.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/parent-asin/{parentAsin}")
    public ResponseEntity<?> getAllByParentAsin(@PathVariable String parentAsin) {
        List<Statistics> statistics = statisticsService.getAllByParentAsin(parentAsin);
        if (statistics.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(statistics);
    }

    @PostMapping("/parent-asin")
    public ResponseEntity<?> getByParentAsinIn(@RequestBody List<String> parentAsins) {
        List<Statistics> statistics = statisticsService.getByParentAsinIn(parentAsins);
        if (statistics.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/summary")
    public ResponseEntity<?> getAllForSummary() {
        List<Statistics> statistics = statisticsService.getAllForSummary();
        if (statistics.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/asin-summary")
    public ResponseEntity<?> getAllByAsinSummary() {
        if (statisticsService.getAllByAsinSummary().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(statisticsService.getAllByAsinSummary());
    }
}
