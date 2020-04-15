
package ar.com.playmedia.controller;

import ar.com.playmedia.model.UserM;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class UserC{

    Connection dbConnection = null;
    String url = "jdbc:postgresql://127.0.0.1:5432/stockcontroller";
    String dbUser = "dba";
    String dbPassword = "12345678";
    Statement query = null;
    ResultSet result = null;


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

    public UserM login(UserM model){ 
        String queryString = String.format(
                        "SELECT * FROM users_login('%s', '%s');"
                        ,model.getUser()
                        ,model.getPassword()
        );

        try{
            query = dbConnection.createStatement();
            result = query.executeQuery(queryString);

            int counter = 0;
            while(result.next()){
                int dni = result.getInt(1);
                String name = result.getString(2);
                String surname = result.getString(3);
                String phone = result.getString(4);
                String user = result.getString(5);
                String password = result.getString(6);

                model = new UserM(dni, name, surname, phone, user, password);
                counter ++;
            }
            if(counter == 0){
                model = null;
            }
            query.close();
        }catch(Exception e){
            System.out.println("ERROR: " + e);
        }
        return model;
    }

    public void signUp(
        Integer personId, 
        String name, 
        String surname, 
        String phone, 
        String user, 
        String password
    ){
        String queryString = 
            String.format("SELECT * FROM users_ins(%d, '%s', '%s', '%s', '%s', '%s');"
                ,personId
                ,name
                ,surname
                ,phone
                ,user
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

    public ArrayList<UserM> listUsers(){
        ArrayList<UserM> users = new ArrayList<UserM>();

        String queryString = "SELECT * FROM users_list();";

        try{
            query = dbConnection.createStatement();
            result = query.executeQuery(queryString);
            while(result.next()){
                int dni = result.getInt(1);
                String name = result.getString(2);
                String surname = result.getString(3);
                String phone = result.getString(4);
                String user = result.getString(5);
                String password = result.getString(6);

                UserM model = new UserM(dni, name, surname, phone, user, password);

                users.add(model);
            }

            query.close();
        }catch(Exception e){
            System.out.println("*-ERROR-* " + e);
        }

        return users;
    }

    public ArrayList<UserM> searchUsers(UserM model){   
        ArrayList<UserM> users = new ArrayList<UserM>();     
        String queryString = String.format(
                        "SELECT * FROM users_search(%d, '%s', '%s', '%s', '%s');"
                        ,model.getPersonId()
                        ,model.getName()
                        ,model.getSurname()
                        ,model.getPhone()
                        ,model.getUser()
        );
        Statement query = null;

        try{
            query = dbConnection.createStatement();
            result = query.executeQuery(queryString);

            while(result.next()){
                int dni = result.getInt(1);
                String name = result.getString(2);
                String surname = result.getString(3);
                String phone = result.getString(4);
                String user = result.getString(5);
                String password = result.getString(6);

                UserM modelUser = new UserM(dni, name, surname, phone, user, password);

                users.add(modelUser);
            }
            query.close();
        }catch(Exception e){
            System.out.print("ERROR: " + e);
        }
        return users;
    }

    public void deleteUser(Integer userId){
		String queryString = String.format("SELECT * FROM users_del (%d);", userId);
		try{
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception ex){
            System.out.println("No se puso eliminar, el usuario ha generado ventas previamente.");
		}
    }

	public UserM setName(UserM model, String input) {
		String queryString = String.format("SELECT * FROM users_set_names (%d, '%s');",
							model.getPersonId(), input);
		try{
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception ex){

		}

		return searchUsers(model).get(0);
	}

	public UserM setSurname(UserM model, String input) {
		String queryString = String.format("SELECT * FROM users_set_surname (%d, '%s');",
							model.getPersonId(), input);
		try{
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception ex){

		}

		return searchUsers(model).get(0);
	}

	public UserM setPhone(UserM model, String input) {
		String queryString = String.format("SELECT * FROM users_set_phone (%d, '%s');",
							model.getPersonId(), input);
		try{
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception ex){

		}

		return searchUsers(model).get(0);
	}

	public UserM setUsername(UserM model, String input) {
		String queryString = String.format("SELECT * FROM users_set_username (%d, '%s');",
							model.getPersonId(), input);
		try{
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception ex){

		}

		return searchUsers(model).get(0);
	}

	public UserM setPassword(UserM model, String input) {
		String queryString = String.format("SELECT * FROM users_set_pass (%d, '%s');",
							model.getPersonId(), input);
		try{
			query = dbConnection.createStatement();
			query.execute(queryString);
			query.close();
		} catch (Exception ex){

		}

		return searchUsers(model).get(0);
	}
}