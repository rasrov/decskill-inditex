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

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private Caffeine caffeine;

    @Test
    void cache_config_should_be_init_properly() {
        assertThat(cacheConfig).isNotNull();
        assertThat(cacheConfig.duration()).isNotNull();
        assertThat(cacheConfig.maxSize()).isNotNull();

        assertThat(caffeine).isNotNull();
        assertThat(cacheManager).isNotNull();
    }

}
