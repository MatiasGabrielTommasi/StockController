package ar.com.playmedia.view;

import java.text.DecimalFormat;
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
            System.out.println("Menu de productos (Hola " + this.oUserM.getName() + ")");
            System.out.println("*************************************\r\n");
            System.out.println("\t1 - Buscar productos");
            System.out.println("\t2 - Agregar nuevo producto");
            System.out.println("\t3 - Actualizar un producto");
            System.out.println("\t4 - Eliminar un producto");
            System.out.println("\t5 - Mostrar todos");
            System.out.println("\t6 - Ver faltantes (productos con un stock inferior a 5 unidades)");
            System.out.println();
            System.out.println("\t0 - Volver al menu principal");

            intOption = Utilities.getNumeric(oScanner, 6);

            switch (intOption) {
                case 1:
                System.out.println("(puede omitir campos dejando los vacios)");
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
                    LowStockProduct();
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
        }

        ArrayList<ProductM> iProducts = new ArrayList<ProductM>();
        oProductC.connect();
        iProducts = oProductC.listProduct(oProductM);
        oProductC.disconnect();

        Utilities.clearConsole();
        if(iProducts.size() == 0){
            System.out.println("No se encontraron productos");
        }else{
            System.out.println("ID    NOMBRE                             PRECIO  CODIGO         STOCK");
            System.out.println("==    ======                             ======  ======         =====");            
            System.out.println();
            for(ProductM oProduct : iProducts){
                System.out.println(oProduct.toString());
            }
        }
        oScanner.nextLine();
    }
    
    private ProductM getProductParameter(Boolean bitBusqueda){
        ProductM oResult = new ProductM();

        System.out.println("Ingrese la informacion del producto");
        System.out.println();

        System.out.println("nombre del producto:");
        oResult.setStrName(oScanner.nextLine());

        System.out.println("Codigo del producto:");
        oResult.setStrCode(oScanner.nextLine());


        if(bitBusqueda){
            System.out.println("Id de producto:");
            String intIdProducto = oScanner.nextLine();
            oResult.setIntIdProduct((intIdProducto.equals("")) ? 0 : Integer.valueOf(intIdProducto));
        }
        else{
            System.out.println("Stock del producto:");
            String intExistencia = oScanner.nextLine();
            oResult.setIntStock((intExistencia.equals("")) ? -1 : Integer.valueOf(intExistencia));

            System.out.println("Precio:");
            oResult.setFloPrice(Float.valueOf(oScanner.nextLine()));
        }

        return oResult;
    }
	
	private void addProduct(){
        Utilities.clearConsole();
        ProductM oProduct = new ProductM();
        oProduct = getProductParameter(false);

        try {
            ProductC oProductC = new ProductC();
            oProductC.connect();
            
            Utilities.clearConsole();
            try {
                ArrayList<ProductM> exists = oProductC.listProduct(new ProductM(0 , "", 0f, oProduct.getStrCode(), 0));
                if(exists.size() == 0){
                    oProductC.saveProduct(oProduct);
                    System.out.println("Se guardo el registro correctamente"); 
                }
                else{
                    System.out.println("El Producto ya existe");
                }            
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            oProductC.disconnect();           
        } catch (Exception e) {            
            System.out.println("Ocurrio un problema al guardar el registro");     
        }
        
        oScanner.nextLine();
	}
	
	private void updateProduct(){
        Utilities.clearConsole();

        ProductM oProduct = new ProductM();
        ProductC oProductC = new ProductC();
        ArrayList<ProductM> iProducts = new ArrayList<ProductM>();
        while(iProducts.size() == 0){
            Utilities.clearConsole();
            System.out.println("Ingrese en codigo del producto:");
            String strCode = oScanner.nextLine();
            oProduct = new ProductM(0, "", 0f, strCode, -1);
            oProductC.connect();
            iProducts =  oProductC.listProduct(oProduct);
            oProductC.disconnect();
            if(iProducts.size() == 0){
                System.out.println("Producto no encontrado");
                oScanner.nextLine();
                return;
            }
        }

        oProduct = iProducts.get(0);

        System.out.println("Que dato quiere actualizar?");
        System.out.println("0 - Nombre");
        System.out.println("1 - Precio");
        System.out.println("2 - Stock");
        System.out.println("3 - Codigo");

        Integer selection = Utilities.getNumeric(oScanner, 3);

        switch (selection) {
            case 0:                
                System.out.println("Ingrese el Nombre (" + oProduct.getStrName() + "):");
                break;                
            case 1:
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                System.out.println("Ingrese el Precio (" + decimalFormat.format(oProduct.getFloPrice()) + "):");
                break;            
            case 2:
                System.out.println("Ingrese el Stock (" + oProduct.getIntStock() + "):");
                break;
            case 3:
                System.out.println("Ingrese el Codigo (" + oProduct.getStrCode() + "):");
                break;
        }

        String input = oScanner.nextLine();

        oProductC.connect();
        switch (selection) {
            case 1:
                oProductC.setStrName(oProduct, input);
                break;
            case 2:
                oProductC.setFloPrirce(oProduct, Float.valueOf(input));
                break;
            case 3:   
                oProductC.setIntStock(oProduct, Integer.valueOf(input));                    
                break;
            case 4:
                oProductC.setStrCode(oProduct, input);
                break;
            default:
                System.out.println("No selecciono ninguna accion, volviendo al menu anterior");
                break;
        }
        oProductC.disconnect();
        oScanner.nextLine();
	}
	
	private void deleteProduct(){
        Utilities.clearConsole();
        System.out.println("Ingrese en codigo del producto que desea eliminar");
        Integer intIdProduct = Integer.valueOf(oScanner.nextLine());
        ProductM oProduct = new ProductM(intIdProduct, "", 0f, "", -1);
        try {
            ProductC oProductC = new ProductC();

            oProductC.connect();
            System.out.println("Esta seguro de eliminar el producto?");
            if(Utilities.getYesNo(oScanner)){                
                try {
                    ArrayList<ProductM> exists = oProductC.listProduct(new ProductM(0 , "", 0f, oProduct.getStrCode(), 0));
                    if(exists.size() > 0){
                        oProductC.deleteProduct(oProduct);
                        Utilities.clearConsole();
                    }
                    else{
                        System.out.println("El producto ya no se encuentra en existencia");
                    }            
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            oProductC.disconnect();
            System.out.println("El producto fue eliminado exitosamente");
            oScanner.nextLine();
        } catch (Exception e) {
            
        }
    }
    
	
	private void LowStockProduct(){
        Utilities.clearConsole();
        ArrayList<ProductM> iProducts = new ArrayList<ProductM>();
        oProductC.connect();
        iProducts = oProductC.listLowStockProduct();
        oProductC.disconnect();

        Utilities.clearConsole();
        if(iProducts.size() == 0){
            System.out.println("No se encontraron productos");
        }else{
            System.out.println("ID    NOMBRE                             PRECIO  CODIGO         STOCK");
            System.out.println("==    ======                             ======  ======         =====");            
            System.out.println();
            for(ProductM oProduct : iProducts){
                System.out.println(oProduct.toString());
            }
        }
        oScanner.nextLine();
    }
    
}