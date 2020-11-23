package com.tradingengine.client_connectivity.order;

public class Order {
    private String product;
    private int  quantity;
    private double price;
    private String side;

    public Order(String product, int quantity, double price, String side) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}


//package com.tradingengine.client_connectivity.order;
//
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Id;
//
//public @Data
//class Order {
//    @Id
//    private @Getter @Setter String orderId;
//    private @Getter @Setter String userId;
//    private @Getter @Setter int unitPrice;
//    private @Getter @Setter String tickerSymbol;
//    private @Getter @Setter int statusId;
//    private @Getter @Setter int quantity;
//    private @Getter @Setter int transactionId;
//    private @Getter @Setter String dateCreated;
//    private @Getter @Setter String dateModified;
//    private @Getter @Setter int orderTypeId;
//
//    public Order(String userId, int unitPrice, String tickerSymbol, int statusId, int quantity, int transactionId, String dateCreated, String dateModified, int orderTypeId) {
//        this.userId = userId;
//        this.unitPrice = unitPrice;
//        this.tickerSymbol = tickerSymbol;
//        this.statusId = statusId;
//        this.quantity = quantity;
//        this.transactionId = transactionId;
//        this.dateCreated = dateCreated;
//        this.dateModified = dateModified;
//        this.orderTypeId = orderTypeId;
//    }
//}
