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


        //мапа, в которую будут складываться элементы после прохождения по всем юзерам: e-mail (ключ), user (значение)
        //в этой мапе будут храниться только уникальные e-mailы (в качестве ключей)
        //далее нужно раскидать все эти мэйлы по одинаковым юзерам, кот. хранятся в качестве значений
        Map<String, User> map = new HashMap<>();

        //создаем несколько юзеров
        User userOne = new User("user1", new HashSet<>(Arrays.asList("x", "y")));
        User userTwo = new User("user2", new HashSet<>(Arrays.asList("y", "x", "r")));
        User userThree = new User("user3", new HashSet<>(Arrays.asList("z", "x", "y")));
        User userFour = new User("user4", new HashSet<>(Arrays.asList("x", "f", "e", "j")));
        User userFive = new User("user5", new HashSet<>(Arrays.asList("b", "n", "s")));

        //добавляем юзеров в список
        List<User> userList = List.of(userOne, userTwo, userThree, userFour, userFive);

        //бежим по имеющемуся списку юзеров
        for (User user : userList) {

            //создаем множество Set, берем общую мапу и в качестве значений передаем ключи в виде e-mail
            Set<String> mapMail = new HashSet<>(map.keySet());

            //создаем множество Set, в котором лежат все почты юзера, по которому сейчас проходит цикл
            Set<String> userMails = user.getMail();

            //сравниваем два полученных множества. делаем стрим из общего Set, ищем совпадение мэйлов рассматриваемого юзера
            //в множестве почт, которые были ранее записаны в общую мапу
            //если совпадение найдено, то присваиваем совпавшее значение переменной duplicate
            //если совпадений нет, то duplicate присваиваем null
            String duplicate = mapMail.stream().filter(userMails::contains).findFirst().orElse(null);

            //если совпадение найдено,
            // отсечь совпавший мэйл
            // и также нужно  нужно получить юзера, с которым было найдено совпадение мэйлов
            if (duplicate != null) {
                //удаляем из почт юзера на котором стоит цикл совпавшую почту
                userMails.removeIf(mapMail::contains);
                user = map.get(duplicate);
            }

            //записываем в общую мапу уникальные почты, которые остались после посика дубликатов
            for (String mail : userMails) {
                map.put(mail, user);
            }
        }
        System.out.println(map);

        map.forEach((key, value) -> System.out.println("key: " + key + "     user name: " + value.name + "      user mails: " + value.mail));

        //создаем список, в который будем складывать юзеров с уникальными мэйлами
        List<User> uniqueList = new ArrayList<>();

        //бежим по ключам (мэйлам) общей получившейся мапы
        for (String key : map.keySet()) {

            //получаем юзера по ключу
            User user = map.get(key);

            //если такого юзера нет
            if (!uniqueList.contains(user)) {


                //создаем для этого юзера множество Set, в которое будем складывать уникальные мэйлы
                Set<String> userMails = new HashSet<>();

                //добавляем мэйл
                userMails.add(key);

                //добавляем пользователя в итоговый список юзеров с уникальными мэйлами
                uniqueList.add(new User(user.name, userMails));

            //если такой юзер уже есть в итоговом списке юзеров, то
            } else {

                //бежим по итоговуму списку и ищем юзера, по которому вылезло совпадение
                for (User uniqueUser : uniqueList) {

                    //находим и добавляем к нему еще одну почту
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
