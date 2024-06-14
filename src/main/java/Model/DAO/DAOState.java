package Model.DAO;

import Model.Config;
import Model.OperacionesBasicas;
import Model.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DAOState implements OperacionesBasicas{
    Config bd = new Config(); 
    State state = new State(); 
    
    @Override
    public boolean insertar(Object obj, int id) {
       this.state =(State)obj;
       Connection con;
       PreparedStatement pst;
       String sql= "insert into State (name) values(?)";
       
       try{
           Class.forName(this.bd.getDriver());
           con = DriverManager.getConnection(this.bd.getUrl(),
                                            this.bd.getUser(),
                                            this.bd.getPassword());
           pst = con.prepareStatement(sql);
           
            pst.setString(1, this.state.getState_name());
      
            int afectedRows = pst.executeUpdate();
            con.close();
            pst.close();
            return afectedRows > 0;
           
       }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
           return false;
       }  
    }

    @Override
    public boolean modificar(Object obj, int id) {
       this.state =(State)obj;
       Connection con;
       PreparedStatement pst;
       String sql= "update State set state_name = ? where id_state = ?";
       
       try{
           Class.forName(this.bd.getDriver());
           con = DriverManager.getConnection(this.bd.getUrl(),
                                            this.bd.getUser(),
                                            this.bd.getPassword());
            pst = con.prepareStatement(sql);
           
            pst.setString(1, this.state.getState_name());
            pst.setInt(2, this.state.getId_state());
            
            int afectedRows = pst.executeUpdate();
            con.close();
            pst.close();
            return afectedRows > 0;
           
       }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
           return false;
       }  
    }

    @Override
    public boolean eliminar(int id) {
        try{
           Connection con;
           PreparedStatement pst;
           String sql= "delete from State where id_state = ?";
           Class.forName(this.bd.getDriver());
           con = DriverManager.getConnection(this.bd.getUrl(),
                                            this.bd.getUser(),
                                            this.bd.getPassword());
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
         
            int afectedRows = pst.executeUpdate();
            con.close();
            pst.close();
            return afectedRows > 0;
           
       }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
           return false;
       }  
    }

    @Override
    public ArrayList<?> seleccionar(int id) {
        ArrayList<State> states = new ArrayList<>();
        String consulta = "SELECT id_state, state_name FROM State";
        try 
        {
            // Establecer conexion   
            Connection con;
            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(),
                                              this.bd.getUser(),
                                              this.bd.getPassword());
            // Preparar la consulta
            PreparedStatement statement = con.prepareStatement(consulta);
            
            // Ejecutar la consulta
            ResultSet resultado = statement.executeQuery();
            
            // Procesar el resultado
            while (resultado.next()) {
                State state = new State();
                state.setId_state(resultado.getInt("id_state"));
                state.setState_name(resultado.getString("state_name"));

                states.add(state);
            }
            
            // Cerrar recursos
            resultado.close();
            statement.close();
            con.close();
            
        } catch (Exception e) {
         
            e.printStackTrace();
        }
        return states;
    }
}
