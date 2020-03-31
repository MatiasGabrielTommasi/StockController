package ar.com.playmedia.model;

public class ProductM{
    private Integer intIdProduct;
    private String strName;
    private Float floPrice;
    private String strCode;
    private Integer intStock;

    public Integer getIntIdProduct(){return this.intIdProduct;}
    public void setIntIdProduct(Integer intIdProduct){this.intIdProduct = intIdProduct;}

    public String getStrName(){return this.strName;}
    public void setStrName(String strName){this.strName = strName;}

    public Float getFloPrice(){return this.floPrice;}
    public void setFloPrice(Float floPrice){this.floPrice = floPrice;}

    public String getStrCode(){return this.strCode;}
    public void setStrCode(String strCode){this.strCode = strCode;}

    public Integer getIntStock(){return this.intStock;}
    public void setIntStock(Integer intStock){this.intStock = intStock;}

    public ProductM(){
        String _vacio = "";
        Integer _cero = 0;

        this.intIdProduct = _cero;
        this.strName = _vacio;
        this.floPrice = Float.valueOf(_cero);
        this.strCode = _vacio;
        this.intStock = _cero;
    }

    public ProductM(Integer intIdProduct, String strName, Float floPrice, String strCode, Integer intStock){
        this.intIdProduct = intIdProduct;
        this.strName = strName;
        this.floPrice = floPrice;
        this.strCode = strCode;
        this.intStock = intStock;
    }

    public String toString(){
        return String.format("%d\t\t%s\t\t%f\t\t%d\t\t%s", this.intIdProduct, this.strName, this.floPrice, this.intStock.toString(), this.strCode);
    }

    public String toTicketString(){
        return String.format("%d\t%s\t\t%f", this.intStock, this.strName, this.floPrice);
    }
}