package Model.DAO;

import Model.Config;
import Model.OperacionesBasicas;
import Model.Person;
import Model.State;
import Model.Task;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class DAOTask implements OperacionesBasicas{
    Config bd = new Config(); 
    Task task = new Task(); 

    @Override
    public boolean insertar(Object obj, int id) {
       this.task =(Task)obj;
       Connection con;
       PreparedStatement pst;
       String sql= "insert into Task (task_name, task_description, task_date, task_state, person) values(?, ?, ?, ?, ?)";
       
       try{
           Class.forName(this.bd.getDriver());
           con = DriverManager.getConnection(this.bd.getUrl(),
                                            this.bd.getUser(),
                                            this.bd.getPassword());
           pst = con.prepareStatement(sql);
           
            pst.setString(1, this.task.getTask_name());
            pst.setString(2, this.task.getTask_description());
            pst.setString(3, this.task.getTask_date());
            
            //Get state id
            State state = new State();
            state = task.getState(); 
            
            //Get person id
            Person person = new Person(); 
            person = task.getPerson(); 
            
            pst.setInt(4, state.getId_state());
            pst.setInt(5, person.getId_person());
      
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
       this.task =(Task)obj;
       Connection con;
       PreparedStatement pst;
       String sql= "update Task set task_name = ?, task_description = ?, task_date = ?, task_state = ? where id_task = ?";
       
       try{
           Class.forName(this.bd.getDriver());
           con = DriverManager.getConnection(this.bd.getUrl(),
                                            this.bd.getUser(),
                                            this.bd.getPassword());
            pst = con.prepareStatement(sql);
           
            pst.setString(1, this.task.getTask_name());
            pst.setString(2, this.task.getTask_description());
            pst.setString(3, this.task.getTask_date());          
            pst.setInt(4, this.task.getState().getId_state());
            pst.setInt(5, this.task.getId_task());
            
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
           String sql= "delete from Task where id_task = ?";
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
    public ArrayList<Task> seleccionar(int id) {
        ArrayList<Task> tasks = new ArrayList<>();
        String consulta = "SELECT T.id_task, T.task_name, T.task_description, T.task_date, " +
                "S.id_state as id_state, " +
                "S.state_name as state_name, " +
                "P.id_person as id_person, " +
                "P.name as person_name " +
                "FROM Task T " +
                "INNER JOIN State S ON T.task_state = S.id_state " +
                "INNER JOIN Person P ON T.person = P.id_person " +
                "WHERE T.person = ?";
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
            statement.setInt(1,id);
            // Ejecutar la consulta
            ResultSet resultado = statement.executeQuery();
            
            // Procesar el resultado
            while (resultado.next()) {
                Task task = new Task();
                task.setId_task(resultado.getInt("id_task"));
                task.setTask_name(resultado.getString("task_name"));
                task.setTask_description(resultado.getString("task_description"));
                task.setTask_date(resultado.getString("task_date"));
                
                Person person = new Person(); 
                person.setId_person(resultado.getInt("id_person"));
                person.setName(resultado.getString("person_name"));
                task.setPerson(person);
                
                State state = new State(); 
                state.setId_state(resultado.getInt("id_state"));
                state.setState_name(resultado.getString("state_name"));
                task.setState(state);
                
                tasks.add(task);
            }
            
            // Cerrar recursos
            resultado.close();
            statement.close();
            con.close();
            
        } catch (Exception e) {
         
            e.printStackTrace();
        }
        return tasks;
    }
    
    public ArrayList<Task> seleccionarDonde(int id, String busqueda) {
        ArrayList<Task> tasks = new ArrayList<>();
        String consulta = "SELECT T.id_task, T.task_name, T.task_description, T.task_date, " +
                "S.id_state as id_state, " +
                "S.state_name as state_name, " +
                "P.id_person as id_person, " +
                "P.name as person_name " +
                "FROM Task T " +
                "INNER JOIN State S ON T.task_state = S.id_state " +
                "INNER JOIN Person P ON T.person = P.id_person " +
                "WHERE T.person = ? and S.state_name = ?";
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
            statement.setInt(1,id);
            statement.setString(2,busqueda);
            // Ejecutar la consulta
            ResultSet resultado = statement.executeQuery();
            
            // Procesar el resultado
            while (resultado.next()) {
                Task task = new Task();
                task.setId_task(resultado.getInt("id_task"));
                task.setTask_name(resultado.getString("task_name"));
                task.setTask_description(resultado.getString("task_description"));
                task.setTask_date(resultado.getString("task_date"));
                
                Person person = new Person(); 
                person.setId_person((int)resultado.getObject("id_person"));
                person.setName(resultado.getString("person_name"));
                task.setPerson(person);
                
                State state = new State(); 
                state.setId_state(resultado.getInt("id_state"));
                state.setState_name(resultado.getString("state_name"));
                task.setState(state);
                
                tasks.add(task);
            }
            
            // Cerrar recursos
            resultado.close();
            statement.close();
            con.close();
            
        } catch (Exception e) {
         
            e.printStackTrace();
        }
        return tasks;
    }
    public ArrayList<Map<String, Object>> seleccionarReport(int id, String busqueda)
    {
        ArrayList<Map<String, Object>> tasks = new ArrayList<>();
        String consulta = "SELECT T.id_task, T.task_name, T.task_description, T.task_date, " +
                "S.id_state as id_state, " +
                "S.state_name as state_name, " +
                "P.id_person as id_person, " +
                "P.name as person_name " +
                "FROM Task T " +
                "INNER JOIN State S ON T.task_state = S.id_state " +
                "INNER JOIN Person P ON T.person = P.id_person " +
                "WHERE T.person = ? and S.state_name = ?";
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
            statement.setInt(1,id);
            statement.setString(2,busqueda);
            // Ejecutar la consulta
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                Map<String, Object> fila = new HashMap<>();
                fila.put("task_name", resultado.getObject("task_name"));
                fila.put("task_description", resultado.getObject("task_description"));
                fila.put("task_date", resultado.getString("task_date"));
                fila.put("state_name", resultado.getObject("state_name"));
                fila.put("person_name", resultado.getObject("person_name"));
                tasks.add(fila);
            }
            
            // Cerrar recursos
            resultado.close();
            statement.close();
            con.close();
            
        } catch (Exception e) {
         
            e.printStackTrace();
        }
        return tasks;
    }
}
