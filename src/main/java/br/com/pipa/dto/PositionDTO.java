package br.com.pipa.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {
    Integer userId;
    Integer score;
    Integer position;

    public static PositionDTO parse(ScoreDTO score, int position){
        return PositionDTO.builder().userId(score.getUserId()).score(score.getPoints()).position(position).build();
    }
}
