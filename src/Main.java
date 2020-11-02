import classes.Consulter;
import classes.Product;
import classes.Supermarket;
import classes.Warehouse;

import java.util.List;

public class Main {
    private final static int MAX_WAREHOUSE = 3;

    //1, 2, 1, 1, 2, 1, 3
    public static void main(String[] args) {
        List<Product> products = List.of(new Product(1), new Product(2), new Product(1),
                new Product(1), new Product(2), new Product(1), new Product(3));
        new Warehouse(products);
        Product[] p = {new Product(1), new Product(2), new Product(3)};
        Thread[] warehouses = new Thread[MAX_WAREHOUSE];
        for (int i = 0; i < MAX_WAREHOUSE; i++) {
            warehouses[i] = new Thread(new Consulter(p[i]), "Consulter" + (i+1));
        }

        Thread supermarketThread = new Thread(new Supermarket(), "Supermarket");
        supermarketThread.start();
        for (int i = 0; i < MAX_WAREHOUSE; i++) {
            warehouses[i].start();
        }

    }
}
