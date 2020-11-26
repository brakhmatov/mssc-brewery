package guru.springframework.msscbrewery.services;

import java.util.UUID;

import guru.springframework.msscbrewery.web.module.BeerDto;

public interface BeerService {

	BeerDto getBeerById(UUID beerId);

}
