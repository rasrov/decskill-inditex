package rasrov.decskill.inditex.rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rasrov.decskill.inditex.api.PriceService;
import rasrov.decskill.inditex.entity.Price;
import rasrov.decskill.inditex.entity.PriceResponse;

import java.util.Objects;

@RestController
@RequestMapping("/price")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping
    public ResponseEntity<PriceResponse> findPrice(@Valid @RequestBody final Price price) {
        final PriceResponse priceResponse = priceService.findPrice(
                price.getBrandId(),
                price.getProductId(),
                price.getStartDate()
        );
        return ResponseEntity
                .status(Objects.nonNull(priceResponse.error()) ? HttpStatus.BAD_REQUEST : HttpStatus.OK)
                .body(priceResponse);
    }

}
