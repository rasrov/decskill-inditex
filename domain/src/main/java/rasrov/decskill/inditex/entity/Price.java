package rasrov.decskill.inditex.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Price {

    @NotNull(message = "Start date can not be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @NotNull(message = "Product id can not be null")
    private Integer productId;

    @NotNull(message = "Brand id can not be null")
    private Integer brandId;

    public Price() {
    }

    public Price(LocalDateTime startDate, Integer productId, Integer brandId) {
        this.startDate = startDate;
        this.productId = productId;
        this.brandId = brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
}
