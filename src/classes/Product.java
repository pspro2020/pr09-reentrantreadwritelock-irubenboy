package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Product {

    private final int id;
    private static final List<Integer> stock = new ArrayList<>();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Product(int id){
        if(id <= 0) throw new IllegalArgumentException("Non validate arguments");
        this.id = id;
        stock.add(id-1,0);
        stock.set(id-1, stock.get(id-1)+1);
    }

    public void consultStock() throws InterruptedException {
        readLock.lock();
        try {
            getStock();
        } finally {
            readLock.unlock();
        }
    }

    public void getStock() throws InterruptedException {
        System.out.printf("%s -> %s consulting stock...\n", LocalDateTime.now().format(f),
                Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(5);
        System.out.printf("%s -> %s There %s %d stocks of Product #%d\n", LocalDateTime.now().format(f),
                Thread.currentThread().getName(), stock.get(id-1) > 1 ? "are" : "is", stock.get(id-1), id);
    }

    public void updateStock() throws InterruptedException {
        writeLock.lock();
        try {
            incrementStock();
        } finally {
            writeLock.unlock();
        }
    }

    private void incrementStock() throws InterruptedException {
        System.out.printf("%s -> %s is updating stock...\n", LocalDateTime.now().format(f),
                Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(3);
        stock.set(id-1, stock.get(id-1)+1);
        System.out.printf("%s -> %s New Stock: %d\n",
                LocalDateTime.now().format(f),
                Thread.currentThread().getName(),
                stock.get(id-1));
    }
}
