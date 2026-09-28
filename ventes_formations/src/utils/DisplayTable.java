package utils;

import exception.EmptyArrayException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DisplayTable<T extends TableRow> {
    /**
     * Number of item per page
     */
    private static final int ITEMS_PER_PAGE = 10;
    /**
     * List of data of the table cannot be empty
     */
    protected final List<T> data;
    /**
     * The list of Data corresponding to the search
     */
    private List<T> filteredData;
    /**
     * Current page
     */
    private int currentPage = 0;


    /**
     * Constructor by an array
     *
     * @param data
     */
    public DisplayTable(T[] data) {
        this.data = java.util.Arrays.asList(data);
        filteredData = this.data;
    }

    /**
     * Constructor by an list
     *
     * @param data
     */
    public DisplayTable(List<T> data) {
        this.data = data;
        filteredData = this.data;
    }

    /**
     * Do a search in the data
     *
     * @param search
     */
    protected void filterData(String search) {
        currentPage = 0;
        filteredData = data.stream().filter(ligne -> Arrays.stream(ligne.getRowData()).anyMatch(s -> s.toUpperCase().contains(search.toUpperCase()))).collect(Collectors.toCollection(ArrayList::new));
        if (filteredData.isEmpty()) {
            System.out.println("Aucun résultat trouvée pour la recherche");
            filteredData = data;
        }
    }

    /**
     * Get the total number of page
     *
     * @return
     */
    protected int getTotalPages() {
        return (int) Math.ceil((double) filteredData.size() / ITEMS_PER_PAGE);
    }

    /**
     * Return the size by columns (number of char)
     *
     * @return
     */
    protected int[] getColumnWidths() {
        List<T> currentData = getCurrentPageData();
        String[] columnName = currentData.get(0).getColumnNames();

        int[] result = new int[columnName.length];

        for (int columnNumber = 0; columnNumber < columnName.length; columnNumber++) {
            result[columnNumber] = columnName[columnNumber].length();
            for (T currentDatum : currentData) {
                result[columnNumber] = Math.max(result[columnNumber], currentDatum.getRowData()[columnNumber].length());
            }
        }
        return result;
    }

    /**
     * Return the data of the page
     *
     * @return
     */
    protected List<T> getCurrentPageData() {
        return filteredData.subList(currentPage * 10, Math.min((currentPage + 1) * 10, filteredData.size()));
    }

    /**
     * Display the table header and content
     *
     * @throws EmptyArrayException
     */
    private void displayTable() throws EmptyArrayException {
        if (filteredData.isEmpty()) {
            throw new EmptyArrayException();
        }
        int[] sizeColumn = getColumnWidths();
        String[] columnName = filteredData.get(0).getColumnNames();

        /*
            Display First line +----+-------+------+
         */
        StringBuilder delimiter = new StringBuilder();
        for (int columnIndex = 0; columnIndex < columnName.length; columnIndex++) {
            delimiter.append("+");
            delimiter.append(StringUtils.repeat("-", sizeColumn[columnIndex] + 2));
        }
        delimiter.append("+");
        System.out.println(delimiter);

        /*
            Display Header
         */
        System.out.print("| ");
        for (int columnIndex = 0; columnIndex < columnName.length; columnIndex++) {
            System.out.print(StringUtils.padOrTrunc(columnName[columnIndex], sizeColumn[columnIndex]) + " | ");
        }
        System.out.println();
        System.out.println(delimiter);

        for (T line : getCurrentPageData()) {
            String[] colRowData = line.getRowData();
            System.out.print("| ");
            for (int columnIndex = 0; columnIndex < colRowData.length; columnIndex++)
                System.out.print(StringUtils.padOrTrunc(colRowData[columnIndex], sizeColumn[columnIndex]) + " | ");
            System.out.println();
        }
        System.out.println(delimiter);
        System.out.println();
    }

    /**
     * Display the table and choices
     *
     * @param scanner
     * @param text
     * @return
     * @throws EmptyArrayException
     */
    public String show(Scanner scanner, String text) throws EmptyArrayException {
        while (true) {
            displayMenu(text);

            String choice = scanner.next();

            if (handleChoice(choice, scanner)) {
                return choice.equalsIgnoreCase("Q") ? null : choice;
            }
        }
    }

    /**
     * Display choices
     *
     * @param text
     * @throws EmptyArrayException
     */
    private void displayMenu(String text) throws EmptyArrayException {
        displayTable();

        System.out.printf("Page %d sur %d  Total : %d éléments%n", currentPage + 1, getTotalPages(), filteredData.size());

        if (currentPage + 1 < getTotalPages()) {
            System.out.print("[N]ext ");
        }
        if (currentPage > 0) {
            System.out.print("[P]rev ");
        }

        System.out.print("[S]earch ");

        if (data.size() != filteredData.size()) {
            System.out.print("[R]eset ");
        }

        System.out.println("[Q]uit");

        if (text != null) {
            System.out.println(text);
        }
    }

    /**
     * Handle the choice
     *
     * @param choice
     * @param scanner
     * @return
     */
    private boolean handleChoice(String choice, Scanner scanner) {
        switch (choice.toUpperCase()) {
            case "N":
                nextPage();
                break;
            case "P":
                previousPage();
                break;
            case "S":
                promptSearch(scanner);
                break;
            case "R":
                clearFilter();
                break;
            case "Q":
                return true;
            default:
                return true;
        }

        return false;
    }

    /**
     * Reset the search
     */
    private void clearFilter() {
        currentPage = 0;
        filteredData = data;
    }

    /**
     * Go to the next page
     */
    private void nextPage() {
        if (currentPage + 1 < getTotalPages()) {
            currentPage++;
        }
    }

    /**
     * Go to the previous page
     */
    private void previousPage() {
        if (currentPage > 0) {
            currentPage--;
        }
    }

    /**
     * Do a search
     *
     * @param scanner
     */
    private void promptSearch(Scanner scanner) {
        System.out.println("Entrez votre recherche: ");
        filterData(scanner.next());
    }
}
