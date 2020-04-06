package br.com.pipa.dto;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HighScoresDTO {
    LinkedHashSet<PositionDTO> highscores = new LinkedHashSet<>();

    public void setHighScores(LinkedHashSet<PositionDTO> highscores, int count){
        this.highscores = highscores.stream().limit(count).collect(Collectors.toCollection(LinkedHashSet::new));
    }
}