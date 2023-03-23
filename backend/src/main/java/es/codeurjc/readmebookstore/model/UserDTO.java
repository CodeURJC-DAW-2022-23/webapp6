package es.codeurjc.readmebookstore.model;
import javax.persistence.Lob;
import java.sql.Blob;


public class UserDTO {
    private String name;
    private String password;
    private String email;

    public UserDTO() {
    }

    public UserDTO(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
