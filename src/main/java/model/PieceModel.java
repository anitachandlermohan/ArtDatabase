package model;

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
	
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "gallery_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Gallery gallery;
	
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
	
	public Gallery getGallery() {
		return gallery;
	}
	
	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}
}
