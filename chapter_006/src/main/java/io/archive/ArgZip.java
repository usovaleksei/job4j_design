package io.archive;

/**
 * class to working with entry arguments
 * @author Aleksei Usov
 * @since 13/03/2021
 */

public class ArgZip {

    //entry arguments
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    /**
     * method checks all arguments were transferred or not
     * @return true if all argument were transferred
     */
    public boolean valid() {
        return !(directory().isEmpty() || exclude().isEmpty() || output().isEmpty());
    }

    /**
     * method to get directory name
     * @return directory name from entry argument
     */
    public String directory() {
        return this.args[1];
    }

    /**
     * method to get file parameter for exclusion
     * @return file parameter from entry argument
     */
    public String exclude() {
        return this.args[3];
    }

    /**
     * method to get zip directory name
     * @return zip directory name from entry argument
     */
    public String output() {
        return this.args[5];
    }
}
