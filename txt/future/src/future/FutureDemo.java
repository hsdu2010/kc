package future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureDemo {
    
    private FutureDemo() {
    }

    private static final int TASK_COUNT = 3;
    private static final int TIMEOUT_TIME = 8;
    
    public static void main(String[] args) {
        long beginTm = System.currentTimeMillis();
        parallelAwait(startThread());
        System.out.println(System.currentTimeMillis() - beginTm);
    }
    
    
    private static List<Future<String>> startThread(){
        ExecutorService es = Executors.newFixedThreadPool(4);
        List<Future<String>> futures = new ArrayList<>(TASK_COUNT); 
        for(int i = 0; i<TASK_COUNT; i++ ){
        	final int j = i;
            futures.add(es.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("run...");
                    if(j == 0 || j == 2){
                    	while(true){
                            Thread.sleep(1000);
                        }
                    }
                    return j + "";
                }
            }));
        }
        return futures;
    }
    
    /** 
     * 采用for循环的方式进行遍历校验future
     * @param futures 任务句柄
     * @author 80002517
     * @date 2018年6月14日
     */
    private static void await(List<Future<String>> futures){
        for (Future<String> future : futures) {
            try {
                String result = future.get(TIMEOUT_TIME, TimeUnit.SECONDS);// 设置超时时间为1分钟
                System.out.println(result);
            } catch (InterruptedException e) {// 如果当前的线程在等待时被中断
                System.err.println("task thread break");
                future.cancel(true);// 取消任务
            } catch (ExecutionException e) {// 如果计算抛出异常
                System.err.println("task thread error");
                future.cancel(true);// 取消任务
            } catch (TimeoutException e) {// 线程超时
                System.err.println("task thread timeout");
                future.cancel(true);// 取消任务
            }
        }
        futures.clear();
    }
    
    /** 
     * 采用for循环并行的方式校验future
     * @param futures 任务句柄
     * @author 80002517
     * @date 2018年6月14日
     */
    private static void parallelAwait(List<Future<String>> futures){
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "1");
        futures.parallelStream().forEach(future->{
            try {
                String result = future.get(TIMEOUT_TIME, TimeUnit.SECONDS);// 设置超时时间为1分钟
                System.out.println(result);
            } catch (InterruptedException e) {// 如果当前的线程在等待时被中断
                System.err.println("task thread break");
                future.cancel(true);// 取消任务
            } catch (ExecutionException e) {// 如果计算抛出异常
                System.err.println("task thread error");
                future.cancel(true);// 取消任务
            } catch (TimeoutException e) {// 线程超时
                System.err.println("task thread timeout");
                future.cancel(true);// 取消任务
            }
        });
        futures.clear();
    }
}
