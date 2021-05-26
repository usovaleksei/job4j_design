/**
 * class created objects without references
 * i.e. potentially the ones to be removed
 */

public class GCDemo {
    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        info();
        User userOne = new User(35, "Alex");
        User userTwo = new User(28, "Sergey");
        User userThree = new User(30, "Ivan");

        for (int i = 0; i < 2000; i++) {
            new Person(i, "N" + i);
        }
        info();
    }

   /* 2. Объект User userOne = new User(35, “Alex”) для 64-битного JDK:
         Заголовок: 16 Байт
         Поля User: 4 Байта (int) + 64 Байта (String “Alex”: 32 байта(заголовок, поля, ссылка на массив char[]) + 32 байта (массив символов char[4]: Заголовок – 16 байт + длина массива 4 байта; Примитивы char – 2 * 4 = 8 байт; выравнивание – 4 байта)).
         Выравнивание User до кратности 8-ми: 4 Байта
         Итого: 16 + 4 + 64 + 4 = 88 Байт

     3. Пустой объект без полей занимает 16 Байт памяти (для 64-битного JDK). 12 Байт – заголовок, 4 Байта – смещение до размера, кратного 8-ми.
        Пустой объект без полей занимает 8 Байт памяти (для 32-битного JDK).

     5. Ключами -Xms3m и -Xmn3m задаем начальный и максимальный размер хипа 3 Мб.
        При запуске цикла, в котором происходит создание новых объектов User, память хипа начинает заполняться и
        java вызывает сборщик мусора для удаления «мертвых объектов» и освобождения памяти.
*/

}
