package Model;

import java.util.ArrayList;

public interface OperacionesBasicas {
    public boolean insertar(Object obj, int id); 
    public boolean modificar(Object obj, int id); 
    public boolean eliminar(int id); 
    public ArrayList<?> seleccionar(int id); 
    
}
