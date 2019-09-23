import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        long time;
        int numberOfWord = 10_000_000;
        int threshold = numberOfWord/Runtime.getRuntime().availableProcessors()*20;
        GenerateString generateString = new GenerateString(3, 10);
        FilterString filterString = new FilterString(generateString.getData(numberOfWord), threshold);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(filterString);
        Thread.sleep(5000);
        System.out.println(time = System.currentTimeMillis());
        forkJoinPool.invoke(filterString);
        System.out.println(System.currentTimeMillis() - time);
    }
}

