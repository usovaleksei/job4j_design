import java.util.ArrayList;
import java.util.List;

public class WeaksTypeDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        while (true) {
            list.add(String.valueOf(System.currentTimeMillis()));
        }
    }
}
