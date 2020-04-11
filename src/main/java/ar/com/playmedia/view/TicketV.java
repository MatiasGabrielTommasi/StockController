package ar.com.playmedia.view;

import ar.com.playmedia.controller.ProductC;
import ar.com.playmedia.controller.TicketC;
import ar.com.playmedia.model.ProductM;
import ar.com.playmedia.model.TicketM;
import ar.com.playmedia.model.UserM;
import ar.com.playmedia.utilities.Utilities;

import java.util.ArrayList;
import java.util.Scanner;

public class TicketV {
    UserM oUserM = null;
    Scanner oScanner = null;

	public TicketV(UserM oUserM, Scanner oScanner) {
        this.oScanner = oScanner;
        this.oUserM = oUserM;
	}

    public void mainMenu(){
        Integer intOption = -1;
        while (intOption != 0) {
            Utilities.clearConsole();
            System.out.println("Menu de Ventas");
            System.out.println("*************************************\r\n");
            System.out.println("\t1 - Vender");
            System.out.println("\t2 - Extracto semanal");
            // System.out.println("\t3 - Extracto semanal");
            // System.out.println("\t4 - Extracto mensual");
            // System.out.println("\t5 - Mostrar todas las ventas");
            System.out.println();
            System.out.println("\t0 - Volver al menu principal");

            try {
                intOption = Integer.valueOf(oScanner.nextLine());                
            } catch (Exception e) {
                intOption = -1;
            }

            switch (intOption) {
                case 1:
                    sell();
                    intOption = -1;
                    break;
                case 2:
                    getWeekTickets();
                    intOption = -1;
                    break;
            }
        }        
    }

    private void getWeekTickets() {
        try {
            TicketC oTicketC = new TicketC();
            oTicketC.connect();
            ArrayList<TicketM> iTickets = new ArrayList<TicketM>();
            iTickets = oTicketC.getWeekTickets();
            oTicketC.disconnect();

            for(TicketM ticket : iTickets){
                System.out.println(ticket.toReportString());
            }
            oScanner.nextLine();
        } catch (Exception e) {
            
        }
    }

    private void sell() {
        Utilities.clearConsole();
        TicketM oTicket = new TicketM();
        oTicket.setUser(oUserM);

        Boolean bitAdd = true;

        while(bitAdd){
            Utilities.clearConsole();
            System.out.println("Escanee o ingrese codigo de producto");
            String code = oScanner.nextLine();

            Boolean enableAdd = true;
            for(ProductM product : oTicket.getProducts()){
                if(product.getStrCode().equals(code)){
                    product.setIntStock(product.getIntStock() + 1);
                    enableAdd = false;
                    break;
                }else{
                    enableAdd = true;
                }
            }

            if(enableAdd){
                ProductC oProductC = new ProductC();
                oProductC.connect();
                ProductM oProduct = oProductC.listProduct(new ProductM(0, "", 0f, code, 0)).get(0);
                oProduct.setIntStock(1);
                oTicket.getProducts().add(oProduct);
                oProductC.disconnect();
            }


            Utilities.clearConsole();
            System.out.println(oTicket.toDetailString());
            System.out.println("Agregar otro producto (1 si - 0 no)");
            String answer = oScanner.nextLine();
            if(Integer.valueOf(answer) == 0)
                bitAdd = false;
        }

        TicketC oTicketC = new TicketC();
        oTicketC.connect();
        oTicketC.saveTicket(oTicket);
        oTicketC.disconnect();

        System.out.println("Se registro la venta exitosamente");
        oScanner.nextLine();
    }
}