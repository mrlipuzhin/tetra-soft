package info;

public class AppInfo {
    public void initialize() {
        long startTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        long startHeapSize = runtime.totalMemory() - runtime.freeMemory();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            long heapSize = runtime.totalMemory() - runtime.freeMemory();

            System.out.println(String.format("Time: %d ms\nStart heap size %dMb\nEnd heap size: %dMb",
                    elapsedTime,
                    convertBytesToMb(startHeapSize),
                    convertBytesToMb(heapSize)));
        }));
    }

    private long convertBytesToMb(long value)
    {
        return value / (1024 * 1024);
    }
}
