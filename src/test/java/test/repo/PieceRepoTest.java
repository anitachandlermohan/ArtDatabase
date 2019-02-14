package test.repo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import appRunner.ArtDBapp;
import appRunner.model.PieceModel;
import appRunner.repository.PieceRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ArtDBapp.class})
@DataJpaTest
public class PieceRepoTest {

	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private PieceRepository repo;
	
	@Test
	public void retrieveByIDTest() {
		PieceModel model1 = new PieceModel("testname","testtype","testartist");
		em.persist(model1);
		em.flush();
		assertTrue(repo.findById(model1.getId()).isPresent());
		
	}
	

	


}
