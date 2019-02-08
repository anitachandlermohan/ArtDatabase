
package appRunner.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import appRunner.exception.ResourceNotFoundException;
import appRunner.repository.PieceRepository;
import appRunner.model.PieceModel;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PieceController {
	
	@Autowired
	PieceRepository repo;
	
	
	//post piece//
	@PostMapping("/PieceModel")
	public PieceModel postPiece(@Valid @RequestBody PieceModel piece) {
		return repo.save(piece);
	}
	
	
	//get piece//
	
	@GetMapping("/PieceModel/{piece_id}")
	public PieceModel getPiece(@PathVariable(value = "piece_id")Long pieceID) {
		return repo.findById(pieceID).orElseThrow(()->new ResourceNotFoundException("PieceModel","piece_id", pieceID));
	}
	
	//get all pieces//
	
	@GetMapping("/PieceModel")
	public List<PieceModel> getAllPieces(){
		return repo.findAll();
	}
	
	
	//update piece//
	@PutMapping("/PieceModel/{piece_id}")
	public PieceModel updatePiece(@PathVariable(value = "piece_id")Long pieceID,
			@Valid @RequestBody PieceModel pieceDetails) {
		PieceModel piece = repo.findById(pieceID).orElseThrow(()-> new ResourceNotFoundException("Piece", "piece_id", pieceID));
		piece.setName(pieceDetails.getName());
		piece.setArtist(piece.getArtist());
		piece.setGallery(pieceDetails.getGallery());
		piece.setDescription(pieceDetails.getDescription());
		piece.setType(pieceDetails.getType());
		piece.setImageRef(pieceDetails.getImageRef());
		PieceModel updateData = repo.save(piece);
		return updateData;
		
	}
	
	@DeleteMapping("/PieceModel/{piece_id}")
	public ResponseEntity<?> deletePiece(@PathVariable(value = "piece_id")Long pieceID) {
		PieceModel piece = repo.findById(pieceID).orElseThrow(()-> new ResourceNotFoundException("Piece", "piece_id",pieceID));
		repo.delete(piece);
		return ResponseEntity.ok().build();
		
	}
	
	
	
	

}
