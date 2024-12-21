package rasrov.decskill.inditex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rasrov.decskill.inditex.entity.PriceEntity;

import java.time.LocalDateTime;
import java.util.Set;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

    Set<PriceEntity> findByBrandIdAndProductIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(
            Integer brandId,
            Integer productId,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

}
