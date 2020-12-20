package guru.springframework.msscbrewery.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import guru.springframework.msscbrewery.mapper.BeerMapper;
import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.Beer;
import guru.springframework.msscbrewery.web.model.BeerDto;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {
	
	@Autowired
	private BeerService beerService;
	
	@Autowired
	private BeerMapper mapper;
	
//	public BeerController(BeerService beerService) {
//		this.beerService = beerService;
//	}

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {

		Beer beer = mapper.toBeer(BeerDto.builder().id(UUID.randomUUID()).build());
		System.out.println(beer);

		return new ResponseEntity<BeerDto>(beerService.getBeerById(beerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> handlePost(@Valid @RequestBody BeerDto beerDto) {
		BeerDto saveDto = beerService.saveBeer(beerDto);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "api/v1/beer/" + saveDto.getId().toString());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
//	@PostMapping("api/v1/beer")
//	public ResponseEntity<BeerDto> handlePost(BeerDto beerDto) {
//		BeerDto saveDto = beerService.saveBeer(beerDto);
//		return new ResponseEntity<BeerDto>(saveDto, HttpStatus.OK);
//	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity<HttpStatus> handleUpdate(@PathVariable("beerId") UUID beerId,
			@Valid @RequestBody BeerDto beerDto) {
		beerService.updateBeer(beerId, beerDto);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		beerService.deleteBeer(beerId);
	}
}
