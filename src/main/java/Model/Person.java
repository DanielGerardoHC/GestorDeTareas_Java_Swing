package Model;

public class Person {
    private int id_person; 
    private String name; 
    private String secret;
    
    public Person(int id_person, String name, String secret){
        this.id_person = id_person; 
        this.name = name; 
        this.secret = secret; 
    }
    
    public Person(){ }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
