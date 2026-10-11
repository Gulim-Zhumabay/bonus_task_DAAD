import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000};
        for (int pass = 0; pass < 3; pass++) run(sizes, pass == 0 ? "warmup" : pass == 1 ? "random" : "sorted");
    }

    static void run(int[] sizes, String mode) {
        System.out.println("--- " + mode + " ---");
        System.out.printf("%-8s %-10s %-10s %-10s %-10s%n", "n", "struct", "insert", "search", "delete");
        for (int n : sizes) {
            int[] keys = new int[n];
            for (int i = 0; i < n; i++) keys[i] = i;
            if (!mode.equals("sorted")) {
                Random r = new Random(42);
                for (int i = n - 1; i > 0; i--) {
                    int j = r.nextInt(i + 1);
                    int t = keys[i]; keys[i] = keys[j]; keys[j] = t;
                }
            }
            bst b = new bst();
            hashtable h = new hashtable();

            long t = System.nanoTime();
            for (int k : keys) b.insert(k);
            long bi = System.nanoTime() - t;
            t = System.nanoTime();
            for (int k : keys) b.search(k);
            long bs = System.nanoTime() - t;
            t = System.nanoTime();
            for (int k : keys) b.delete(k);
            long bd = System.nanoTime() - t;

            t = System.nanoTime();
            for (int k : keys) h.insert(k);
            long hi = System.nanoTime() - t;
            t = System.nanoTime();
            for (int k : keys) h.search(k);
            long hs = System.nanoTime() - t;
            t = System.nanoTime();
            for (int k : keys) h.delete(k);
            long hd = System.nanoTime() - t;

            System.out.printf("%-8d %-10s %-10.3f %-10.3f %-10.3f%n", n, "bst", bi / 1e6, bs / 1e6, bd / 1e6);
            System.out.printf("%-8d %-10s %-10.3f %-10.3f %-10.3f%n", n, "hash", hi / 1e6, hs / 1e6, hd / 1e6);
        }
    }
}