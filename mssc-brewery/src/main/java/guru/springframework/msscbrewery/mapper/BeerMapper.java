package guru.springframework.msscbrewery.mapper;


import org.mapstruct.Mapper;

import guru.springframework.msscbrewery.web.model.Beer;
import guru.springframework.msscbrewery.web.model.BeerDto;

@Mapper(uses = { DateMapper.class })
public interface BeerMapper {

	Beer toBeer(BeerDto beerDto);

	BeerDto toBeerDto(Beer beer);

//	List<BeerDto> toBeerDtoList(List<Beer> beerList);
//
//	List<Beer> toBeerList(List<BeerDto> beerDtoList);
}