package ar.com.playmedia.view;

import java.util.ArrayList;
import java.util.Scanner;
import ar.com.playmedia.controller.ProductC;
import ar.com.playmedia.model.ProductM;
import ar.com.playmedia.model.UserM;
import ar.com.playmedia.utilities.*;

public class ProductV {
    Scanner oScanner;
    ProductC oProductC;
    UserM oUserM;

    public ProductV(UserM oUserM, Scanner oScanner){
        this.oScanner = oScanner;
        oProductC = new ProductC();
        this.oUserM = oUserM;
    }
    
    public void mainMenu(){
        Integer intOption = -1;
        while (intOption != 0) {
            Utilities.clearConsole();
            System.out.println("Menu de productos");
            System.out.println("*************************************\r\n");
            System.out.println("\t1 - Buscar productos");
            System.out.println("\t2 - Agregar nuevo producto");
            System.out.println("\t3 - Actualizar un producto");
            System.out.println("\t4 - Eliminar un producto");
            System.out.println("\t5 - Mostrar todos");
            System.out.println("\t6 - Ver faltantes (productos con un stock inferior a 5 unidades)");
            System.out.println();
            System.out.println("\t0 - Volver al menu principal");

            intOption = oScanner.nextInt();

            switch (intOption) {
                case 1:
                    searchProduct(1);
                    intOption = -1;
                    break;
                case 2:
                    addProduct();
                    intOption = -1;
                    break;
                case 3:
                    updateProduct();
                    intOption = -1;
                    break;
                case 4:
                    deleteProduct();
                    intOption = -1;
                    break;
                case 5:
                    searchProduct(2);
                    intOption = -1;
                    break;
                case 6:
                    searchProduct(3);
                    intOption = -1;
                    break;
            }
        }        
    }
	
	private void searchProduct(Integer intSearchOption){
        Utilities.clearConsole();
        ProductM oProductM = new ProductM();
        switch (intSearchOption) {
            case 1://buscar
                oProductM = getProductParameter(true);
                break;
            case 2://listar todos
                oProductM.setIntStock(-1);
                break;
            case 3://listar faltantes
                oProductM.setIntStock(5);
                break;
        }

        ArrayList<ProductM> iProducts = new ArrayList<ProductM>();
        oProductC.connect();
        iProducts = oProductC.listProduct(oProductM);
        oProductC.disconnect();

        if(iProducts.size() == 0){
            System.out.println("No se encontraron productos");
        }else{
            System.out.println("ID\t\tNOMBRE\t\tPRECIO\t\tSTOCK\t\tCODIGO");
            for(ProductM oProduct : iProducts){
                System.out.println(oProduct.toString());
            }
        }
    }
    
    private ProductM getProductParameter(Boolean bitBusqueda){
        ProductM oResult = new ProductM();
        String strStock = "Stock del producto";
        if(bitBusqueda){
            strStock = "Stock minimo del producto:";
            System.out.println("Ingrese los parametros de busqueda:");
            System.out.println("(para omitirlos deje el espacio en blanco)");

            System.out.println("Id de producto:");
            String intIdProducto = oScanner.nextLine();
            oResult.setIntIdProduct((intIdProducto.equals("")) ? 0 : Integer.valueOf(intIdProducto));
        }else{
            System.out.println("Ingrese la informacion del producto");
            System.out.println();
        }

        System.out.println("nombre del producto:");
        oResult.setStrName(oScanner.nextLine());

        System.out.println("Codigo del producto:");
        oResult.setStrCode(oScanner.nextLine());

        System.out.println(strStock);
        String intExistencia = oScanner.nextLine();
        oResult.setIntIdProduct((intExistencia.equals("")) ? -1 : Integer.valueOf(intExistencia));

        return oResult;
    }
	
	private void addProduct(){
        Utilities.clearConsole();
        ProductM oProduct = new ProductM();
        oProduct = getProductParameter(true);
	}
	
	private void updateProduct(){
        Utilities.clearConsole();

	}
	
	private void deleteProduct(){
        Utilities.clearConsole();

	}
}