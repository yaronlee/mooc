package thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadVs {

    /**
     *  老的处理方式
     */
    @Test
    public void oldHandle() throws InterruptedException {

        /**
         *  使用循环来模拟许多（100个）用户请求的场景，每个用户直接开启一个线程
         */
        for (int request = 1; request <= 100; request++){
            new Thread(() ->{
                System.out.println("文档处理开始！");
                try{
                    /**
                     *  将word转换为pdf格式：实际业务简化成 很长很长的耗时过程（目的是学习线程池）
                     */
                    Thread.sleep(1000L * 30);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("文档处理结束！");
            }).start();
        }
        //主线程也sleep，为了便于观察
        Thread.sleep(1000L * 1000);
    }

    /**
     *  新的处理方式
     */
    @Test
    public void newHandle() throws InterruptedException {
        /**
         *  开启一个线程池：线程个数是10个
         */
        ExecutorService threadPool =
                Executors.newFixedThreadPool(10);
        /**
         *  使用循环来模拟许多（100个）用户请求的场景，每个用户直接开启一个线程
         */
        for (int request = 1; request <= 100; request++){
            threadPool.execute(() ->{
                System.out.println("文档处理开始！");
                try{ Thread.sleep(1000L * 30); } catch (InterruptedException e){ e.printStackTrace(); }
                System.out.println("文档处理结束！");
            });
        }

        //便于观察
        Thread.sleep(1000L * 1000);
    }

}
