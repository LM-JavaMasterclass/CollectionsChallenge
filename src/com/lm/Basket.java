package com.lm;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        this.list = new HashMap<>();
    }

    public boolean addToBasket(StockItem item, int quantity) {
        if ((item != null) && (quantity > 0)) {
            int inBasket = list.getOrDefault(item, 0);
//            System.out.println("Found " + inBasket + " items in basket");
            // AddItemToBasket already checked that there was enough stock
            // and changed quantities in item in stock
            list.put(item, inBasket + quantity);

            return true;
        }
        return false;
    }

    public boolean removeFromBasket(StockItem item, int quantity) {
        if ((item != null) && (quantity > 0)) {
            int inBasket = list.getOrDefault(item, 0);
            // Check if there are enough items to unreserve:
            if (inBasket >= quantity) {
                list.put(item, inBasket - quantity); // check enough on basket to unreserve
//                System.out.println(item.getName() + " - new quantity in basket: " + list.get(item));
                item.reserveStock(-quantity);
                return true;
            }
            System.out.println("Cannot remove more items than currently present in basket: " + list.get(item));
            return false;
        }
        System.out.println("Enter valid item / quantity to remove from basket");
        return false;
    }

    public void checkOut() {
        System.out.println("\nShopping basket " + name + " checked out " +
                list.size() + ((list.size() == 1) ? " item" : " items") + "\n");

        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
            System.out.println(item.getKey() + " (" + item.getValue() + ") : $" + item.getKey().getPrice());
            totalCost += item.getKey().getPrice() * item.getValue();
            item.getKey().adjustStock(-item.getValue()); // TB creates a method that is sellStock (and includes these)
            removeFromBasket(item.getKey(), item.getValue());
        }
        System.out.println("Total cost: $ " + String.format("%.2f", totalCost));
        System.out.println("================================");
        this.list.clear();
    }

    public Map<StockItem, Integer> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " +
                list.size() + ((list.size() == 1) ? " item" : " items") + "\n";

        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
            s = s + item.getKey() + ". " + item.getValue() + " reserved\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + String.format("%.2f", totalCost);
    }
}
