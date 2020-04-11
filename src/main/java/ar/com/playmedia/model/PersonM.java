package ar.com.playmedia.model;

public class PersonM{
    private int personId;
    private String name;
    private String surname; 
    private String phone;
    
    public PersonM(){
        this.personId = 0;
        this.name = "";
        this.surname = "";
        this.phone = "";
    }

    public PersonM(int personId, String name, String surname, String phone){
        this.personId = personId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){ 
        return name; 
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getSurname(){
        return surname;
    }

    public void setPersonId(int id){
        personId = id;
    }

    public int getPersonId(){
        return personId;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }
}