package ar.com.playmedia.model;

public class ProductM{
    private Integer intIdProduct;
    public Integer getIntIdProduct(){return this.intIdProduct;}
    public void setIntIdProduct(Integer intIdProduct){this.intIdProduct = intIdProduct;}

    private String strName;
    public String getStrName(){return this.strName;}
    public void setStrName(String strName){this.strName = strName;}

    private Float floPrice;
    public Float getFloPrice(){return this.floPrice;}
    public void setFloPrice(Float floPrice){this.floPrice = floPrice;}

    private Integer intStock;
    public Integer getIntStock(){return this.intStock;}
    public void setIntStock(Integer intStock){this.intStock = intStock;}

    private String strCode;
    public String getStrCode(){return this.strCode;}
    public void setStrCode(String strCode){this.strCode = strCode;}

    public String toString(){
        return String.format("%d\t\t%s\t\t%f\t\t%d\t\t%s", this.intIdProduct, this.strName, this.floPrice, this.intStock.toString(), this.strCode);
    }

    public String toTicketString(){
        return String.format("%d\t%s\t\t%f", this.intStock, this.strName, this.floPrice);
    }
}