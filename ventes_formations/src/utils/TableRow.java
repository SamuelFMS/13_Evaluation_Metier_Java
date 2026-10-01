package utils;

/**
 * Interface to become a table
 */
public interface TableRow {
    /**
     * Returns the column headers.
     */
    String[] getColumnNames();

    /**
     * Returns the row data for the current instance.
     */
    String[] getRowData();

    /**
     * The id of the column to do the search by default the first
     */
    default int getIdColumnIndex() {
        return 0;
    }
}
