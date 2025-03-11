package io.lottery.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulletinDataSubBo {

    private String code;

    private String gameCode;

    private String gameName;

    private String sales;

    private String week;

    private String poolMoney;

    private String bulletinDate;

    private Integer redBall1;

    private Integer redBall2;

    private Integer redBall3;

    private Integer redBall4;

    private Integer redBall5;

    private Integer redBall6;

    private Integer blueBall1;

    private Integer blueBall2;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime insertDate;

    private String content;

    List<PrizeGradesBo> prizeGrades;
}
