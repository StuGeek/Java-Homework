import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class TicketWindow implements Runnable {
    private int windowNumber;
    private List<Integer> ticketList = new ArrayList<Integer>();
    static Object o = new Object();

    public TicketWindow(int windowNumber, List<Integer> ticketList) {
        this.windowNumber = windowNumber;
        this.ticketList = ticketList;
    }

    public void run() {
        while (!ticketList.isEmpty()) {
            try {
                //休眠50毫秒执行一次，避免多线程加同步锁导致的并发
                Thread.sleep(50);
                synchronized (o) {
                    //如果票没卖完，将票列表中的第一张票卖出
                    if (!ticketList.isEmpty()) {
                        System.out.println("Window " + windowNumber + ": Ticket " + ticketList.get(0) + " is sold.");
                        ticketList.remove(0);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class TicketWindowTest {
    public static void main(String[] args) {
        //按照输出样例给的不是顺序的卖票顺序初始化票编号
        List<Integer> ticketList = new ArrayList<Integer>(
                Arrays.asList(1, 2, 3, 4, 5, 7, 6, 8, 9, 10, 11, 12, 13, 14, 16, 17, 15, 18, 19, 20));
        TicketWindow window1 = new TicketWindow(1, ticketList);
        TicketWindow window2 = new TicketWindow(2, ticketList);
        TicketWindow window3 = new TicketWindow(3, ticketList);
        Thread t1 = new Thread(window1);
        Thread t2 = new Thread(window2);
        Thread t3 = new Thread(window3);
        //三个窗口同时开始卖票
        t1.start();
        t2.start();
        t3.start();
    }
}