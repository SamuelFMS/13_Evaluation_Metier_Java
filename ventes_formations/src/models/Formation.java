package models;

import lombok.Getter;
import lombok.Setter;
import utils.TableRow;

import java.math.BigDecimal;

@Getter @Setter
public class Formation implements TableRow {
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

    @Override
    public String[] getColumnNames() {
        return new String[]{"Titre", "Description", "Nombre de jours", "A Distance", "Prix"};
    }

    @Override
    public String[] getRowData() {
        return new String[]{titleFormation, description, String.valueOf(numberOfDays), isRemote?"Oui": "Non", String.valueOf(price)};
    }
}
