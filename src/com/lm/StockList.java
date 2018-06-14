package com.lm;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock (StockItem item) {
        if (item!=null) {
            StockItem inStock = list.getOrDefault(item.getName(), item);

            if(inStock!=item) {  // If not, it means it already was in the Map
                item.adjustStock(inStock.quantityStock()); // adjusts existing quantity with new quantity
            }

            list.put(item.getName(), item); // if already exists, it will update, else it will add to Map
            return item.quantityStock();
        }
        return 0; // if it was null
    }

    public int reserveStock(String item, int quantity) {
        // check if it is in stock, if not make null
        StockItem inStock = list.getOrDefault(item, null);

        if((inStock!=null) && (inStock.availableStock() >= quantity) && (quantity>0)){
//            System.out.println("Quantity stock after reserve: " + inStock.quantityStock());
            inStock.reserveStock(quantity);
//            System.out.println("Quantity reserve after reserve: " + inStock.quantityReserved());
            return quantity;
        }

        return 0; // couldn't do transaction
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, Double> PriceList() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for(Map.Entry<String,StockItem> item : list.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    public Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = ("\nStock List\n");
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item: list.entrySet()) {
            StockItem stockItem = item.getValue();

            double stockValue = stockItem.getPrice() * stockItem.quantityStock();
            double reserveValue = stockItem.getPrice() * stockItem.quantityReserved();
            s = s + stockItem + ". There are " + stockItem.quantityReserved() + " reserved, " + stockItem.quantityStock() + " in stock. \n\tValue of items reserved: $";
            s = s + String.format("%.2f", reserveValue) + ". Value of items in stock: $";
            s = s + String.format("%.2f", stockValue) + "\n";
            totalCost+= stockValue;
        }

        return s + "Total value of stock is: $" + String.format("%.2f", totalCost);
    }
}
