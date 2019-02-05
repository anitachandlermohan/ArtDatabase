package appRunner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import appRunner.model.Gallery;
//
@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long>{
//
}
