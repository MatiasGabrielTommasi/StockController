package ar.com.playmedia.view;

import java.util.ArrayList;
import java.util.Scanner;

import ar.com.playmedia.utilities.*;
import ar.com.playmedia.model.UserM;
import ar.com.playmedia.controller.UserC;

public class UserV{

    public void userMenu(){
        Scanner keyboard = new Scanner(System.in);
        
        ArrayList <UserM> users = new ArrayList<UserM>();

        int option = -1;
        while(option != 0){
            Utilities.clearConsole();
            System.out.println("* Menu de usuarios *");
            System.out.println();
            System.out.println("1-Registrar nuevo usuario.");
            System.out.println("2-Buscar usuario.");
            System.out.println("3-Actualizar usuario.");
            System.out.println("4-Borrar usuario.");
            System.out.println("5-Mostrar lista de usuarios.");
            System.out.println();
            System.out.println("0-Salir.");

            option = Integer.parseInt(keyboard.nextLine());

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
        Scanner keyboard = new Scanner(System.in);
        UserM model = new UserM();

        System.out.print("Ingrese su nombre de usuario: ");
        String userName = keyboard.nextLine();
        
        System.out.print("Ingrese su contraseña: ");
        String password = keyboard.nextLine();

        model.setUser(userName);
        model.setPassword(password);

        UserC controller = new UserC();
        controller.connect();
        model = controller.login(model);
        controller.disconnect();

        return model;
    }

    public void signUp(){
        Scanner keyboard = new Scanner(System.in);
        Utilities.clearConsole();
        
        System.out.print("Ingrese dni: ");
        int dni = Integer.parseInt(keyboard.nextLine());

        System.out.print("Ingrese su nombre: ");
        String name = keyboard.nextLine();

        System.out.print("Ingrese apellido: ");
        String surname = keyboard.nextLine();

        System.out.print("Ingrese telefono: ");
        String phone = keyboard.nextLine();

        System.out.print("Ingrese su nombre de usuario: ");
        String username = keyboard.nextLine();

        System.out.print("Ingrese contraseña: ");
        String password = keyboard.nextLine();

    
        UserC controller = new UserC();
        controller.connect();
        controller.signUp(dni, name, surname, phone, username, password);
        controller.disconnect();
        System.out.println("Usuario generado exitosamente");
        keyboard.nextLine();
    }

    private void updateUser(){
        Utilities.clearConsole();
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Escriba el dni del usuario a actualizar ---> ");
        int id = Integer.parseInt(keyboard.nextLine());
        UserM model = new UserM(id, "", "", "", "", "");

        System.out.println("Que dato quiere actualizar?");
        System.out.println("1 - Nombre");
        System.out.println("2 - Apellido");
        System.out.println("3 - Telefono");
        System.out.println("4 - Usuario");
        System.out.println("5 - Contraseña");

        Integer selection = Integer.valueOf(keyboard.nextLine());

        System.out.println("Ingrese el valor:");
        String input = keyboard.nextLine();

        UserC controller = new  UserC();
        controller.connect();
        switch (selection) {
            case 1:
                controller.setName(model, input);
                break;
            case 2:
                controller.setSurname(model, input);
                break;
            case 3:   
                controller.setPhone(model, input);                    
                break;
            case 4:
                controller.setUsername(model, input);
                break;
            case 5:
                controller.setPassword(model, input);
                break;
            default:
                System.out.println("No selecciono ninguna accion, volviendo al menu anterior");
                break;
        }
        controller.disconnect();
    }

    private void deleteUser(){
        Utilities.clearConsole();
        
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Escriba el dni del usuario a eliminar ---> ");
        int id = Integer.parseInt(keyboard.nextLine());

        UserC controller = new UserC();
        controller.connect();
        controller.deleteUser(id);
        controller.disconnect();
    }

    private void listUsers(){
        Utilities.clearConsole();
        Scanner keyboard = new Scanner(System.in);

        ArrayList<UserM> users = new ArrayList<UserM>();

        UserC controller = new UserC();
        controller.connect();
        users = controller.listUsers();
        controller.disconnect();
        
        for(UserM user : users){
            System.out.println(user.toString());
        }
        keyboard.nextLine();
    }
    private void searchUsers(){
        Utilities.clearConsole();
        Scanner keyboard = new Scanner(System.in);

        ArrayList<UserM> users = new ArrayList<UserM>();

        UserM model = new UserM();
        System.out.println("Ingrese la informacion del usuario a buscar");
        System.out.println("(puede dejar campos en blanco)");
        System.out.println();

        System.out.println("Nombre del usuario:");
        model.setName(keyboard.nextLine());

        System.out.println("Apellido del usuario:");
        model.setSurname(keyboard.nextLine());

        System.out.println("DNI del usuario:");
        String personId = keyboard.nextLine();
        model.setPersonId((personId.equals("")) ? 0 : Integer.valueOf(personId));

        System.out.println("Usuario de usuario:");
        model.setUser(keyboard.nextLine());

        UserC usersC = new UserC();
        usersC.connect();
        users = usersC.searchUsers(model);
        usersC.disconnect();

        for(UserM user : users){
            System.out.println(user.toString());
        }
        keyboard.nextLine();
    }
}