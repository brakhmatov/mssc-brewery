package guru.springframework.msscbrewery.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private BeerService beerService;

	@Autowired
	ObjectMapper objectMapper;
	
	static BeerDto validBeer;

	@BeforeAll
	static void setUp() {
		validBeer = BeerDto.builder().id(UUID.randomUUID()).beerName("Beer1")
				.beerStyle("PALE_ALE")
				.upc(123456789012L)
				.build();
	}

//	@Test
//	void testGetBeerById() throws Exception {
//		mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk());
//	}
//
//	@Test
//	void testSaveNewBeer() throws Exception {
//		BeerDto beerDto = validBeer;
//		// beerDto.setId(null);
//		when(beerService.saveBeer(beerDto)).thenReturn(BeerDto.builder().id(UUID.randomUUID()).build());
//		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
//		
//		mockMvc.perform(post("/api/v1/beer/")
//					.contentType(MediaType.APPLICATION_JSON)
//					.content(beerDtoJson))
//				.andExpect(status().isCreated());
//	}
//
//	@Test
//	void testUpdateBeerById() throws Exception {
//		BeerDto beerDto = validBeer;
//		beerDto.setId(null);
//		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
//		
//		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON)
//				.content(beerDtoJson))
//				.andExpect(status().isNoContent());
//	}

	// error handling!!!
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> validationErrorHander(ConstraintViolationException e) {
		List<String> errors = new ArrayList<>(e.getConstraintViolations().size());

		e.getConstraintViolations().forEach(constrainVaolation -> {
			errors.add(constrainVaolation.getPropertyPath() + " : " + constrainVaolation.getMessage());
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}
