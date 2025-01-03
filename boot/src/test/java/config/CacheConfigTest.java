package config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rasrov.decskill.inditex.config.AppConfig;
import rasrov.decskill.inditex.config.CacheConfig;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AppConfig.class)
@TestPropertySource("classpath:application.properties")
public class CacheConfigTest {

    @Autowired
    private CacheConfig cacheConfig;

    @Test
    void cache_config_should_be_init_properly() {
        assertThat(cacheConfig).isNotNull();
        assertThat(cacheConfig.duration()).isNotNull();
        assertThat(cacheConfig.duration()).isEqualTo(15);
        assertThat(cacheConfig.maxSize()).isEqualTo(15);
        assertThat(cacheConfig.maxSize()).isNotNull();
    }

}
