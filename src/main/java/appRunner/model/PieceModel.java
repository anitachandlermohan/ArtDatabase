package appRunner.model;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "piece")
public class PieceModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long piece_id;
	
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private String artist;
	
	@Column
	private String description;
	
	@Column
	private String image_ref;
	public String getImageRef() {
		return image_ref;
	}
	
	public void setImageRef(String image_ref) {
		this.image_ref = image_ref;
	}
	
	

	private Long galleryID;
	
	public Long getId() {
		return piece_id;
		
	}
	
	public void setId(Long id) {
		this.piece_id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public  String getType() {
		return type;
	}
		
	public void setType(String type) {
		this.type = type;
	}
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getGallery() {
		return galleryID;
	}
	
	public void setGallery(Long galleryID) {
		this.galleryID = galleryID;
	}
}
