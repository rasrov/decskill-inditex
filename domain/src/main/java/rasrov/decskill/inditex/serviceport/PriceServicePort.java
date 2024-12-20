package rasrov.decskill.inditex.serviceport;

import rasrov.decskill.inditex.entity.PriceEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceServicePort {

    List<PriceEntity> findPrices(Integer brandId, Integer productId, LocalDateTime startDate);

}
