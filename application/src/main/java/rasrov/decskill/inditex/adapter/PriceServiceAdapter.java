package rasrov.decskill.inditex.adapter;

import org.springframework.stereotype.Service;
import rasrov.decskill.inditex.api.PriceService;
import rasrov.decskill.inditex.entity.EffectiveDates;
import rasrov.decskill.inditex.entity.ErrorResponse;
import rasrov.decskill.inditex.entity.PriceEntity;
import rasrov.decskill.inditex.entity.PriceResponse;
import rasrov.decskill.inditex.serviceport.PriceServicePort;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;

@Service
public class PriceServiceAdapter implements PriceService {

    private final PriceServicePort priceServicePort;

    public PriceServiceAdapter(PriceServicePort priceServicePort) {
        this.priceServicePort = priceServicePort;
    }

    @Override
    public PriceResponse findPrice(final Integer brandId, final Integer productId, final LocalDateTime startDate) {
        final LocalDateTime endDate = LocalDateTime.now();
        final Set<PriceEntity> prices = priceServicePort.findPrices(brandId, productId, startDate, endDate);

        return prices.stream()
                .min(Comparator.comparingInt(PriceEntity::getPriority))
                .map(this::buildPrice)
                .orElse(buildPriceResponseErrorMessage(brandId, productId, startDate, endDate));
    }

    private PriceResponse buildPrice(final PriceEntity priceEntity) {
        final Integer fee = priceEntity.getPriceList().getFee();
        final Double pvpPrice = priceEntity.getPrice() * calculatePercentageFee(fee);
        final EffectiveDates effectiveDates = new EffectiveDates(priceEntity.getStartDate(), priceEntity.getEndDate());

        return new PriceResponse(priceEntity.getProduct().getId(), priceEntity.getBrand().getId(), fee, effectiveDates, pvpPrice, null);
    }

    private PriceResponse buildPriceResponseErrorMessage(final Integer brandId, final Integer productId, final LocalDateTime startDate, final LocalDateTime endDate) {
        return new PriceResponse(null, null, null, null, null,
                new ErrorResponse(String.format("Not found any active price with brand id %s, product id %s, start date %s and end date %s",
                        brandId, productId, startDate, endDate)));
    }

    private Double calculatePercentageFee(final Integer fee) {
        return 1.0 + fee / 100.0;
    }
}
