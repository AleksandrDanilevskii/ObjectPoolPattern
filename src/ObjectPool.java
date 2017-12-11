import static java.lang.System.out;

public class ObjectPool {
    public volatile boolean[] shoes;
    /*Модификатор volatile накладывает некоторые дополнительные условия на чтение/запись переменной.
    Операции чтения/записи volatile переменной являются атомарными.
    Результат операции записи значения в volatile переменную одним потоком, становится виден всем другим потокам, которые используют эту переменную для чтения из нее значения.
    */

    public ObjectPool(int n) {//заполняем все true
        shoes = new boolean[n];
        for (int i = 0; i < n; i++) {
            shoes[i] = true;
        }
        out.println("Objectpool containing "+n+" elements is created.");
    }

    public synchronized int rentshoes(int i) throws InterruptedException { //аренда обуви
        //Синхронизированый блок кода может быть выполнен только одним потоком одновременно.
        shoes[i] = false;
        return i;

    }

    public void returnshoes(int i) throws InterruptedException {
        Thread.sleep(200);
        shoes[i] = true;
    }

    public synchronized int next(int i) throws InterruptedException {
        if (shoes[i]) return i % shoes.length;
        else {
            Thread.sleep(1);
            return next((i + 1) % shoes.length);
        }
    }
}