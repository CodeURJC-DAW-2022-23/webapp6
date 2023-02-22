package es.codeurjc.readmebookstore.model;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.DynamicUpdate;
import java.sql.Blob;
import java.util.List;
import java.sql.Blob;
import java.util.List;

import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany; 
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
@DynamicUpdate

public class User {

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name +  ", email=" + email
				+ ", imageFile=" + imageFile + ", image=" + image + ", encodedPassword=" + encodedPassword + 
				", roles=" + roles + ", readedReviews=" + readedReviews + "]";
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
    
    private String email;

	private String encodedPassword;

    @ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

    @Lob
	private Blob imageFile;
	private boolean image;

	@OneToMany
	private List<Review> readedReviews;

	public User() {
	}

	public User(String name, String encodedPassword, String email, String... roles) {
		this.name = name;
		this.encodedPassword = encodedPassword;
		this.email = email;
        this.roles = List.of(roles);
	}



	public Long getId() {
		return id;
	}

    public String getName() {
		return name;
	}

    public String getEmail() {
		return email;
	}

    public String getEncodedPassword() {
		return encodedPassword;
	}

    public List<String> getRoles() {
		return roles;
	}

    public List<Review> getReadedReviews() {
		return readedReviews;
	}

	public void setName(String name) {
		this.name = name;
	}

    public void setEmail(String email) {
		this.email = email;
	}	

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}	

	public void setReadedReviews(List<Review> readedReviews) {
		this.readedReviews = readedReviews;
	}

	public Blob getImageFile() {
		return imageFile;
	}

	public void setImageFile(Blob image) {
		this.imageFile = image;
	}

	public boolean hasImage(){
		return this.image;
	}

	public void setImage(boolean image){
		this.image = image;
	}
	public boolean getImage() {
		return image;
	}

}