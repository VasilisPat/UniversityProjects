package ordsys;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Order{
    private String appId;
    private String orderId;
    private Date orderDate;
    private String clientName;
    private String itemName;
    private Integer unitCount;
    private Double netItemPrice;
    private Double taxPercentage;

    public Order(){
        this.appId = "18390166";
        this.orderId = "";
        this.orderDate = null;
        this.clientName = "";
        this.itemName = "";
        this.unitCount = 0;
        this.netItemPrice = 0.00;
        this.taxPercentage = 0.00;
    }

    public Order(String orderId, Date orderDate, String clientName, String itemName, Integer unitCounts, Double netPrice, Double taxPercentage) {
        this.appId = "18390166";
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.clientName = clientName;
        this.itemName = itemName;
        this.unitCount = unitCounts;
        this.netItemPrice = netPrice;
        this.taxPercentage = taxPercentage;
    }

    @Override
    //Specifically for export file
    public String toString(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        return  appId + ";" + orderId + ";" + dateFormat.format(orderDate) + ";" + clientName + ";" + itemName + ";" + unitCount + ";" + netItemPrice + ";" + taxPercentage + "\n";
    }

    //Specifically for printing tabs and symbols to screen
    public String toScreen(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        return  orderId + "\t" + dateFormat.format(orderDate) + " \t" + clientName + "\t" + itemName + "\t" + unitCount + "\t" + netItemPrice + "$" + "\t" + taxPercentage + "%";
    }

    //Class Order Getters
    public String getAppId(){return appId;}

    public String getOrderId(){return orderId;}

    public Date getOrderDate(){return orderDate;}

    public String getClientName(){return clientName;}

    public String getItemName(){return itemName;}

    public Integer getUnitCount(){return unitCount;}

    public Double getNetItemPrice(){return netItemPrice;}

    public Double getTaxPercentage(){return taxPercentage;}

    //Class Order Setters
    public void setAppId(String appId){this.appId = appId;}

    public void setOrderId(String orderId){this.orderId = orderId;}

    public void setOrderDate(Date orderDate){this.orderDate = orderDate;}

    public void setClientName(String clientName){this.clientName = clientName;}

    public void setItemName(String itemName){this.itemName = itemName;}

    public void setUnitCount(Integer unitCount){this.unitCount = unitCount;}

    public void setNetItemPrice(Double netItemPrice){this.netItemPrice = netItemPrice;}

    public void setTaxPercentage(Double taxPercentage){this.taxPercentage = taxPercentage;}

}