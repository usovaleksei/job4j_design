package jenerics.container;

/**
 * Class store for objects RoleStore
 * @author Aleksei Usov
 * @since 17/12/2020
 */

public class RoleStore implements Store<Role> {

    private final Store<Role> role = new MemStore<>();

    @Override
    public void add(Role model) {
        this.role.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return this.role.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return this.role.delete(id);
    }

    @Override
    public Role findById(String id) {
        return this.role.findById(id);
    }
}
