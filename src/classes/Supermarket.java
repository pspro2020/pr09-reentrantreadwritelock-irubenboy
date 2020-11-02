package classes;

import java.util.Objects;
import java.util.Random;

public class Supermarket implements Runnable{
    private Product p;
    private Random r = new Random();


    @Override
    public void run() {
       p = new Product(r.nextInt(3)+1);
        try {
            p.updateStock();
        } catch (InterruptedException e) {
            System.err.println("I've been interrupted while updating the stock");
        }
    }
}
