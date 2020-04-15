package ar.com.playmedia;

import java.util.Scanner;
import ar.com.playmedia.model.UserM;
import ar.com.playmedia.utilities.*;
import ar.com.playmedia.view.ProductV;
import ar.com.playmedia.view.TicketV;
import ar.com.playmedia.view.UserV;

public class App
{
    static UserM oUserM = null;
    static Scanner oScanner = null;

    public static void main(String[] args) {
        oScanner = new Scanner(System.in);

        Integer intOption = -1;
        while (intOption != 0) {
            Utilities.clearConsole();
            System.out.println("Bienvenido a Stock Controller");
            System.out.println("*************************************\r\n");
            System.out.println("\t1 - Iniciar Sesion");
            System.out.println("\t2 - Registrarse");
            System.out.println();
            System.out.println("\t0 - Salir");

            try {
                intOption = Utilities.getNumeric(oScanner, 2);               
            } catch (Exception e) {
                intOption = -1;
            }

            UserV view = new UserV(oScanner);

            switch (intOption) {
                case 1:
                    oUserM = view.logIn();
                    if(oUserM == null){
                        Utilities.clearConsole();
                        System.out.println("Error al iniciar sesion, revisa usuario o contraseña.");
                        oScanner.nextLine();
                    }else{
                        mainMenu(oScanner);
                    }
                    intOption = -1;
                    break;
                case 2:
                    view.signUp();
                    intOption = -1;
            }
        }
        Utilities.clearConsole();
        System.out.println("Gracias por utilizar Stock Controller");
    }
    public static void mainMenu(Scanner oScanner){
        Integer intOption = -1;
        while (intOption != 0) {
            if(oUserM == null)
                break;
            //usuarios
            //productos
            //ventas
            //bitacora
            Utilities.clearConsole();
            System.out.println("Menu principal");
            System.out.println("*************************************\r\n");
            System.out.println("\t1 - Ventas");
            System.out.println("\t2 - Productos");
            System.out.println("\t3 - Usuarios");
            //System.out.println("\t4 - Bitacora");
            System.out.println();
            System.out.println("\t0 - Salir");
            try {
                intOption = Utilities.getNumeric(oScanner, 3);                 
            } catch (Exception e) {
                intOption = -1;
            }

            switch (intOption) {
                case 1:
                    TicketV oTicketV = new TicketV(oUserM, oScanner);
                    oTicketV.mainMenu();
                    intOption = -1;
                    break;
                case 2:
                    ProductV oProductV = new ProductV(oUserM, oScanner);
                    oProductV.mainMenu();
                    intOption = -1;
                    break;
                case 3:
                    UserV oUserV = new UserV(oScanner, oUserM);
                    oUserV.userMenu();
                    intOption = -1;
                    break;
                case 0:
                    oUserM = null;
                    break;
            }
        }
        Utilities.clearConsole();
        System.out.println("Gracias por utilizar Stock Controller.");
        oScanner.nextLine();
    }
}
