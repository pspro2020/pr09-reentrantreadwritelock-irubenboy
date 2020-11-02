package classes;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    List<Product> products = new ArrayList<>();
    public Warehouse(List<Product> products){
        this.products = products;
    }
}
