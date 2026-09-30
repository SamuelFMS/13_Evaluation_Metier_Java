package models;

public class FormationItemBasket extends Formation {
    /**
     * Is the item in the basket ?
     */
    boolean isInBasket;

    /**
     * Default constructor
     *
     * @param isInBasket
     * @param formation
     */
    public FormationItemBasket(boolean isInBasket, Formation formation) {
        super(formation);
        this.isInBasket = isInBasket;
    }

    @Override
    public String[] getColumnNames() {
        return new String[]{"", "id", "Titre", "Description", "Nombre de jours", "A Distance", "Prix"};
    }

    @Override
    public String[] getRowData() {
        return new String[]{isInBasket ? "✓" : " ", String.valueOf(getIdFormation()), getTitleFormation(), getDescription(), String.valueOf(getNumberOfDays()), isRemote() ? "Oui" : "Non", String.valueOf(getPrice())};
    }

    @Override
    public int getIdColumnIndex() {
        return 1;
    }

}
