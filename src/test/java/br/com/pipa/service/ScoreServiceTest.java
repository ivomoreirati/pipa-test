package br.com.pipa.service;

import br.com.pipa.dto.PositionDTO;
import br.com.pipa.dto.ScoreDTO;
import br.com.pipa.service.impl.ScoreServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ScoreServiceTest {

    private ScoreService service;

    @Before
    public void setup() {
        this.service = new ScoreServiceImpl();
    }

    @Test
    public void testGetCurrentPositionByUserAfterTwoScoreAdded() throws Exception {
        service.saveScore(ScoreDTO.builder().userId(1).points(10).build());
        service.saveScore(ScoreDTO.builder().userId(1).points(10).build());
        Assert.assertEquals(20, (int) service.getCurrentPositionByUserWithCallable(1).call().getScore());
    }

    @Test
    public void testGetHighScoresListWhenCalculatedPosition() throws Exception {
        service.saveScore(ScoreDTO.builder().userId(1).points(10).build());
        service.saveScore(ScoreDTO.builder().userId(2).points(20).build());
        service.saveScore(ScoreDTO.builder().userId(3).points(15).build());
        Assert.assertTrue(
                service.getHighScoresListWithCallable()
                .call()
                .stream().anyMatch(positionDTO -> positionDTO.getPosition().equals(1)
                                    && positionDTO.getScore().equals(20)));
    }

    @Test
    public void testGetCurrentPositionByUserWhenNotExistsOnList() throws Exception {
        service.saveScore(ScoreDTO.builder().userId(1).points(10).build());
        service.saveScore(ScoreDTO.builder().userId(2).points(20).build());
        service.saveScore(ScoreDTO.builder().userId(3).points(15).build());
        PositionDTO positionDTO = service.getCurrentPositionByUserWithCallable(5)
                .call();
        Assert.assertTrue(positionDTO.getScore().equals(0) && positionDTO.getPosition().equals(4));
    }
}
