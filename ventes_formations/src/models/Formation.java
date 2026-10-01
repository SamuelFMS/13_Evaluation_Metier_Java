package models;

import lombok.Getter;
import lombok.Setter;
import utils.TableRow;

import java.math.BigDecimal;

/**
 * Model Formation contain all the informations of the Formation
 */
@Getter
@Setter
public class Formation implements TableRow {
    /**
     * id of the formation in the batabase null otherwise
     */
    private int idFormation;
    /**
     * Title of the formation
     */
    private String titleFormation;
    /**
     * Description of the formation
     */
    private String description;
    /**
     * Number of days the formation is
     */
    private int numberOfDays;
    /**
     * Is it remote ?
     */
    private boolean isRemote;
    /**
     * Price to buy the formation
     */
    private BigDecimal price;
    /**
     * Is it available to buy ?
     */
    private boolean isAvailable;

    /**
     * Constructor by the object for the Formation Item Basket
     *
     * @param formation
     */
    public Formation(Formation formation) {
        this.idFormation = formation.idFormation;
        this.titleFormation = formation.titleFormation;
        this.description = formation.description;
        this.numberOfDays = formation.numberOfDays;
        this.isRemote = formation.isRemote;
        this.price = formation.price;
        this.isAvailable = formation.isAvailable;
    }

    /**
     * Default constructor
     *
     * @param idFormation
     * @param titleFormation
     * @param description
     * @param numberOfDays
     * @param isRemote
     * @param price
     * @param isAvailable
     */
    public Formation(int idFormation, String titleFormation, String description, int numberOfDays, boolean isRemote, BigDecimal price, boolean isAvailable) {
        this.idFormation = idFormation;
        this.titleFormation = titleFormation;
        this.description = description;
        this.numberOfDays = numberOfDays;
        this.isRemote = isRemote;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return titleFormation + " -> " + this.price + "€";
    }

    @Override
    public String[] getColumnNames() {
        return new String[]{"Titre", "Description", "Nombre de jours", "A Distance", "Prix"};
    }

    @Override
    public String[] getRowData() {
        return new String[]{titleFormation, description, String.valueOf(numberOfDays), isRemote ? "Oui" : "Non", String.valueOf(price)};
    }
}
