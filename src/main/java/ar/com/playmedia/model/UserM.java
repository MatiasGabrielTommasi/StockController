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
        String result = "";
        String dni = String.valueOf(this.getPersonId());
        String surname = this.getSurname();
        String name = this.getName();
        String user = this.getUser();
        String phone = this.getPhone();
        int aux = dni.length();
        for(int i = aux;i<12;i++)
            dni = dni + " ";
        
        aux = surname.length();
        for(int i = aux;i<25;i++)
            surname = surname + " ";
                
        aux = name.length();
        for(int i = aux;i<20;i++)
            name = name + " ";
            
        aux = user.length();
        for(int i = aux;i<20;i++)
            user = user + " ";
            
        aux = phone.length();
        for(int i = aux;i<15;i++)
            phone = phone + " ";

        //System.out.println("12|25|20|20|15");
        result = String.format("%s%s%s%s%s", dni, surname, name, user, phone);
        return result;
    }
}