package jenerics.container;

/**
 * Interface to container store
 * @author Aleksei Usov
 * @since 17/12/2020
 */

public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);

}
