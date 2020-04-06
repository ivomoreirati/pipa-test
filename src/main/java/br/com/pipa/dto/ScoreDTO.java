package br.com.pipa.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"userId"})
public class ScoreDTO {
    Integer userId;
    Integer points;
}
