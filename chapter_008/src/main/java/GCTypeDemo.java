import java.util.Random;

/**
 * Class составляет строку из продублированного несколько раз символа, при этом перезаписывая ячейки массива.
 * Старые строки стираются сборщиком мусора.
 */

public class GCTypeDemo {
    public static void main(String[] args) {
        Random random = new Random();
        int length = 100;
        String[] data = new String[1_000_000];
        for (int i = 0; ; i = (i + 1) % data.length) {
            data[i] = String.valueOf(
                    (char) random.nextInt(255)
            ).repeat(length);
        }
    }

    /*
    Инструменты для профилирования приложения:
    jps - консольная утилита для получения pid (process id)
    jmap - утилита позволяет видеть какие объекты создаются, какие ожидают удаления
            jmap -histo <pid>
    jstat - утилита предоставляет сводную информацию о состоянии памяти программы
            jstat -gc <pid> 1s 10 (сэмплинг каждую секунду 10 секунд)
    jconsole - утилита отображает состояние изменения памяти. Имеет графический интерфейс
     */

}
