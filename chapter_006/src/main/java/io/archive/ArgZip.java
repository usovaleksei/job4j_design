package io.archive;

public class ArgZip {

    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        return !(directory().isEmpty() || exclude().isEmpty() || output().isEmpty());
    }

    public String directory() {
        return this.args[1];
    }

    public String exclude() {
        return this.args[3];
    }

    public String output() {
        return this.args[5];
    }
}
