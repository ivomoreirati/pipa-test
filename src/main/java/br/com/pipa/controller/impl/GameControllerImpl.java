package br.com.pipa.controller.impl;

import br.com.pipa.controller.GameController;
import br.com.pipa.dto.HighScoresDTO;
import br.com.pipa.dto.PositionDTO;
import br.com.pipa.dto.ScoreDTO;
import br.com.pipa.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@RestController
public class GameControllerImpl implements GameController {

	@Autowired
	private ScoreService service;

	@Autowired
	Environment env;

	@Autowired
	ExecutorService executorService;

	@Override
	public void saveScore(final ScoreDTO score) {
		executorService.execute(() -> service.saveScore(score));
	}

	@Override
	public PositionDTO getCurrentPositionByUser(final Integer userId) {
		Future<PositionDTO> result = executorService.submit(service.getCurrentPositionByUserWithCallable(userId));
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HighScoresDTO getHighScoreList() {
		int controlLimitOutputListScore =
				Integer.parseInt(Objects.requireNonNull(env.getProperty("control.limit-output-list-score")));
		HighScoresDTO highScoresDTO = new HighScoresDTO();
		Future<LinkedHashSet<PositionDTO>> result = executorService.submit(service.getHighScoresListWithCallable());
		try {
			highScoresDTO.setHighScores(result.get(), controlLimitOutputListScore);
		} catch (InterruptedException | ExecutionException e) {
			highScoresDTO = null;
		}
		return highScoresDTO;
	}
}
