package ar.com.playmedia;

import java.util.Scanner;
import ar.com.playmedia.model.UserM;
import ar.com.playmedia.utilities.*;
import ar.com.playmedia.view.ProductV;

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
            System.out.println();
            System.out.println("\t0 - Salir");

            try {
                intOption = Integer.valueOf(oScanner.nextLine());                
            } catch (Exception e) {
                intOption = -1;
            }

            switch (intOption) {
                case 1:
                    loginMenu(oScanner);
                    intOption = -1;
                    break;
            }
        }
        Utilities.clearConsole();
        System.out.println("Gracias por utilizar Stock Controller");
    }
    public static void loginMenu(Scanner oScanner){
        //TODO enlazar con el metodo login en el view de UsuarioV
        //debe devolver un objeto del tipo usuario completo si usuario y pass es corecto
        // o null si no se inicio sesion

        // UserV oUserV = new UserV(oScanner);
        // oUserM = oUserV.loginMenu();
        if(oUserM != null){
            System.out.println("Error al iniciar sesion, revisa usuario o contraseña.");
        }else{//TODO este es el menu para iniciar sesion
            Utilities.clearConsole();
            System.out.print("Usuario:  ");
            String strUser = oScanner.nextLine();
            System.out.println();
            System.out.print("Contraseña:  ");
            String strPass = oScanner.nextLine();
            //TODO llamar al controlador de user para obtener el usuario desde la base de datos
            oUserM = new UserM();//esto lo puse para continuar con la programacion
            mainMenu(oScanner);
        }
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
            System.out.println("\t4 - Bitacora");
            System.out.println();
            System.out.println("\t0 - Salir");
            try {
                intOption = Integer.valueOf(oScanner.nextLine());                
            } catch (Exception e) {
                intOption = -1;
            }

            switch (intOption) {
                case 1:
                    // TicketV oTocketV = new oTicketV(oUserM, oScanner);
                    // oTicketV.mainMenu();
                    intOption = -1;
                    break;
                case 2:
                    ProductV oProductV = new ProductV(oUserM, oScanner);
                    oProductV.mainMenu();
                    intOption = -1;
                    break;
                case 3:
                    // UsuarioV oUsuarioV = new UsuarioV(oUserM, oScanner);
                    // oUsuarioV.mainMenu();
                    intOption = -1;
                    break;
                case 4:
                    // BitacoraV oBitacoraV = new BitacoraV(oUserM, oScanner);
                    // oBitacoraV.mainMenu();
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
