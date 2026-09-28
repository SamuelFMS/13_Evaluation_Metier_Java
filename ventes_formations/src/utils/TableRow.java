package utils;

public interface TableRow {
    /**
     * Returns the column headers.
     */
    String[] getColumnNames();

    /**
     * Returns the row data for the current instance.
     */
    String[] getRowData();
}
