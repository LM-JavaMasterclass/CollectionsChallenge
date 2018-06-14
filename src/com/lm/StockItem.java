package com.lm;


public class StockItem implements Comparable<StockItem>{
    private final String name;
    private double price;
    private int quantityStock;
    private int quantityReserved;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0;
        this.quantityReserved = 0;
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

   
    public int quantityStock() {

        return quantityStock;
    }

    public int availableStock() {
        return quantityStock - quantityReserved;
    }

    public int quantityReserved() {
        return quantityReserved;
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantityStock + quantity;
        if (newQuantity>=0){
            this.quantityStock = newQuantity;
        }
    }

    public boolean reserveStock(int quantity) {
        if (quantity <= (this.availableStock())) {
            this.quantityReserved += quantity;
            return true;
        }

        System.out.println("Could not reserve items");
        return false;
    }



    public void clearReserve() {
        this.quantityReserved = 0;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("entering StockItem.equals");
        if(obj==this){
            return true;
        }
        if ((obj==null) ||(obj.getClass()!=this.getClass())){
            return false;
        }

        String objName = ((StockItem)obj).getName();
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    @Override
    public int compareTo(StockItem o) {
        System.out.println("Entering StockItem.compareTo");
        if (this == o) {
            return 0;
        }

        if (o!=null) {
            return this.name.compareTo(o.getName());
        }

        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + " : price " + this.price;
    }
}
