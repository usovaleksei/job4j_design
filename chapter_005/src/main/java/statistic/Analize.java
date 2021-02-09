package statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class collects statistics on the change of two lists
 * @author Aleksei Usov
 * @since 09/02/2021
 */

public class Analize {

    /**
     * method collects statistic
     * @param previous list before changes
     * @param current list after changes
     * @return info about changes (added, changes, deleted users)
     */

    public Info diff(List<User> previous, List<User> current) {

        Map<Integer, String> map = current.stream().collect(Collectors.toMap(User::getId, User::getName));
        Info info = new Info(0, 0, 0);
        for (User user : previous) {
            if (!map.containsKey(user.getId())) {
                info.deleted++;
            } else if (!map.containsValue(user.getName())) {
                info.changed++;
            }
            info.added = Math.abs(current.size() - previous.size() - info.deleted);
        }
        return null;
    }

    /**
     * inner class user model
     */

    @Data
    public static class User {
        private int id;
        private String name;
    }

    /**
     * inner class statistic info model
     */

    @Getter
    @AllArgsConstructor
    public static class Info {
        private int added;
        private int deleted;
        private int changed;
    }
}
