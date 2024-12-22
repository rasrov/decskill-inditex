package rasrov.decskill.inditex.adapter;

import org.springframework.stereotype.Service;
import rasrov.decskill.inditex.entity.PriceEntity;
import rasrov.decskill.inditex.repository.PriceRepository;
import rasrov.decskill.inditex.serviceport.PriceServicePort;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class PriceAdapter implements PriceServicePort {

    private final PriceRepository priceRepository;

    public PriceAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Set<PriceEntity> findPrices(final Integer brandId, final Integer productId, final LocalDateTime startDate, final LocalDateTime endDate) {
        return priceRepository.findByBrandIdAndProductIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(
                brandId, productId, startDate, endDate);
    }

}
