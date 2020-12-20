package guru.springframework.msscbrewery.web.model;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Beer {

	private UUID id;
	private String beerName;
	private String beerStyle;
	private Long upc;

	private Timestamp createdDate;
	private Timestamp updatedDate;
}
