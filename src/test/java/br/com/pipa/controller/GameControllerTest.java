package br.com.pipa.controller;

import br.com.pipa.dto.HighScoresDTO;
import br.com.pipa.dto.PositionDTO;
import br.com.pipa.dto.ScoreDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GameControllerTest {

    @SpyBean
    private GameController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCurrentPositionByUser() {
        controller.saveScore(ScoreDTO.builder().userId(1).points(10).build());
        PositionDTO position =  controller.getCurrentPositionByUser(1);
        Assert.assertEquals(10, (int) position.getScore());
    }

    @Test
    public void testGetHighScoreList() {
        ScoreDTO scoreDTO = ScoreDTO.builder().userId(1).points(10).build();
        controller.saveScore(scoreDTO);
        PositionDTO positionDTO = PositionDTO.builder().userId(1).score(10).position(1).build();
        HighScoresDTO highScoresDTO =  controller.getHighScoreList();
        Assert.assertTrue(highScoresDTO.getHighscores().stream().anyMatch(position -> position.getUserId().equals(1)));
    }
}
