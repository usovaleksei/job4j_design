package io.duplicates;

import java.util.Objects;

/**
 * class file data model
 * @author Aleksei Usov
 * @since 10/03/2021
 */

public class FileProperty {

    private long size;
    private String name;

    public FileProperty(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return size == that.size && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, name);
    }

    @Override
    public String toString() {
        return "FileProperty{" + "size=" + size + ", name='" + name + '\'' + '}';
    }
}
