package TestTask.demo;

import TestTask.demo.model.Statistics;
import TestTask.demo.repository.StatisticsRepository;
import TestTask.demo.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StatisticsServiceTest {

    @Mock
    private StatisticsRepository statisticsRepository;

    @InjectMocks
    private StatisticsService statisticsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStatisticsByDateRangeWithValidData() {
        // Arrange
        String startDate = "2024-01-01";
        String endDate = "2024-01-31";
        Statistics stat1 = new Statistics();
        Statistics stat2 = new Statistics();
        List<Statistics> mockStatistics = Arrays.asList(stat1, stat2);

        when(statisticsRepository.findByDateBetween(startDate, endDate)).thenReturn(mockStatistics);


        List<Statistics> result = statisticsService.getStatisticsByDateRange(startDate, endDate);


        assertNotNull(result);
        assertEquals(2, result.size());
        verify(statisticsRepository, times(1)).findByDateBetween(startDate, endDate);
    }

    @Test
    void testGetStatisticsByDateRangeWithEmptyEndDate() {

        String startDate = "2024-01-01";
        String endDate = "";
        Statistics stat1 = new Statistics();
        List<Statistics> mockStatistics = Arrays.asList(stat1);

        when(statisticsRepository.findByDateBetween(startDate, startDate)).thenReturn(mockStatistics);


        List<Statistics> result = statisticsService.getStatisticsByDateRange(startDate, endDate);


        assertNotNull(result);
        assertEquals(1, result.size());
        verify(statisticsRepository, times(1)).findByDateBetween(startDate, startDate);
    }

    @Test
    void testGetAllByParentAsin() {

        String parentAsin = "parentAsin123";
        Statistics stat1 = new Statistics();
        List<Statistics> mockStatistics = Arrays.asList(stat1);

        when(statisticsRepository.findAllByParentAsin(parentAsin)).thenReturn(mockStatistics);


        List<Statistics> result = statisticsService.getAllByParentAsin(parentAsin);


        assertNotNull(result);
        assertEquals(1, result.size());
        verify(statisticsRepository, times(1)).findAllByParentAsin(parentAsin);
    }

    @Test
    void testGetByParentAsinIn() {

        List<String> parentAsins = Arrays.asList("parentAsin123", "parentAsin456");
        Statistics stat1 = new Statistics();  // You can set values here
        List<Statistics> mockStatistics = Arrays.asList(stat1);

        when(statisticsRepository.findByParentAsinIn(parentAsins)).thenReturn(mockStatistics);


        List<Statistics> result = statisticsService.getByParentAsinIn(parentAsins);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(statisticsRepository, times(1)).findByParentAsinIn(parentAsins);
    }

    @Test
    void testGetAllForSummary() {

        Statistics stat1 = new Statistics();
        List<Statistics> mockStatistics = Arrays.asList(stat1);

        when(statisticsRepository.findAllForSummary()).thenReturn(mockStatistics);


        List<Statistics> result = statisticsService.getAllForSummary();


        assertNotNull(result);
        assertEquals(1, result.size());
        verify(statisticsRepository, times(1)).findAllForSummary();
    }

    @Test
    void testGetAllByAsinSummary() {

        Statistics stat1 = new Statistics();
        List<Statistics> mockStatistics = Arrays.asList(stat1);

        when(statisticsRepository.findAllByAsinSummary()).thenReturn(mockStatistics);


        List<Statistics> result = statisticsService.getAllByAsinSummary();


        assertNotNull(result);
        assertEquals(1, result.size());
        verify(statisticsRepository, times(1)).findAllByAsinSummary();
    }
}
