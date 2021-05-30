/*
Объекты, на которые ссылаются слабые ссылки удаляются сразу, если на них нет сильных или безопасных ссылок.
Данный тип ссылок служит для реализации структур для которых у одного значения типа может быть только один объект, например пул строк,
и объекты чаще всего используется всего один раз, т.е. сохранили-получили-забыли.
 */

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeakReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        //example1();
        example2();
    }

    /*
    В первом методе за'null'ение сильной ссылки приводит к удалению объекта и мы его также уже не можем получить по слабой ссылке.
     */
    private static void example1() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        WeakReference<Object> weak = new WeakReference<>(object);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(weak.get());
    }

    /*
    Во втором методе мы создаем объект вообще без сильных ссылки. При вызове сборщика мусора они все удаляются.
     */
    private static void example2() throws InterruptedException {
        List<WeakReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            objects.add(new WeakReference<Object>(new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Removed!");
                }
            }));
        }
        System.gc();
        TimeUnit.SECONDS.sleep(3);
    }
}
