package models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class Contain {
    private Order order;
    private Formation formation;
    private BigDecimal unitPrice;

    public Contain(Order order, Formation formation, BigDecimal unitPrice) {
        this.order = order;
        this.formation = formation;
        this.unitPrice = unitPrice;
    }
}
