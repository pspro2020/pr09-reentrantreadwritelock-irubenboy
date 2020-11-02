package classes;

import java.util.Objects;

public class Consulter implements Runnable{

    private Product product;
    public Consulter(Product product){
        Objects.requireNonNull(product);
        this.product = product;
    }

    @Override
    public void run() {
        try {
            product.consultStock();
        } catch (InterruptedException e) {
            System.err.println("I've been interrupted while consulting the stock");
        }
    }
}
