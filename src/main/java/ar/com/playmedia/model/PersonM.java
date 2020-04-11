package ar.com.playmedia.model;

public class PersonM{
    protected int personId;
    protected String name;
    protected String surname; 
    protected String phone;
    
    public PersonM(){}

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
        return name;
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