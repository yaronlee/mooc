package thread;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class QueueTest {

    @Test
    public void arrayBlockingQueueTest() throws InterruptedException {
        /**
         *  基于数组的有界阻塞队列，队列容量为10
         */
        ArrayBlockingQueue<Integer> queue =
                new ArrayBlockingQueue<Integer>(10);   //入参表示队列容量
        //循环向队列添加元素
        for (int i = 0; i < 20; i++){
            queue.put(i);
            System.out.println("向队列中增加值：" + i);
        }

        //当程序向队列中加入到10个值后，就会阻塞住
    }

    @Test
    public void linkedBlockingQueueTest() throws InterruptedException {
        /**
         *  基于链表的有界阻塞队列，队列容量为10
         */
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        for (int i = 0; i < 20; i++){
            queue.put(i);
            System.out.println("向队列中增加值：" + i);
        }
    }

    @Test
    public void linkedBlockingQueueTest2() throws InterruptedException {
        /**
         *  基于链表的无界阻塞队列，队列容量为10
         *  【无参的构造方法】点击去看一下，发现是max_value
         */
        LinkedBlockingQueue<Integer> queue
                = new LinkedBlockingQueue<>();

        for (int i = 0; i < 20; i++){
            queue.put(i);
            System.out.println("向队列中增加值：" + i);
        }
    }

    @Test
    public void synchronousQueueTest() throws InterruptedException {
        /**
         *  同步移交阻塞队列（无参构造方法），没有存储的能力，
         *  元素的插入依托于有一个线程进行元素的删除，
         *  同样元素的删除也依托于一个元素的插入
         */
        SynchronousQueue<Integer> synchronousQueue =
                new SynchronousQueue<>();

        new Thread(() -> {
            try {
                synchronousQueue.put(1);    //这个线程负责插入值
                System.out.println("插入成功");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                synchronousQueue.take();    //这个线程负责删除值
                System.out.println("删除成功");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //如果把删除值的线程注释掉，发现“插入成功”也打印不出来，说明同步移交队列没有存储的能力
        //只有类似于消费者-生产者模式的缓冲的作用
        //Thread.sleep( 1000L * 60);
    }

}
