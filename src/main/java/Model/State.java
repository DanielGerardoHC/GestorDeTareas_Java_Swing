package Model;

public class State {
    private int id_state; 
    private String state_name; 
    
    public State(int id_state, String state_name){
        this.id_state = id_state; 
        this.state_name = state_name;
    }
    
    public State(){ }

    public int getId_state() {
        return id_state;
    }

    public void setId_state(int id_state) {
        this.id_state = id_state;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }
    
}
