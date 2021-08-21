/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.home.bean;

/**
 *
 * @author Lenovo
 */
public class TransactionQteItemBean {

    private String transQteItemKey;
    private String transKey;
    private String qQuoteNo;
    private String itemKey;
    private String partNo;
    private String barcode;
    private String quantity;
    private String quantityUnitKey;
    private String shipDate;
    private String price;
    private String currencyKey;
    private String multiplier;

    public String getTransQteItemKey() {
        return transQteItemKey;
    }

    public void setTransQteItemKey(String transQteItemKey) {
        this.transQteItemKey = transQteItemKey;
    }

    public String getTransKey() {
        return transKey;
    }

    public void setTransKey(String transKey) {
        this.transKey = transKey;
    }

    public String getqQuoteNo() {
        return qQuoteNo;
    }

    public void setqQuoteNo(String qQuoteNo) {
        this.qQuoteNo = qQuoteNo;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantityUnitKey() {
        return quantityUnitKey;
    }

    public void setQuantityUnitKey(String quantityUnitKey) {
        this.quantityUnitKey = quantityUnitKey;
    }

    public String getShipDate() {
        return shipDate.split(" ")[0];
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrencyKey() {
        return currencyKey;
    }

    public void setCurrencyKey(String currencyKey) {
        this.currencyKey = currencyKey;
    }

    public String getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(String multiplier) {
        this.multiplier = multiplier;
    }
}
