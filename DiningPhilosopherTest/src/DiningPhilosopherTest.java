import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Philosopher implements Runnable {
    private int philosopherNumber;
    //是否举起左叉子
    private boolean isTakeLeftFork = false;
    //是否举起右叉子
    private boolean isTakeRightFork = false;
    //是否吃完面
    private boolean hasEaten = false;
    //叉子的空闲状态
    private static List<Boolean> isForksAvailable = new ArrayList<Boolean>(Arrays.asList(true, true, true, true, true));
    static Object o = new Object();

    public Philosopher(int philosopherNumber){
        this.philosopherNumber = philosopherNumber;
    }

    public void run(){
        while (true) {
            try {
                //休眠100毫秒执行一次，避免多线程加同步锁导致的并发
                Thread.sleep(100);
                synchronized (o) {
                    //如果还没吃面
                    if ((!isTakeLeftFork || !isTakeRightFork) && !hasEaten){
                        //没有左叉子，当左叉子空闲的时候举起左叉子，并把左叉子设置为不空闲
                        if (!isTakeLeftFork) {
                            if (isForksAvailable.get(philosopherNumber - 1)){
                                isForksAvailable.set(philosopherNumber - 1, false);
                                System.out.println("Philosopher " + philosopherNumber + " takes left fork.");
                                isTakeLeftFork = true;
                            }
                        }
                        //没有右叉子，当右叉子空闲的时候举起右叉子，并把右叉子设置为不空闲
                        if (!isTakeRightFork) {
                            if (isForksAvailable.get(philosopherNumber % 5)){
                                isForksAvailable.set(philosopherNumber % 5, false);
                                System.out.println("Philosopher " + philosopherNumber + " takes right fork.");
                                isTakeRightFork = true;
                            }
                        }
                    }
                    //吃完面之后
                    else{
                        //开始吃面，打印吃面
                        if (!hasEaten) {
                            System.out.println("Philosopher " + philosopherNumber + " eats spaghetti.");
                            hasEaten = true;
                        }
                        //两个叉子都被放下，打印哲学家思考
                        if (!isTakeLeftFork && !isTakeRightFork) {
                            System.out.println("Philosopher " + philosopherNumber + " thinks.");
                            break;
                        }
                        //左叉子没被放下，放下左叉子，并把左叉子设为空闲状态
                        else if (isTakeLeftFork) {
                            System.out.println("Philosopher " + philosopherNumber + " puts left forks down.");
                            isTakeLeftFork = false;
                            isForksAvailable.set(philosopherNumber - 1, true);
                        }
                        //右叉子没被放下，放下右叉子，并把右叉子设为空闲状态
                        else {
                            System.out.println("Philosopher " + philosopherNumber + " puts right forks down.");
                            isTakeRightFork = false;
                            isForksAvailable.set(philosopherNumber % 5, true);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class DiningPhilosopherTest {
    public static void main(String[] args) {
        //有五个哲学家
        Philosopher philosopher1 = new Philosopher(1);
        Philosopher philosopher2 = new Philosopher(2);
        Philosopher philosopher3 = new Philosopher(3);
        Philosopher philosopher4 = new Philosopher(4);
        Philosopher philosopher5 = new Philosopher(5);
        Thread t1 = new Thread(philosopher1);
        Thread t2 = new Thread(philosopher2);
        Thread t3 = new Thread(philosopher3);
        Thread t4 = new Thread(philosopher4);
        Thread t5 = new Thread(philosopher5);
        //五个哲学家开始吃面
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}



