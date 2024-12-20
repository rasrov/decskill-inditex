package rasrov.decskill.inditex.serviceport;

import rasrov.decskill.inditex.entity.PriceEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceServicePort {

    Optional<PriceEntity> findPrice(LocalDateTime startDate, Integer productId, Integer brandId);

}
