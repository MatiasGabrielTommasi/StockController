package ar.com.playmedia.model;

public class UserM extends PersonM{
    private String user;
    private String password;

    public UserM(){
        super();

        this.user = "";
        this.password = "";
    } 

    public UserM(
        int personId, String name, String surname, String phone,
        String username, String password
    ){
        super(personId, name, surname, phone);

        this.user = username;
        this.password = password;
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
    
    public String toString(){
        String user = "";
        user = String.format("%s  %s  %s  %s  %s"
        , this.getPersonId(), this.getSurname(), this.getName(), this.getPhone(), this.user);
        return user;
    }
}