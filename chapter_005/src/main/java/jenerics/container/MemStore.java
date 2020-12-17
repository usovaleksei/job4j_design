package jenerics.container;

import java.util.ArrayList;
import java.util.List;

/**
 * Class universal store for any type objects
 * @author Aleksei Usov
 * @since 17/12/2020
 */

public class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        this.mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = findIndexById(id);
        if (index != -1) {
            this.mem.set(index, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = findIndexById(id);
        if (index != -1) {
            this.mem.remove(index);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        int index = findIndexById(id);
        if (index != -1) {
            result = this.mem.get(index);
        }
        return result;
    }

    public int findIndexById(String id) {
        int result = -1;
        for (int i = 0; i < this.mem.size(); i++) {
            if (this.mem.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }
}
