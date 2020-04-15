package ar.com.playmedia.controller;

import java.sql.Statement;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import ar.com.playmedia.model.ProductM;

public class ProductC{
	private Connection dbConnection;
	private Statement query;
	private ResultSet result;
	private String server;
	private String username;
	private String pass;

	public ProductC() {
		server = "jdbc:postgresql://127.0.0.1:5432/stockcontroller";
		username = "dba";
		pass = "12345678";
    }
    
	public void connect() {	
		try {
			Class.forName("org.postgresql.Driver");
			dbConnection = DriverManager.getConnection(server, username, pass);
		} catch(Exception e) {
			System.out.println("ERROR: " + e);
		}
	}


	public void disconnect() {
		try {
			dbConnection.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
    public ArrayList<ProductM> listProduct(ProductM oProduct){
        ArrayList<ProductM> iProducts = new ArrayList<ProductM>();//id nombre stock codigo
        String queryString = String.format("SELECT * from products_list(%d, '%s', %d, '%s')"
            ,oProduct.getIntIdProduct()
            ,oProduct.getStrName()
            ,oProduct.getIntStock()
            ,oProduct.getStrCode()
        );

		try {
			query = dbConnection.createStatement();
			result = query.executeQuery(queryString);

			while(result.next()) {
                Integer intIdProducto = result.getInt(1);
                String strName = result.getString(2);
                Float floPrice = result.getFloat(3);
                Integer intStock = result.getInt(4);
                String strCode = result.getString(5);
				ProductM contact = new ProductM (intIdProducto, strName, floPrice, strCode, intStock);

				iProducts.add(contact);
			}

			query.close();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
        return iProducts;
    }
    
    public ArrayList<ProductM> listLowStockProduct(){
        ArrayList<ProductM> iProducts = new ArrayList<ProductM>();//id nombre stock codigo
        String queryString = "SELECT * from products_list_low_stock();";

		try {
			query = dbConnection.createStatement();
			result = query.executeQuery(queryString);

			while(result.next()) {
                Integer intIdProducto = result.getInt(1);
                String strName = result.getString(2);
                Float floPrice = result.getFloat(3);
                Integer intStock = result.getInt(4);
                String strCode = result.getString(5);
				ProductM contact = new ProductM (intIdProducto, strName, floPrice, strCode, intStock);

				iProducts.add(contact);
			}

			query.close();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
        return iProducts;
    }
    
    public void saveProduct(ProductM oProduct){
        String queryString = String.format("SELECT products_ins('%s', %s, %s, '%s')"
            ,oProduct.getStrName()
            ,oProduct.getFloPrice().toString().replace(",", ".")
            ,oProduct.getIntStock().toString()
            ,oProduct.getStrCode()
        );

		try {
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception e) {
            
        }
    }

    public void deleteProduct(ProductM oProduct) {
        String queryString = String.format("SELECT products_del(%d)"
            ,oProduct.getIntIdProduct()
        );

		try {
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception e) {            
            //throw new Exception(e.getMessage());
        }
    }

    public ProductM setStrName(ProductM oProduct, String strName){
		String queryString = String.format("SELECT * FROM products_set_product_name (%d, '%s');",
							oProduct.getIntIdProduct(), strName);
		try{
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception ex){

		}

		return listProduct(oProduct).get(0);
	}
	public ProductM setFloPrirce(ProductM oProduct, Float floPrirce){
		String queryString = String.format("SELECT * FROM products_set_price (%d, %s);",
							oProduct.getIntIdProduct(), String.valueOf(floPrirce));
		try{
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception ex){

		}

		return listProduct(oProduct).get(0);
	}
	public ProductM setStrCode(ProductM oProduct, String strCode){
		String queryString = String.format("SELECT * FROM products_set_code (%d, '%s');",
							oProduct.getIntIdProduct(), strCode);
		try{
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception ex){

		}

		return listProduct(oProduct).get(0);
	}
	public ProductM setIntStock(ProductM oProduct, Integer intStock){
		String queryString = String.format("SELECT * FROM products_set_stock (%d, %d);",
							oProduct.getIntIdProduct(), intStock);
		try{
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception ex){

		}

		return listProduct(oProduct).get(0);
	}
}