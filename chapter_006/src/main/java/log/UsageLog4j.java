package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 35;
        boolean flag = true;
        char letter = 'U';
        double cost = 25.15;
        float price = 3.5f;
        byte value = 25;
        short speed = 32124;
        long salary = 18250000;

        LOG.debug("age : {}, answer : {}, main char : {}, cost : {}, price : {}, value : {}, speed : {}, salary : {}",
                age, flag, letter, cost, price, value, speed, salary);
    }
}
