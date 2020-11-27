package guru.springframework.msscbrewery.web.controller.v2;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbrewery.service.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;

@RequestMapping("api/v2/beer")
@RestController
public class BeerControllerV2 {
	
	private final BeerServiceV2 beerServiceV2;
	
	
	
	public BeerControllerV2(BeerServiceV2 beerServiceV2) {
		super();
		this.beerServiceV2 = beerServiceV2;
	}

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId) {
		return new ResponseEntity<BeerDtoV2>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
	}
	
	@PostMapping("api/v2/beer")
	public ResponseEntity<Void> handlePost(@RequestBody BeerDtoV2 beerDto) {
		BeerDtoV2 saveDto = beerServiceV2.saveBeer(beerDto);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "api/v1/beer/" + saveDto.getId().toString());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
//	@PostMapping("api/v2/beer")
//	public ResponseEntity<BeerDtoV2> handlePost(BeerDtoV2 beerDto) {
//		BeerDtoV2 saveDto = beerService.saveBeer(beerDto);
//		return new ResponseEntity<BeerDtoV2>(saveDto, HttpStatus.OK);
//	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity<HttpStatus> handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDtoV2 beerDto) {
		beerServiceV2.updateBeer(beerId, beerDto);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		beerServiceV2.deleteBeer(beerId);
	}
}
