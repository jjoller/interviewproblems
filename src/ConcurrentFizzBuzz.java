import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Concurrent FizzBuzz (Cracking the Coding Interview, Edition 5, page 180)
 * <p>
 * In the classic problem FizzBuzz, you are told to print the number from 1 to n. However, when the number is divisible
 * by 3, print "Fizz". When it is divisible by 5, print "Buzz". Whe it is divisible by 3 and 5, print "FizzBuzz".
 * In this problem, you are sked to do this in a multithreaded way. Implement a multithreaded version of FizzBuzz with
 * four threads. One thread checks for divisibility of 3 and prints "Fizz". Another thread is responsible for
 * divisibility of 5 and prints "Buzz". A third thread is responsible for divisibility of 3 and 5 and prints "FizzBuzz".
 * A fourth thread does the numbers.
 */
public class ConcurrentFizzBuzz {
    public static void main(String... args) {

        int n = 100;

        CyclicBarrier gate = new CyclicBarrier(4);

        List<Thread> workers = Arrays.asList(
                new Thread(new Numbers(n, gate)),
                new Thread(new Fizz(n, gate)),
                new Thread(new Buzz(n, gate)),
                new Thread(new FizzBuzz(n, gate)));

        workers.forEach(Thread::start);
        workers.forEach(w -> {
            try {
                w.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

abstract class Worker implements Runnable {

    Worker(int n, CyclicBarrier gate) {
        this.n = n;
        this.gate = gate;
    }

    private final int n;
    private final CyclicBarrier gate;

    @Override
    public void run() {
        try {
            for (int i = 1; i < n; i++) {
                gate.await();
                if (isResponsible(i)) {
                    print(i);
                }
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract boolean isResponsible(int i);

    protected abstract void print(int i);
}

class Numbers extends Worker {

    Numbers(int n, CyclicBarrier gate) {
        super(n, gate);
    }

    @Override
    protected boolean isResponsible(int i) {
        return i % 3 != 0 && i % 5 != 0;
    }

    @Override
    protected void print(int i) {
        System.out.println(i);
    }
}

class Fizz extends Worker {

    Fizz(int n, CyclicBarrier gate) {
        super(n, gate);
    }

    @Override
    protected boolean isResponsible(int i) {
        return i % 3 == 0 && i % 5 != 0;
    }

    @Override
    protected void print(int i) {
        System.out.println("Fizz");
    }
}

class Buzz extends Worker {

    Buzz(int n, CyclicBarrier gate) {
        super(n, gate);
    }

    @Override
    protected boolean isResponsible(int i) {
        return i % 5 == 0 && i % 3 != 0;
    }

    @Override
    protected void print(int i) {
        System.out.println("Buzz");
    }
}

class FizzBuzz extends Worker {
    FizzBuzz(int n, CyclicBarrier gate) {
        super(n, gate);
    }

    @Override
    protected boolean isResponsible(int i) {
        return i % 5 == 0 && i % 3 == 0;
    }

    @Override
    protected void print(int i) {
        System.out.println("FizzBuzz");
    }
}