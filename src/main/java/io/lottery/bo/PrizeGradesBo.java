package io.lottery.bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrizeGradesBo implements Serializable {

    private String type;

    private String typeNum;

    private String typeMoney;
}
