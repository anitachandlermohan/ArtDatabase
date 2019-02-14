package test.integration;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import appRunner.ArtDBapp;
import appRunner.model.Gallery;
import appRunner.repository.GalleryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ArtDBapp.class})
@AutoConfigureMockMvc
public class GalleryIntegrationTest {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private GalleryRepository repo;
	
	@Before
	public void clearDB() {
		repo.deleteAll();
	}
	
	
	@Test
	public void findAnRetrieveGalleryTest() throws Exception {
		repo.save(new Gallery ("testname", "testcountry"));
		mvc.perform(get("/api/Gallery")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].name",is("testname")));
	}
	
	@Test
	public void addGalleryToDBTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Gallery gallery = new Gallery("name", "country");
		String galleryJSONstring = mapper.writeValueAsString(gallery);
		mvc.perform(post("/api/Gallery")
				.contentType(MediaType.APPLICATION_JSON)
				.content(galleryJSONstring))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.name", is("name")));
		
		}
	@Test
	public void deleteGalleryfromDB() throws Exception {
		Gallery gallery = new Gallery("testname", "testcountry");
		repo.save(gallery);
		mvc.perform(delete("/api/Gallery/{id}", gallery.getId())
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}
	
	@Test 
	public void updateGallery() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Gallery gallery = new Gallery("testname", "testcountry");
		Gallery galleryUpdate = new Gallery("testname2", "testcountry");
		String galleryString = mapper.writeValueAsString(gallery);
		String galleryUpdateString = mapper.writeValueAsString(galleryUpdate);
		repo.save(gallery);
		mvc.perform(put("/api/Gallery/{id}", gallery.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(galleryUpdateString))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.name", is("testname2")));
		
	}
}
