package io.chat;

import java.io.*;
import java.util.*;

/**
 * class console chat
 * @author Aleksei Usov
 * @since 20/03/2021
 */

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * method reads user question, then display bot answer
     * and write dialog user with bot to log file
     */

    public void run() {
        List<String> dialog = new LinkedList<>();
        List<String> botAnswers = botAnswers();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ваш вопрос: ");
        String userQuestion = scanner.nextLine().strip();
        String botAnswer;
        boolean flag = false;
        while (!userQuestion.equals(OUT)) {
            dialog.add("User: " + userQuestion);
            if (userQuestion.equals(STOP)) {
                flag = true;
            }
            if (userQuestion.equals(CONTINUE)) {
                flag = false;
            }
            if (!flag) {
                botAnswer = botAnswers.get(new Random().nextInt(botAnswers.size()));
                System.out.println("Bot: " + botAnswer + System.lineSeparator());
                dialog.add("Bot: " + botAnswer + System.lineSeparator());
            }
            System.out.print("Введите ваш вопрос: ");
            userQuestion = scanner.nextLine().strip();
        }
        dialog.add("User: " + userQuestion);
        writeLog(dialog);
    }

    /**
     * method adding all strings from file to list
     * @return list of bot answers
     */

    public List<String> botAnswers() {
        List<String> botAnswers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.botAnswers))) {
            br.lines().forEach(botAnswers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botAnswers;
    }

    /**
     * method write dialog to file
     * @param dialog list of user questions and bot answers
     */

    public void writeLog(List<String> dialog) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(this.path)))) {
            dialog.forEach(out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat chat = new ConsoleChat("./data/chatLog.txt", "./data/botAnswers.txt");
        chat.run();
    }
}
