import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String nextWord = StdIn.readString();
            randomizedQueue.enqueue(nextWord);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(randomizedQueue.sample());
        }
    }
}
