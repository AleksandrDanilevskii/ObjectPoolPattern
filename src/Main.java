import static java.lang.System.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        /*По своей сути, InterruptedException сигнализирует о том, что поток
        просят завершить его работу. При этом вас не просят немедленно завершить свою работу.
        Вас просят корректно завершить работу.*/

        int nOP = 5;//количество объектов в ObjectPool
        int nT = 20;//количество потоков
        Objectpool pool = new Objectpool(nOP);

        class MyCode implements Runnable {
            public void run() {

                long t = currentTimeMillis();
                int i;
                try {
                    i = pool.rentshoes(pool.next(0));
                    out.println(Thread.currentThread().getName() + " rented shoes.");
                    Thread.sleep(5000);//время аренды
                    pool.returnshoes(i);
                    out.println(Thread.currentThread().getName() + " returned shoes.");
                    out.println("Time "+ Thread.currentThread().getName() + ": " + (currentTimeMillis() - t) + "ms");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //out.println(Thread.currentThread().getName() + " played");
                //out.println("Time "+ Thread.currentThread().getName() + ": " + (currentTimeMillis() - t));
            }
        }

        Thread[] threads = new Thread[nT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new MyCode()); //Thread(Runnable объект_потока
            threads[i].start();
        }
    }
}