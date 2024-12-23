package rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import rasrov.decskill.inditex.DecskillInditexApplication;
import rasrov.decskill.inditex.entity.Price;
import rasrov.decskill.inditex.entity.PriceResponse;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DecskillInditexApplication.class)
public class PriceControllerIntegrationTest {

    private static final Integer BRAND_ID = 1;
    private static final Integer PRODUCT_ID = 35455;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void first_use_case_test() {
        final LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        final String url = "/price";

        System.out.printf("Test 1: petición a las 10:00 del día 14 del producto %s para la brand %s (%s)%n", PRODUCT_ID, BRAND_ID, "ZARA");

        final ResponseEntity<PriceResponse> response = restTemplate.postForEntity(url, buildPriceBody(startDate, PRODUCT_ID, BRAND_ID), PriceResponse.class);

        assertAll(
                () -> assertThat(response.getStatusCode().is2xxSuccessful()).isTrue(),
                () -> assertThat(Objects.requireNonNull(response.getBody()).brandId()).isEqualTo(1),
                () -> assertThat(Objects.requireNonNull(response.getBody()).productId()).isEqualTo(35455),
                () -> assertThat(Objects.requireNonNull(response.getBody()).fee()).isEqualTo(5),
                () -> assertThat(Objects.requireNonNull(response.getBody()).effectiveDates()).isNotNull(),
                () -> assertThat(Objects.requireNonNull(response.getBody()).effectiveDates().startDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 0, 0, 0)),
                () -> assertThat(Objects.requireNonNull(response.getBody()).effectiveDates().endDate()).isEqualTo(LocalDateTime.of(2020, 12, 31, 23, 59, 59)),
                () -> assertThat(Objects.requireNonNull(response.getBody()).price()).isEqualTo(37.275),
                () -> assertThat(Objects.requireNonNull(response.getBody()).error()).isNull()
        );
    }

    @Test
    void second_use_case_test() {
        final LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        final String url = "/price";

        System.out.printf("Test 2: petición a las 16:00 del día 14 del producto %s para la brand %s (%s)%n", PRODUCT_ID, BRAND_ID, "ZARA");

        final ResponseEntity<PriceResponse> response = restTemplate.postForEntity(url, buildPriceBody(startDate, PRODUCT_ID, BRAND_ID), PriceResponse.class);

        assertAll(
                () -> assertThat(response.getStatusCode().is2xxSuccessful()).isTrue(),
                () -> assertThat(Objects.requireNonNull(response.getBody()).brandId()).isEqualTo(1),
                () -> assertThat(Objects.requireNonNull(response.getBody()).productId()).isEqualTo(35455),
                () -> assertThat(Objects.requireNonNull(response.getBody()).fee()).isEqualTo(10),
                () -> assertThat(Objects.requireNonNull(response.getBody()).effectiveDates()).isNotNull(),
                () -> assertThat(Objects.requireNonNull(response.getBody()).effectiveDates().startDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 15, 0, 0)),
                () -> assertThat(Objects.requireNonNull(response.getBody()).effectiveDates().endDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 18, 30, 0)),
                () -> assertThat(Objects.requireNonNull(response.getBody()).price()).isEqualTo(27.995),
                () -> assertThat(Objects.requireNonNull(response.getBody()).error()).isNull()
        );
    }

    @Test
    void third_use_case_test() {
        final LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        final String url = "/price";

        System.out.printf("Test 3: petición a las 21:00 del día 14 del producto %s para la brand %s (%s)%n", PRODUCT_ID, BRAND_ID, "ZARA");

        final ResponseEntity<PriceResponse> response = restTemplate.postForEntity(url, buildPriceBody(startDate, PRODUCT_ID, BRAND_ID), PriceResponse.class);

        assertAll(
                () -> assertThat(response.getStatusCode().is2xxSuccessful()).isTrue(),
                () -> assertThat(Objects.requireNonNull(response.getBody()).brandId()).isEqualTo(1),
                () -> assertThat(Objects.requireNonNull(response.getBody()).productId()).isEqualTo(35455),
                () -> assertThat(Objects.requireNonNull(response.getBody()).fee()).isEqualTo(5),
                () -> assertThat(Objects.requireNonNull(response.getBody()).effectiveDates()).isNotNull(),
                () -> assertThat(Objects.requireNonNull(response.getBody()).effectiveDates().startDate()).isEqualTo(LocalDateTime.of(2020, 6, 14, 0, 0, 0)),
                () -> assertThat(Objects.requireNonNull(response.getBody()).effectiveDates().endDate()).isEqualTo(LocalDateTime.of(2020, 12, 31, 23, 59, 59)),
                () -> assertThat(Objects.requireNonNull(response.getBody()).price()).isEqualTo(37.275),
                () -> assertThat(Objects.requireNonNull(response.getBody()).error()).isNull()
        );
    }

    @Test
    void fourth_use_case_test() {
        final LocalDateTime startDate = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        final String url = "/price";

        System.out.printf("Test 4: petición a las 10:00 del día 15 del producto %s para la brand %s (%s)%n", PRODUCT_ID, BRAND_ID, "ZARA");

        final ResponseEntity<PriceResponse> response = restTemplate.postForEntity(url, buildPriceBody(startDate, PRODUCT_ID, BRAND_ID), PriceResponse.class);

        assertAll(
                () -> assertThat(response.getStatusCode().is2xxSuccessful()).isTrue(),
                () -> assertThat(Objects.requireNonNull(response.getBody()).brandId()).isEqualTo(1),
                () -> assertThat(Objects.requireNonNull(response.getBody()).productId()).isEqualTo(35455),
                () -> assertThat(Objects.requireNonNull(response.getBody()).fee()).isEqualTo(15),
                () -> assertThat(Objects.requireNonNull(response.getBody()).effectiveDates()).isNotNull(),
                () -> assertThat(Objects.requireNonNull(response.getBody()).effectiveDates().startDate()).isEqualTo(LocalDateTime.of(2020, 6, 15, 0, 0, 0)),
                () -> assertThat(Objects.requireNonNull(response.getBody()).effectiveDates().endDate()).isEqualTo(LocalDateTime.of(2020, 6, 15, 11, 0, 0)),
                () -> assertThat(Objects.requireNonNull(response.getBody()).price()).isEqualTo(35.075),
                () -> assertThat(Objects.requireNonNull(response.getBody()).error()).isNull()
        );
    }

    @Test
    void fifth_use_case_test() {
        final LocalDateTime startDate = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        final String url = "/price";

        System.out.printf("Test 5: petición a las 21:00 del día 16 del producto %s para la brand %s (%s)%n", PRODUCT_ID, BRAND_ID, "ZARA");

        final ResponseEntity<PriceResponse> response = restTemplate.postForEntity(url, buildPriceBody(startDate, PRODUCT_ID, BRAND_ID), PriceResponse.class);

        assertAll(
                () -> assertThat(response.getStatusCode().is2xxSuccessful()).isTrue(),
                () -> assertThat(Objects.requireNonNull(response.getBody()).brandId()).isEqualTo(1),
                () -> assertThat(Objects.requireNonNull(response.getBody()).productId()).isEqualTo(35455),
                () -> assertThat(Objects.requireNonNull(response.getBody()).fee()).isEqualTo(21),
                () -> assertThat(Objects.requireNonNull(response.getBody()).effectiveDates()).isNotNull(),
                () -> assertThat(Objects.requireNonNull(response.getBody()).effectiveDates().startDate()).isEqualTo(LocalDateTime.of(2020, 6, 15, 16, 0, 0)),
                () -> assertThat(Objects.requireNonNull(response.getBody()).effectiveDates().endDate()).isEqualTo(LocalDateTime.of(2020, 12, 31, 23, 59, 59)),
                () -> assertThat(Objects.requireNonNull(response.getBody()).price()).isEqualTo(47.13),
                () -> assertThat(Objects.requireNonNull(response.getBody()).error()).isNull()
        );
    }

    private Price buildPriceBody(final LocalDateTime startDate, final Integer productId, final Integer brandId) {
        return new Price(startDate, productId, brandId);
    }

}
