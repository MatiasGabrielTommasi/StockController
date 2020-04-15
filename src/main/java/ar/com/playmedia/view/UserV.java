package ar.com.playmedia.view;

import java.util.ArrayList;
import java.util.Scanner;

import ar.com.playmedia.utilities.*;
import ar.com.playmedia.model.UserM;
import ar.com.playmedia.controller.UserC;

public class UserV{
    Scanner oScanner = null;
    UserM oUserM = null;

    public UserV(Scanner oScanner){
        this.oScanner = oScanner;
    }
    
    public UserV(Scanner oScanner, UserM oUserM){
        this.oScanner = oScanner;
        this.oUserM = oUserM;
    }

    public void userMenu(){
        int option = -1;
        while(option != 0){
            Utilities.clearConsole();
            System.out.println("Menu de usuarios (Hola " + this.oUserM.getName() + ")");
            System.out.println("*************************************\r\n");
            System.out.println("\t1 - Registrar nuevo usuario.");
            System.out.println("\t2 - Buscar usuario.");
            System.out.println("\t3 - Actualizar usuario.");
            System.out.println("\t4 - Borrar usuario.");
            System.out.println("\t5 - Mostrar lista de usuarios.");
            System.out.println();
            System.out.println("\t0 - Salir.");

            option = Utilities.getNumeric(oScanner, 5); 

            switch(option){
                case 1:
                    signUp();
                    option = -1;
                    break;
                case 2:
                    searchUsers();
                    option = -1;
                    break;
                case 3: 
                    updateUser();
                    option = -1;
                    break;
                case 4:
                    deleteUser();
                    option = -1;
                case 5:
                    listUsers();
                    option = -1;
                    break;
            }
        }
    }

    public UserM logIn(){
        UserM model = new UserM();

        System.out.print("Ingrese su nombre de usuario: ");
        String userName = oScanner.nextLine();
        
        System.out.print("Ingrese su contraseña: ");
        String password = oScanner.nextLine();

        model.setUser(userName);
        model.setPassword(password);

        UserC controller = new UserC();
        controller.connect();
        model = controller.login(model);
        controller.disconnect();

        return model;
    }

    public void signUp(){
        Utilities.clearConsole();
        
        System.out.print("Ingrese dni: ");
        int dni = Utilities.getNumeric(oScanner);

        System.out.print("Ingrese su nombre: ");
        String name = oScanner.nextLine();

        System.out.print("Ingrese apellido: ");
        String surname = oScanner.nextLine();

        System.out.print("Ingrese telefono: ");
        String phone = oScanner.nextLine();

        System.out.print("Ingrese su nombre de usuario: ");
        String username = oScanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String password = oScanner.nextLine();

        Utilities.clearConsole();
        UserC controller = new UserC();
        controller.connect();
        try {
            ArrayList<UserM> exists = controller.searchUsers(new UserM(dni, "", "", "", "", ""));
            if(exists.size() == 0){
                controller.signUp(dni, name, surname, phone, username, password);
                System.out.println("Usuario generado exitosamente");
            }
            else{
                System.out.println("El usuario ya se encuentra registrado");
            }            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        controller.disconnect();
        oScanner.nextLine();
    }

    private void updateUser(){
        UserM model = new UserM();
        UserC oUserC = new UserC();
        ArrayList<UserM> iUsers = new ArrayList<UserM>();
        while(iUsers.size() == 0){
            Utilities.clearConsole();
            System.out.print("Ingrese el dni del usuario:");
            int id = Integer.parseInt(oScanner.nextLine());
            model = new UserM(id, "", "", "", "", "");
            oUserC.connect();
            iUsers =  oUserC.searchUsers(model);
            oUserC.disconnect();
            if(iUsers.size() == 0){
                System.out.println("Usuario no encontrado");
                oScanner.nextLine();
                return;
            }
        }

        model = iUsers.get(0);

        System.out.println("Que dato quiere actualizar?");
        System.out.println("\t0 - Nombre");
        System.out.println("\t1 - Apellido");
        System.out.println("\t2 - Telefono");
        System.out.println("\t3 - Usuario");
        System.out.println("\t4 - Contraseña");

        Integer selection = Utilities.getNumeric(oScanner, 4);
        switch (selection) {
            case 0:                
                System.out.println("Ingrese el Nombre (" + model.getName() + "):");
                break;                
            case 1:
                System.out.println("Ingrese el Apellido (" + model.getSurname() + "):");
                break;            
            case 2:
                System.out.println("Ingrese el Telefono (" + model.getPhone() + "):");
                break;
            case 3:
                System.out.println("Ingrese el Usuario (" + model.getUser() + "):");
                break;
            case 4:
                System.out.println("Ingrese el Contraseña:");
                break;
        }

        String input = oScanner.nextLine();

        UserC controller = new  UserC();
        controller.connect();
        switch (selection) {
            case 0:
                controller.setName(model, input);
                break;
            case 1:
                controller.setSurname(model, input);
                break;
            case 2:   
                controller.setPhone(model, input);                    
                break;
            case 3:
                controller.setUsername(model, input);
                break;
            case 4:
                controller.setPassword(model, input);
                break;
        }
        controller.disconnect();
    }

    private void deleteUser(){
        Utilities.clearConsole();
        
        System.out.println("Escriba el dni del usuario a eliminar:");
        int id = Utilities.getNumeric(oScanner);
        Utilities.clearConsole();
        System.out.println("Esta seguro de eliminar el usuario?");
        if(Utilities.getYesNo(oScanner)){
            UserC controller = new UserC();
            controller.connect();
            
            try {
                ArrayList<UserM> exists = controller.searchUsers(new UserM(id, "", "", "", "", ""));
                if(exists.size() > 0){
                    controller.deleteUser(id);
                    Utilities.clearConsole();
                }
                else{
                    System.out.println("El usuario ya no se encuentra registrado");
                }            
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            controller.disconnect();
        }
        oScanner.nextLine();
}

    private void listUsers(){
        Utilities.clearConsole();

        ArrayList<UserM> users = new ArrayList<UserM>();

        UserC controller = new UserC();
        controller.connect();
        users = controller.listUsers();
        controller.disconnect();
        
        System.out.println("DNI         Apellido                 Nombres             Usuario             Telefono       ");        
        System.out.println("===         ========                 =======             =======             ========       ");
        System.out.println();
        for(UserM user : users){
            System.out.println(user.toString());
        }
        oScanner.nextLine();
    }
    private void searchUsers(){
        Utilities.clearConsole();

        ArrayList<UserM> users = new ArrayList<UserM>();

        UserM model = new UserM();
        System.out.println("Ingrese la informacion del usuario a buscar");
        System.out.println("(puede dejar campos en blanco)");
        System.out.println();

        System.out.println("Nombre:");
        model.setName(oScanner.nextLine());

        System.out.println("Apellido:");
        model.setSurname(oScanner.nextLine());

        System.out.println("DNI:");
        String personId = oScanner.nextLine();
        model.setPersonId((personId.equals("")) ? 0 : Integer.valueOf(personId));

        System.out.println("Nombre de usuario:");
        model.setUser(oScanner.nextLine());

        UserC usersC = new UserC();
        usersC.connect();
        users = usersC.searchUsers(model);
        usersC.disconnect();

        Utilities.clearConsole();
        System.out.println("DNI         Apellido                 Nombres             Usuario             Telefono       ");        
        System.out.println("===         ========                 =======             =======             ========       ");
        System.out.println();
        for(UserM user : users){
            System.out.println(user.toString());
        }
        oScanner.nextLine();
    }
}