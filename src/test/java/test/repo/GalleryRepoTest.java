package test.repo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import appRunner.ArtDBapp;
import appRunner.model.Gallery;

import appRunner.repository.GalleryRepository;



@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ArtDBapp.class})
@DataJpaTest
public class GalleryRepoTest {

		@Autowired
		private TestEntityManager em;
		
		@Autowired
		private GalleryRepository repo;
		
		@Test
		public void retrieveByIDTest() {
			Gallery model1 = new Gallery("testname", "testcountry");
			em.persist(model1);
			em.flush();
			assertTrue(repo.findById(model1.getId()).isPresent());
			
	}
		

		


}


