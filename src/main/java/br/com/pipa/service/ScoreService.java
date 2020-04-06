package br.com.pipa.service;

import br.com.pipa.dto.PositionDTO;
import br.com.pipa.dto.ScoreDTO;

import java.util.LinkedHashSet;
import java.util.concurrent.Callable;

public interface ScoreService {

    void saveScore(final ScoreDTO score);

    Callable<LinkedHashSet<PositionDTO>> getHighScoresListWithCallable();

    Callable<PositionDTO> getCurrentPositionByUserWithCallable(Integer userId);
}
