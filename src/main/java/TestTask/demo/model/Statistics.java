package TestTask.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "statistics")
public class Statistics implements Serializable {
    @Id
    private String id;

    private String reportSpecification;
    private String salesAndTrafficByDate;
    private String salesAndTrafficByAsin;
    @Indexed
    private String date;
    private double salesByDate;
    private double trafficByDate;
    private double orderedProductSales;
    private double orderedProductSalesB2B;
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
    private double averageSalesPerOrderItem;
    private double averageSalesPerOrderItemB2B;
    private double averageUnitsPerOrderItem;
    private double averageUnitsPerOrderItemB2B;
    private double averageSellingPrice;
    private double averageSellingPriceB2B;
    private int unitsRefunded;
    private double refundRate;
    private int claimsGranted;
    private double claimsAmount;
    private double shippedProductSales;
    private int unitsShipped;
    private int ordersShipped;
    private int browserPageViews;
    private int browserPageViewsB2B;
    private int mobileAppPageViews;
    private int mobileAppPageViewsB2B;
    private int pageViews;
    private int pageViewsB2B;
    private int browserSessions;
    private int browserSessionsB2B;
    private int mobileAppSessions;
    private int mobileAppSessionsB2B;
    private int sessions;
    private int sessionsB2B;
    private double buyBoxPercentage;
    private double buyBoxPercentageB2B;
    private double orderItemSessionPercentage;
    private double orderItemSessionPercentageB2B;
    private double unitSessionPercentage;
    private double unitSessionPercentageB2B;
    private double averageOfferCount;
    private double averageParentItems;
    private int feedbackReceived;
    private int negativeFeedbackReceived;
    private double receivedNegativeFeedbackRate;

    @Indexed
    private String parentAsin;
    private double salesByAsin;
    private double trafficByAsin;
    private double browserSessionPercentage;
    private double mobileAppSessionPercentage;
    private double sessionPercentage;
    private double browserPageViewsPercentage;
    private double mobileAppPageViewsPercentage;
    private double pageViewsPercentage;
}
