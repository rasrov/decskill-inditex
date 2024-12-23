package rasrov.decskill.inditex.serviceport;

import rasrov.decskill.inditex.entity.PriceEntity;

import java.time.LocalDateTime;
import java.util.Set;

public interface PriceServicePort {

    Set<PriceEntity> findPrices(Integer brandId, Integer productId, LocalDateTime dateTime);

}
