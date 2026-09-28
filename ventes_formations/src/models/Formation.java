package models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class Formation {
    private int idFormation;
    private String titleFormation;
    private String description;
    private int numberOfDays;
    private boolean isRemote;
    private BigDecimal price;
    private boolean isAvailable;

    public Formation(int idFormation, String titleFormation, String description, int numberOfDays, boolean isRemote, BigDecimal price, boolean isAvailable) {
        this.idFormation = idFormation;
        this.titleFormation = titleFormation;
        this.description = description;
        this.numberOfDays = numberOfDays;
        this.isRemote = isRemote;
        this.price = price;
        this.isAvailable = isAvailable;
    }
}
