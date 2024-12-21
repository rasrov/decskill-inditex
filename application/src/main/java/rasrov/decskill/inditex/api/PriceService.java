package rasrov.decskill.inditex.api;

import rasrov.decskill.inditex.entity.PriceResponse;

import java.time.LocalDateTime;

public interface PriceService {

    PriceResponse findPrice(Integer brandId, Integer productId, LocalDateTime startDate);

}
