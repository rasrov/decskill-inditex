package adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rasrov.decskill.inditex.adapter.PriceAdapter;
import rasrov.decskill.inditex.entity.PriceEntity;
import rasrov.decskill.inditex.repository.PriceRepository;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
        PriceAdapter.class
})
public class PriceAdapterTest {

    @Autowired
    private PriceAdapter priceAdapter;

    @MockitoBean
    private PriceRepository priceRepository;

    @Test
    void should_be_create_service_with_not_null_dependencies() {
        assertAll(
                "All dependencies are injected correctly",
                () -> assertThat(priceAdapter).isNotNull(),
                () -> assertThat(priceRepository).isNotNull()
        );
    }

    @Test
    void should_return_empty_set_when_data_not_found() {
        final Integer brandId = 1;
        final Integer productId = 333;
        final LocalDateTime startDate = LocalDateTime.now();
        final LocalDateTime endDate = startDate.plusDays(1);

        given(priceRepository.findByBrandIdAndProductIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(eq(brandId), eq(productId), eq(startDate), eq(endDate)))
                .willReturn(Set.of());

        final Set<PriceEntity> prices = priceAdapter.findPrices(brandId, productId, startDate, endDate);
        assertThat(prices).isEmpty();
    }

    @Test
    void should_return_price_when_data_found() {
        final Integer brandId = 1;
        final Integer productId = 333;
        final LocalDateTime startDate = LocalDateTime.now();
        final LocalDateTime endDate = startDate.plusDays(1);

        given(priceRepository.findByBrandIdAndProductIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(eq(brandId), eq(productId), eq(startDate), eq(endDate)))
                .willReturn(Set.of(new PriceEntity()));

        final Set<PriceEntity> prices = priceAdapter.findPrices(brandId, productId, startDate, endDate);
        assertThat(prices).isNotEmpty();
    }

}