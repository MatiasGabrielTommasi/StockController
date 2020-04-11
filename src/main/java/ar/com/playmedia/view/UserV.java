package ar.com.playmedia.view;

import java.util.ArrayList;
import java.util.Scanner;

import ar.com.playmedia.Utilities.Utilities;
import ar.com.playmedia.model.UserM;
import ar.com.playmedia.controller.UserC;

public class UserV{

    public void userMenu(){
        Scanner keyboard = new Scanner(System.in);
        
        ArrayList <UserM> users = new ArrayList<UserM>();

        int option = -1;

        System.out.println(
            "* Menu de usuarios *\n\n" +

            "1-Iniciar sesión." +
            "2-Registrarse." + 
            "3-Buscar usuario." +
            "4-Actualizar usuario." + 
            "5-Borrar usuario." + 
            "6-Mostrar lista de usuarios." + 
            "0-Salir."            
        );

        option = Integer.parseInt(keyboard.nextLine());

        switch(option){
            case 1:
                logIn();
                break;
            case 2:
                signIn();
                break;
            case 3:
                searchingUser();
                break;
            case 4: 
                updateUser();
                break;
            case 5:
                deleteUser();
                break;
            case 6:
                listUsers();
                break;
            case 0:
                break;
            default:
                System.out.println("ERROR - Ingrese nuevamente.");
                break;
        }

    }

    public void logIn(){
        Scanner keyboard = new Scanner(System.in);
        UserM model = new UserM();

        System.out.print("Ingrese su nombre de usuario: ");
        String userName = keyboard.nextLine();
        
        System.out.print("Ingrese su contraseña: ");
        String password = keyboard.nextLine();

        
    }

    public void signIn(){

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

        System.out.println("Ingrese su nombre de usuario: ");
        String user = keyboard.nextLine();

        System.out.print("Ingrese id de usuario: ");
        int userId = Integer.parseInt(keyboard.nextLine());

        System.out.print("Ingrese contraseña: ");
        String password = keyboard.nextLine();

    
        UserC logInUser = new UserC();
        logInUser.signIn(dni, name, surname, phone, user, userId, password);


    }

    public void searchingUser(){
        Utilities.clearConsole();
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("Escriba el dni del usuario que busca ---> ");
        int id = Integer.parseInt(keyboard.nextLine());

        UserC userSearch = new UserC();
        userSearch.updateUser(id);
    }

    public void updateUser(){
        Utilities.clearConsole();
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Escriba el dni del usuario a actualizar ---> ");
        int id = Integer.parseInt(keyboard.nextLine());

        UserC userUpdate = new UserC();
        userUpdate.updateUser(id);
    }

    public void deleteUser(){
        Utilities.clearConsole();
        
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Escriba el dni del usuario a eliminar ---> ");
        int id = Integer.parseInt(keyboard.nextLine());

        UserC userDelete = new UserC();
        userDelete.deleteUser(id);
    }

    public void listUsers(){
        Utilities.clearConsole();

        ArrayList<UserM> users = new ArrayList<UserM>();

        UserC usersC = new UserC();
        usersC.connect();
        users = usersC.listUsers();
        usersC.disconnect();

        for(UserM user : users){
            System.out.println(
                user.getAll()
            );
        }


    }
}