package ar.com.playmedia.model;

import ar.com.playmedia.view.UserV;
import ar.com.playmedia.controller.UserC;


public class UserM extends PersonM{
    private int personId;
    private int userId;
    private String user;
    private String password;

    public UserM(){} 

    public UserM(
        int personId, String name, String surname, String phone,
        int userId, String password
    ){
        super(personId, name, surname, phone);

        user = name;
        this.userId = userId;
        this.password = password;
    }

   
    public void signIn(){

    }

    public void logIn(){

    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getUserId(){
        return userId;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getUser(){
        return user;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    
}