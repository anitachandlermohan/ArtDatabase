package appRunner.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import appRunner.exception.ResourceNotFoundException;
import appRunner.model.Gallery;
import appRunner.repository.GalleryRepository;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GalleryController {
	
	@Autowired
	GalleryRepository repo;
	
	
	//post piece//
	@PostMapping("/Gallery")
	public Gallery postGallery(@Valid @RequestBody Gallery piece) {
		return repo.save(piece);
	}
	
	
	//get piece//
	
	@GetMapping("/Gallery/{gallery_id}")
	public Gallery getGallery(@PathVariable(value = "gallery_id")Long galleryID) {
		return repo.findById(galleryID).orElseThrow(()->new ResourceNotFoundException("Gallery","gallery_id", galleryID));
	}
	
	//get all pieces//
	
	@GetMapping("/Gallery")
	public List<Gallery> getAllGallerys(){
		return repo.findAll();
	}
	
	
	//update piece//
	@PutMapping("/Gallery/{gallery_id}")
	public Gallery updateGallery(@PathVariable(value = "gallery_id")Long galleryID,
			@Valid @RequestBody Gallery galleryDetails) {
		Gallery piece = repo.findById(galleryID).orElseThrow(()-> new ResourceNotFoundException("Gallery", "gallery_id", galleryID));
		piece.setName(galleryDetails.getName());
		piece.setCountry(piece.getCountry());
		piece.setCity(galleryDetails.getCity());
		piece.setDescription(galleryDetails.getDescription());
		piece.setImageRef(galleryDetails.getImageRef());
		
		Gallery updateData = repo.save(piece);
		return updateData;
		
	}
	
	@DeleteMapping("/Gallery/{gallery_id}")
	public ResponseEntity<?> deleteGallery(@PathVariable(value = "gallery_id")Long galleryID) {
		Gallery piece = repo.findById(galleryID).orElseThrow(()-> new ResourceNotFoundException("Gallery", "gallery_id",galleryID));
		repo.delete(piece);
		return ResponseEntity.ok().build();
		
	}
	
}
