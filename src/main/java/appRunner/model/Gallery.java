package appRunner.model;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.*;

@Entity
@Table(name = "galleries")
public class Gallery {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long gallery_id;
	
	@Column(nullable = false)
	private String name;
	
	@Column
	private String city;
	
	@Column(nullable = false)
	private String country;
	
	@Column
	private String description;
	
	@Column
	@OneToMany(mappedBy="galleryID", fetch = FetchType.EAGER)
	private Collection<PieceModel> pieces = new LinkedHashSet<PieceModel>();
	
	@Column
	private String image_ref;
	
	
	public String getImageRef() {
		return image_ref;
	}
	
	public void setImageRef(String image_ref) {
		this.image_ref = image_ref;
	}
	public String getName() {
		return name;
	}
	
	
	public Long getId() {
		return gallery_id;
	}
	
	public void setId(Long id) {
		this.gallery_id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Collection<PieceModel> getPieces(){
		return pieces;
	}
	
	

}
