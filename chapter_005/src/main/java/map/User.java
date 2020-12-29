package map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;
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

    public static void main(String[] args) {
        User userOne = new User("Alex", 1, new GregorianCalendar(1985, Calendar.JUNE, 21));
        User userTwo = new User("Alex", 1, new GregorianCalendar(1985, Calendar.JUNE, 21));

        Map<User, Object> map = new HashMap<>();
        map.put(userOne, new Object());
        map.put(userTwo, new Object());

        for (Map.Entry<User, Object> element : map.entrySet()) {
            System.out.println("key: " + element.getKey() + '\n'
                             + "key" + element + '\n'
                             + "key hashcode: " + element.getKey().hashCode() + '\n'
                             + "value: " + element.getValue() + '\n');
        }
    }
}
