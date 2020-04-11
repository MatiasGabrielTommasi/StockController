package ar.com.playmedia.controller;

import ar.com.playmedia.model.UserM;
import ar.com.playmedia.view.UserV;

import java.util.ArrayList;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class UserC{

    Connection dbConnection = null;
    String url = "jdbc:postgresql://127.0.0.1:5432//StockController";
    String dbUser = "dba";
    String dbPassword = "12345678";


    public void connect(){
        try{
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(url, dbUser, dbPassword);
        }catch(Exception e){
            System.out.print("ERROR: " + e);
        }
    }

    public void disconnect(){
        try{
            dbConnection.close();
        }catch(Exception e){
            System.out.print("ERROR: " + e);
        }
    }

    public void login(){

    }

    public void signIn(
        Integer personId, 
        String name, 
        String surname, 
        String phone, 
        String user, 
        Integer userId, 
        String password
    ){
        Statement query = null;
        String queryString = 
            String.format(
                "INSERT INTO stock_controller" +
                    "VALUES(%s, %s, %s, %s, %s, %s)"
                ,personId.toString()
                ,name
                ,surname
                ,phone
                ,user
                ,userId
                ,password
            );

        try{
            query = dbConnection.createStatement();
            query.execute(queryString);
            query.close();
        }catch(Exception e){
            System.out.println("ERROR: " + e);
        }
    }

    public ArrayList listUsers(){
        ArrayList<UserM> users = new ArrayList<UserM>();

        Statement query = null;
        ResultSet result = null;
        String queryString = "SELECT * FROM stock_controller";

        try{
            query = dbConnection.createStatement();
            result = query.executeQuery(queryString);

            while(result.next()){
                int dni = result.getInt(1);
                String name = result.getString(2);
                String surname = result.getString(3);
                String phone = result.getString(4);
                String user = result.getString(5);
                int userId = result.getInt(6);
                String password = result.getString(7);

                users.add( 
                    new UserM(dni, name, surname, phone, user, userId, password)
                );
            }

            query.close();
        }catch(Exception e){
            System.out.println("*-ERROR-* " + e);
        }

        return users;
    }

    public void searchingUser(Integer personId){
        
        String queryString = String.format(
                        "SELECT * FROM stock_controller WHERE dni = %s"
                        , personId.toString()
        );
        Statement query = null;

        try{
            query = dbConnection.createStatement();
            query.execute(queryString);
            query.close();
        }catch(Exception e){
            System.out.print("ERROR: " + e);
        }
    }

    public void updateUser(Integer personId){
        searchingUser(personId);

        Scanner keyboard = new Scanner(System.in);
        int option = -1;

        System.out.print(
            "Seleccione campo a cambiar: \n" + 
            "1-DNI." + 
            "2-Nombre." + 
            "3-Apellido" +
            "4-Telefono." + 
            "5-Nombre de usuario." + 
            "6-Id de usuario." + 
            "7-Contraseña."
        );
        option = Integer.parseInt(keyboard.nextLine());

        System.out.print("Ingrese nuevo valor: ");
        String new_value = keyboard.nextLine();

        String fieldToChange = "";
        String queryString = null;        
        switch(option){
            case 1:
                queryString = String.format("
                    UPDATE TABLE stock_controller SET DNI = %s " +
                        "WHERE DNI = %s" 
                        , new_value, personId
                );
                break;
            case 2:
                queryString = String.format(
                    "UPDATE TABLE stock_controller SET NOMBRE = %s" +
                        "WHERE DNI = %s"
                    , new_value, personId
                );
                break;
            case 3:
                queryString = String.format(
                    "UPDATE TABLE stock_controller SET APELLIDO = %s" +
                        "WHERE DNI = %s"
                    , new_value, personId
                );
                break;
            case 4:
                queryString = String.format(
                    "UPDATE TABLE stock_controller SET TELEFONO = %s" + 
                        "WHERE DNI = %s"
                    , new_value, personId
                );
                break;
            case 5:
                queryString = String.format(
                    "UPDATE TABLE stock_controller SET USUARIO = %s" + 
                        "WHERE DNI = %s"
                    , new_value, personId
                );
                break;
            case 6:
                queryString = String.format(
                    "UPDATE TABLE stock_controller SET ID = %s" + 
                        "WHERE DNI = %s"
                    , new_value, personId
                );
                break;
            case 7:
                queryString = String.format(
                    "UPDATE TABLE stock_controller SET CONTRASEÑA = %s" + 
                        "WHERE DNI = %s"
                    ,    new_value, personId
                );
                break;
            default:
                System.out.println("*-ERROR-* Opcion incorrecta.");
        }

        Statement query = null;

        try{
            query = dbConnection.createStatement();
            query.execute(queryString);
            query.close();
        }catch(Exception e){
            System.out.print("ERROR: " + e);
        }
    }

    public void deleteUser(Integer personId){
        Scanner keyboard = new Scanner(System.in);

        searchingUser(personId);

        System.out.print("¿Desea eliminar este contacto? (1 si/2 no): ");
        int delete = Integer.parseInt(keyboard.next());

        String queryString = null;

        switch(delete){
            case 1:
                queryString = String.format(
                    "DELETE FROM stock_controller WHERE dni = %s"
                    , personId.toString()
                );
                break;
            case 2:
                break;
            default:
                System.out.println("*-ERROR-* Opcion incorrecta.");
                break;
        }

        Statement query = null;

        try{
            query = dbConnection.createStatement();
            query.execute(queryString);
            query.close();
        }catch(Exception e){
            System.out.print("ERROR: " + e);
        }
    }
}
