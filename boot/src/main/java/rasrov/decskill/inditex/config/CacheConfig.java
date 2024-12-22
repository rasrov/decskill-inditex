package rasrov.decskill.inditex.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

import java.util.concurrent.TimeUnit;

@ConfigurationProperties(prefix = "cache")
@Validated
public record CacheConfig(@NotNull Integer duration,
                          @NotNull Integer maxSize) {

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(duration, TimeUnit.MINUTES)
                .maximumSize(maxSize);
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }

}
