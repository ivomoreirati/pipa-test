package br.com.pipa.controller;

import br.com.pipa.dto.HighScoresDTO;
import br.com.pipa.dto.PositionDTO;
import br.com.pipa.dto.ScoreDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.*;

@RequestMapping(value = "/v1", consumes = "application/JSON", produces = "application/JSON")
public interface GameController {

	@PostMapping(path = "/score", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	void saveScore(@RequestBody ScoreDTO score);

	@GetMapping(path = "/{userId}/position", consumes = ALL_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	PositionDTO getCurrentPositionByUser(@PathVariable("userId") Integer userId);

	@GetMapping(path = "/highscorelist", consumes = ALL_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	HighScoresDTO getHighScoreList();
}
