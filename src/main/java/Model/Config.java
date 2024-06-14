package Model;
public class Config {
    private String url; 
    private String user; 
    private String password; 
    private String driver;
    
    public Config(){
        this.url = "jdbc:mysql://localhost:3306/to_do_db";
        this.user = "root";
        this.password = "";
        this.driver = "com.mysql.cj.jdbc.Driver";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
    
}
