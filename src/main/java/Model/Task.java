package Model;

public class Task {
    private int id_task; 
    private String task_name; 
    private String task_description; 
    private String task_date; 
    private State state; //task_state en DB
    private Person person; //person en DB
    
    public Task(int id_task, String task_name, String task_description, String task_date, State state, Person person){
        this.id_task = id_task; 
        this.task_name = task_name; 
        this.task_description = task_description; 
        this.task_date = task_date; 
        this.state = state; 
        this.person = person;
    }
    
    public Task(){ }

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public String getTask_date() {
        return task_date;
    }

    public void setTask_date(String task_date) {
        this.task_date = task_date;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
}
