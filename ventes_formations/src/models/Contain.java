package models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Model Contain contain all the informations of the Contain
 */
@Getter
@Setter
public class Contain {
    /**
     * Order of to contain
     */
    private Order order;
    /**
     * Formation of to contain
     */
    private Formation formation;
    /**
     * Price when bought
     */
    private BigDecimal unitPrice;

    /**
     * Default constructor
     *
     * @param order
     * @param formation
     * @param unitPrice
     */
    public Contain(Order order, Formation formation, BigDecimal unitPrice) {
        this.order = order;
        this.formation = formation;
        this.unitPrice = unitPrice;
    }
}
