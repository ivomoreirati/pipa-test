package br.com.pipa.service.impl;

import br.com.pipa.dto.PositionDTO;
import br.com.pipa.dto.ScoreDTO;
import br.com.pipa.service.ScoreService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class ScoreServiceImpl implements ScoreService {

    Map<Integer, ScoreDTO> mapScore = new HashMap<>();

    @Override
    public void saveScore(final ScoreDTO score) {
        ScoreDTO scoreDTO = mapScore.getOrDefault(score.getUserId(), null);
        if(!isNull(scoreDTO)) {
            scoreDTO.setPoints(scoreDTO.getPoints() + score.getPoints());
            mapScore.put(score.getUserId(), scoreDTO);
        } else {
            mapScore.put(score.getUserId(), score);
        }
    }

    private Comparator<ScoreDTO> getComparatorScore() {
        return (o1, o2) -> o2.getPoints().compareTo(o1.getPoints());
    }

    private LinkedHashSet<PositionDTO> getHighScores() {
        AtomicInteger index = new AtomicInteger();
        return mapScore.values().stream()
                .sorted(getComparatorScore())
                .map(scoreDTO-> PositionDTO.parse(scoreDTO, index.getAndIncrement() + 1))
                .collect(Collectors.toCollection( LinkedHashSet::new));
    }

    @Override
    public Callable<PositionDTO> getCurrentPositionByUserWithCallable(Integer userId) {
        return () -> {
            PositionDTO positionDTO = getPositionByUser(userId);
            if(isNull(positionDTO)) {
                saveScore(ScoreDTO.builder().userId(userId).points(0).build());
                return getPositionByUser(userId);
            } else {
                return positionDTO;
            }
        };
    }

    private PositionDTO getPositionByUser(Integer userId) {
        return getHighScores().stream()
                    .filter(score -> score.getUserId().equals(userId))
                    .findFirst()
                    .orElse(null);
    }

    @Override
    public Callable<LinkedHashSet<PositionDTO>> getHighScoresListWithCallable() {
        return this::getHighScores;
    }
}
