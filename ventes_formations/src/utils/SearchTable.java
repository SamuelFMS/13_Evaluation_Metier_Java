package utils;

import exception.EmptyArrayException;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SearchTable<T extends TableRow> extends DisplayTable<T> {
    /**
     * The id of the column to do the search by default the first
     */
    private static final int ID_COLUMN_INDEX = 0;

    /**
     * Constructor by array
     *
     * @param data
     */
    public SearchTable(T[] data) {
        super(data);
        validateUniqueIds();
    }

    /**
     * Constructor by list
     *
     * @param data
     */
    public SearchTable(List<T> data) {
        super(data);
        validateUniqueIds();
    }

    /**
     * Check if the id column table doesn't have duplicate item
     */
    private void validateUniqueIds() {
        Set<String> checked = new HashSet<>();
        this.data.forEach(currentToCheck -> {
            String value = currentToCheck.getRowData()[ID_COLUMN_INDEX].toUpperCase();
            if (!checked.add(value)) {
                throw new IllegalArgumentException("Duplicate item: " + value);
            }
        });
    }

    /**
     * Find a key in the data
     *
     * @param id
     * @return
     */
    private boolean containsId(String id) {
        return this.data.stream().anyMatch(maData -> maData.getRowData()[ID_COLUMN_INDEX].equalsIgnoreCase(id));
    }

    /**
     * Show the table and expect a key or null if he cancels
     *
     * @param scanner
     * @param text
     * @return
     * @throws EmptyArrayException
     */
    @Override
    public String show(Scanner scanner, String text) throws EmptyArrayException {
        String s = "";
        do {
            s = super.show(scanner, text);
            if (s == null) {
                break;
            }
        } while (!containsId(s));
        return s;

    }
}
