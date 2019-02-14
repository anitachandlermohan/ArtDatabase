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
import appRunner.model.PieceModel;
import appRunner.repository.PieceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ArtDBapp.class})
@AutoConfigureMockMvc

public class PieceIntegrationTest {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private PieceRepository repo;
	
	@Before
	public void clearDB() {
		repo.deleteAll();
	}
	
	
	@Test
	public void findAnRetrievePieceModelTest() throws Exception {
		repo.save(new PieceModel ("testname", "testtype","testartist"));
		mvc.perform(get("/api/PieceModel")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].name",is("testname")));
	}
	
	@Test
	public void addPieceModelToDBTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		PieceModel piece = new PieceModel("testname", "testtype","testartist");
		String pieceJSONstring = mapper.writeValueAsString(piece);
		mvc.perform(post("/api/PieceModel")
				.contentType(MediaType.APPLICATION_JSON)
				.content(pieceJSONstring))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.name", is("testname")));
		
		}
	@Test
	public void deletePieceModelfromDB() throws Exception {
		PieceModel piece = new PieceModel("testname", "testtype","testartist");
		repo.save(piece);
		mvc.perform(delete("/api/PieceModel/{id}", piece.getId())
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}
	
	@Test 
	public void updatePieceModel() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		PieceModel piece = new PieceModel("testname", "testtype","testartist");
		PieceModel pieceUpdate = new PieceModel("testname2", "testtype","testartist");
		String pieceString = mapper.writeValueAsString(piece);
		String pieceUpdateString = mapper.writeValueAsString(pieceUpdate);
		repo.save(piece);
		mvc.perform(put("/api/PieceModel/{id}", piece.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(pieceUpdateString))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.name", is("testname2")));
		
	}

}
