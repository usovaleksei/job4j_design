package map;

import java.util.*;

public class User {

    private final String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday.getTime()
                + '}';
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = result * 31 + Integer.hashCode(children);
        result = result * 31 + birthday.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    public static void main(String[] args) {
        User userOne = new User("Alex", 1, new GregorianCalendar(1985, Calendar.JUNE, 21));
        User userTwo = new User("Alex", 1, new GregorianCalendar(1985, Calendar.JUNE, 21));
        User userThree = new User("Oleg", 2, new GregorianCalendar(1988, Calendar.AUGUST, 5));

        Map<User, Object> map = new HashMap<>();
        map.put(userOne, new Object());
        map.put(userTwo, new Object());
        map.put(userThree, new Object());

        for (Map.Entry<User, Object> element : map.entrySet()) {
            System.out.println("key: " + element.getKey() + '\n'
                             //+ "key" + element + '\n'
                             + "key hashcode: " + element.getKey().hashCode() + '\n'
                             + "value: " + element.getValue() + '\n');
        }

        System.out.println(map);

    }
}
