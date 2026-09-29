package utils;

import lombok.Getter;

public class Actions {
    /**
     * Key to press to activate the action
     */
    @Getter
    private char key;
    /**
     * Display information about this action
     */
    private final String val;
    /**
     * Method called when the action is executed
     */
    private final Runnable action;

    /**
     * Constructor
     * @param key
     * @param val
     * @param action
     */
    public Actions(char key, String val, Runnable action) {
        this.key = key;
        this.val = val;
        this.action = action;
    }

    /**
     * Execute the actions
     */
    public void callRunnable(){
        action.run();
    }

    /**
     * Print the action ex: [S]Rechercher
     * @return
     */
    @Override
    public String toString() {
        return "["+key+"]"+val + " ";
    }
}
