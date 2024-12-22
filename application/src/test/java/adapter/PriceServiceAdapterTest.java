package adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rasrov.decskill.inditex.adapter.PriceServiceAdapter;
import rasrov.decskill.inditex.entity.*;
import rasrov.decskill.inditex.serviceport.PriceServicePort;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.extractProperty;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
        PriceServiceAdapter.class
})
public class PriceServiceAdapterTest {

    @Autowired
    private PriceServiceAdapter priceServiceAdapter;

    @MockitoBean
    private PriceServicePort priceServicePort;

    @Test
    void should_be_create_service_with_not_null_dependencies() {
        assertAll(
                "All dependencies are injected correctly",
                () -> assertThat(priceServiceAdapter).isNotNull(),
                () -> assertThat(priceServicePort).isNotNull()
        );
    }

    @Test
    void should_return_empty_prices_but_with_a_warning_message() {
        final Integer brandId = 1;
        final Integer productId = 333;
        final LocalDateTime startDate = LocalDateTime.now();

        given(priceServicePort.findPrices(eq(brandId), eq(productId), eq(startDate), any(LocalDateTime.class))).willReturn(Set.of());

        final PriceResponse price = priceServiceAdapter.findPrice(brandId, productId, startDate);

        assertAll(
                "Assert error message not empty",
                () -> assertThat(price.productId()).isNull(),
                () -> assertThat(price.brandId()).isNull(),
                () -> assertThat(price.fee()).isNull(),
                () -> assertThat(price.effectiveDates()).isNull(),
                () -> assertThat(price.price()).isNull(),
                () -> assertThat(price.error()).isNotNull(),
                () -> assertThat(price.error().message()).isNotEmpty(),
                () -> assertThat(price.error().message()).contains("Not found any active price with")
        );
    }

    @Test
    void should_return_the_highest_priority_active_price() {
        final Integer brandId = 1;
        final Integer productId = 333;
        final LocalDateTime startDate = LocalDateTime.now();
        final LocalDateTime endDate = startDate.plusDays(1);

        final Integer fee = 5;
        final Double priceAmount = 10.0;

        final BrandEntity brandEntity = new BrandEntity(1, "ZARA");
        final ProductEntity productEntity = new ProductEntity(333, "ABRIGO");

        final PriceEntity firstPriceEntity = new PriceEntity(1, brandEntity, startDate, endDate, new PriceListEntity(1, fee), 0, priceAmount, productEntity, "EUR");
        final PriceEntity secondPriceEntity = new PriceEntity(1, brandEntity, startDate, startDate.plusDays(2), new PriceListEntity(1, 10), 1, 20.0, productEntity, "EUR");
        final PriceEntity thirdPriceEntity = new PriceEntity(1, brandEntity, startDate, startDate.plusDays(3), new PriceListEntity(1, 15), 2, 30.0, productEntity, "EUR");

        given(priceServicePort.findPrices(eq(brandId), eq(productId), eq(startDate), any(LocalDateTime.class))).willReturn(Set.of(firstPriceEntity, secondPriceEntity, thirdPriceEntity));

        final PriceResponse price = priceServiceAdapter.findPrice(brandId, productId, startDate);

        assertAll(
                "Assert highest price data",
                () -> assertThat(price.productId()).isEqualTo(productId),
                () -> assertThat(price.brandId()).isEqualTo(brandId),
                () -> assertThat(price.fee()).isEqualTo(fee),
                () -> assertThat(price.effectiveDates()).isNotNull(),
                () -> assertThat(price.effectiveDates().startDate()).isEqualTo(startDate),
                () -> assertThat(price.effectiveDates().endDate()).isEqualTo(endDate),
                () -> assertThat(price.price()).isEqualTo(calculatePvpPrice(fee, priceAmount)),
                () -> assertThat(price.error()).isNull()
        );
    }

    private Double calculatePvpPrice(final Integer fee, final Double price) {
        final Double realFee = 1.0 + fee / 100.0;

        return price * realFee;
    }

}
