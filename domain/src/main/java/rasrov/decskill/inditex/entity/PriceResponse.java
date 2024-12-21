package rasrov.decskill.inditex.entity;

public record PriceResponse(Integer productId, Integer brandId, Integer fee, EffectiveDates effectiveDates, Double price, ErrorResponse error) {}
