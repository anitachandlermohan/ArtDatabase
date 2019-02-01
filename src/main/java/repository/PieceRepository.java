package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.PieceModel;

@Repository
public interface PieceRepository extends JpaRepository<PieceModel, Long> {

}
