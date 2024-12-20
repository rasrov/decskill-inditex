package rasrov.decskill.inditex.adapter;

import rasrov.decskill.inditex.entity.PriceEntity;
import rasrov.decskill.inditex.repository.PriceRepository;
import rasrov.decskill.inditex.serviceport.PriceServicePort;

import java.time.LocalDateTime;
import java.util.List;

public class PriceAdapter implements PriceServicePort {

    private final PriceRepository priceRepository;

    public PriceAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<PriceEntity> findPrices(final Integer brandId, final Integer productId, final LocalDateTime startDate) {
        return priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                brandId, productId, startDate, LocalDateTime.now());
    }

}
