package ar.com.playmedia.model;

import java.text.DecimalFormat;

public class ProductM {
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
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String result = "";
        String id = String.valueOf(this.intIdProduct);
        String name = this.strName;
        String price = decimalFormat.format(this.floPrice);
        String code = this.strCode;
        String stock = String.valueOf(this.intStock);

        int aux = id.length();
        for(int i = aux;i<6;i++)
            id = id + " ";
                
        aux = name.length();
        for(int i = aux;i<35;i++)
            name = name + " ";
        
        aux = price.length();
        for(int i = aux;i<8;i++)
            price = price + " ";
            
        aux = code.length();
        for(int i = aux;i<15;i++)
            code = code + " ";
            
        aux = stock.length();
        for(int i = aux;i<5;i++)
            stock = stock + " ";

        //System.out.println("id 6|nombre 35|precio 8|codigo 15|stock 5");
        result = String.format("%s%s%s%s%s", id, name, price, code, stock);
        return result;
    }

    public String toTicketString(){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String result = "";
        String product = String.format("%s (%s)", String.valueOf(this.intIdProduct), String.valueOf(this.intStock));
        String price = decimalFormat.format(this.floPrice * this.intStock);

        int aux = product.length();
        for(int i = aux;i<45;i++)
            product = product + " ";
                
        aux = price.length();
        for(int i = aux;i<10;i++)
            price = " " + price;
            
        //System.out.println("producto 45|precio 10");
        result = String.format("%s%s", product, price);
        return result;
    }
}