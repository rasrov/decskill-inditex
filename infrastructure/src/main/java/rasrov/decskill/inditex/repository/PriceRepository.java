package rasrov.decskill.inditex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rasrov.decskill.inditex.entity.PriceEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

    List<PriceEntity> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Integer brandId,
            Integer productId,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

}
