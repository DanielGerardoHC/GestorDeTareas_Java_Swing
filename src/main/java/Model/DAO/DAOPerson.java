package Model.DAO;

import Model.Config;
import Model.OperacionesBasicas;
import Model.Person;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.transform.Result;

public class DAOPerson implements OperacionesBasicas{
    Config bd = new Config(); 
    Person person = new Person(); 
    
    @Override
    public boolean insertar(Object obj, int id) {   
       this.person =(Person)obj;
       Connection con;
       PreparedStatement pst;
       String sql= "insert into Person (name, secret) values(?,?)";
       
       try{
           Class.forName(this.bd.getDriver());
           con = DriverManager.getConnection(this.bd.getUrl(),
                                            this.bd.getUser(),
                                            this.bd.getPassword());
           pst = con.prepareStatement(sql);
           
            pst.setString(1, this.person.getName());
            pst.setString(2, this.person.getSecret());  
      
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
       this.person =(Person)obj;
       Connection con;
       PreparedStatement pst;
       String sql= "update Person set name = ?, secret = ? where id_person = ?";
       
       try{
           Class.forName(this.bd.getDriver());
           con = DriverManager.getConnection(this.bd.getUrl(),
                                            this.bd.getUser(),
                                            this.bd.getPassword());
           pst = con.prepareStatement(sql);
           
            pst.setString(1, this.person.getName());
            pst.setString(2, this.person.getSecret());
            pst.setInt(3, this.person.getId_person());
            
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
           String sql= "delete from Person where id_person = ?";
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
        ArrayList<Person> persons = new ArrayList<>();
        String consulta = "SELECT id_person, name, secret FROM Person";
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
                Person person = new Person();
                person.setId_person(resultado.getInt("id_person"));
                person.setName(resultado.getString("name"));
                person.setSecret(resultado.getString("secret"));

                persons.add(person);
            }
            
            // Cerrar recursos
            resultado.close();
            statement.close();
            con.close();
            
        } catch (Exception e) {
         
            e.printStackTrace();
        }
        return persons;
    }
    public Person Autentificacion(String userName, String password)
    {
        Person currentUser = new Person();
        String consulta = "SELECT * FROM Person where name = ? and secret = ?";
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
            statement.setString(1, userName);
            statement.setString(2, password);
            // Ejecutar la consulta
            ResultSet resultado = statement.executeQuery();
            if (!resultado.next())
            {
                return null;
            }
            // Procesar el resultado
                currentUser.setId_person(resultado.getInt("id_person"));
                currentUser.setName(resultado.getString("name"));
                currentUser.setSecret(resultado.getString("secret"));
            // Cerrar recursos
            resultado.close();
            statement.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return currentUser;
    }
    public Person seleccionarEspecificObject(int id)
    {
        Person currentUser = new Person();
        String consulta = "SELECT * FROM Person where id_person = ?";
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
            statement.setInt(1, id);
            // Ejecutar la consulta
            ResultSet resultado = statement.executeQuery();
            if (!resultado.next())
            {
                return null;
            }
            // Procesar el resultado
                currentUser.setId_person(resultado.getInt("id_person"));
                currentUser.setName(resultado.getString("name"));
                currentUser.setSecret(resultado.getString("secret"));
            // Cerrar recursos
            resultado.close();
            statement.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return currentUser;
    }
    public boolean isExist(String userName)
    {
        boolean result = true;
        String consulta = "SELECT * FROM Person where name = ?";
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
            
            statement.setString(1, userName);
            // Ejecutar la consulta
            ResultSet resultado = statement.executeQuery();

            // Procesar el resultado
            result = resultado.next();
            // Cerrar recursos
            statement.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
