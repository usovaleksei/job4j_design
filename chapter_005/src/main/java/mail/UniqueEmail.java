package mail;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

public class UniqueEmail {

    //private HashMap<String, String> userList;

    /*public UniqueEmail(HashMap<String, String> userList) {
        this.userList = userList;
    }*/


    public HashMap<String, String> mergeUser(HashMap<String, String> userList) {


        return null;
    }

    public static void main(String[] args) {


        Map<String, User> map = new HashMap<>();
        User userOne = new User("user1", new HashSet<>(Arrays.asList("x", "y")));
        User userTwo = new User("user2", new HashSet<>(Arrays.asList("y", "x", "r")));
        User userThree = new User("user3", new HashSet<>(Arrays.asList("z", "x", "y")));
        User userFour = new User("user4", new HashSet<>(Arrays.asList("x", "f", "e", "j")));
        User userFive = new User("user5", new HashSet<>(Arrays.asList("b", "n", "s")));

        List<User> userList = List.of(userOne, userTwo, userThree, userFour, userFive);

        for (User user : userList) {
            Set<String> mapMail = new HashSet<>(map.keySet());
            Set<String> userMails = user.getMail();
            String duplicate = mapMail.stream().filter(userMails::contains).findFirst().orElse(null);
            if (duplicate != null) {
                userMails.removeIf(mapMail::contains);
                user = map.get(duplicate);
            }
            for (String mail : userMails) {
                map.put(mail, user);
            }
        }
        System.out.println(map);

        map.forEach((key, value) -> System.out.println("key: " + key + "     user name: " + value.name + "      user mails: " + value.mail));

        List<User> uniqueList = new ArrayList<>();

        for (String key : map.keySet()) {
            User user = map.get(key);
            if (!uniqueList.contains(user)) {
                Set<String> userMails = new HashSet<>();
                userMails.add(key);
                uniqueList.add(new User(user.name, userMails));
            } else {
                for (User uniqueUser : uniqueList) {
                    if (uniqueUser.name.equals(user.name)) {
                        uniqueUser.mail.add(key);
                    }
                }
            }
        }

        for (User user : uniqueList) {
            System.out.println("Name: " + user.name + "       Mails: " + user.mail);
        }
    }


    @Data
    @AllArgsConstructor
    public static class User {
        public String name;
        public Set<String> mail;

        @Override
        public String toString() {
            return "User{"
                    +
                    "name='" + name + '\''
                    +
                    '}';
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
            return Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, mail);
        }
    }
}
