package rasrov.decskill.inditex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRICE_LIST")
public class PriceListEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "fee")
    private Integer fee;

}
