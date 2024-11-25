package TestTask.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "app.cache")
public class AppCacheProperties {


    private final List<String> cacheNames = new ArrayList<>();

    private final Map<String, cacheProperties> caches = new HashMap<>();

    private CacheType cacheType;

    @Data
    public static class cacheProperties {
        private Duration expiry = Duration.ZERO;

    }

    public interface CacheNames {
        String STATICS_BY_DATE_RANGE = "staticsByDateRange";
        String STATICS_BY_PARENT_ASIN = "staticsByParentAsin";
        String STATICS_BY_PARENT_ASIN_IN = "staticsByParentAsinIn";
        String STATICS_BY_ASIN = "staticsByAsin";
        String STATICS_BY_ASIN_SUMMARY = "staticsByAsinSummary";

    }

    public enum CacheType {
        REDIS
    }

}
