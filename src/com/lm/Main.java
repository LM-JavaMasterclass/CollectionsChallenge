package com.lm;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        // We are entering them alphabetically. Since it is a LinkedHashMap, it stores them in order
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);
        temp = new StockItem("cup", 0.45, 7); // testing adding item
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);


        Basket timsbasket = new Basket("Tim");
        addItemToBasket(timsbasket, "car", 1);
        System.out.println(timsbasket);

        addItemToBasket(timsbasket, "car", 1);
        System.out.println(timsbasket);

        addItemToBasket(timsbasket, "car", 1); // shouldn't be able to, not enough stock
        addItemToBasket(timsbasket, "spanner", 1); // shouldn't find it as not in stock
        System.out.println(timsbasket);

        addItemToBasket(timsbasket, "juice", 4);
        addItemToBasket(timsbasket, "cup", 12);
        addItemToBasket(timsbasket, "bread", 1);
        System.out.println(timsbasket);

        System.out.println(stockList);

        removeItemFromBasket(timsbasket, "cup",4);
        removeItemFromBasket(timsbasket, "bread",2);

        System.out.println(stockList);

        timsbasket.checkOut();

        System.out.println("New stock list:");
        System.out.println(stockList);
        System.out.println("=============\n\n");
        System.out.println("New Basket:");
        System.out.println(timsbasket);



//        for(Map.Entry<String, Double> price: stockList.PriceList().entrySet()){
//            System.out.println(price.getKey() + " costs " + price.getValue());
//        }




    }

    public static int addItemToBasket(Basket basket, String item, int quantity) {
        // retrieve item from stock list
        StockItem stockItem = stockList.get(item);

        if(stockItem==null){
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(stockList.reserveStock(item,quantity)!=0) { // This checks that there's enough stock,
                                        // AND COMPLETES RESERVE IN STOCK
            basket.addToBasket(stockItem, quantity);
            return quantity;
        }
        System.out.println("Not enough items in stock for " + item);
        return 0; // gets here if there is no sufficient stock
    }

    public static boolean  removeItemFromBasket(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        return basket.removeFromBasket(stockItem, quantity);
    }
}

