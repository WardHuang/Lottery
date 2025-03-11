package io.lottery.bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataInformationSubBo implements Serializable {

    private String name;

    private String code;

    private String detailsLink;

    private String videoLink;

    private String date;

    private String week;

    private String red;

    private String blue;

    private String blue2;

    private String sales;

    private String poolMoney;

    private String content;

    private String addMoney;

    private String addMoney2;

    private String msg;

    private String z2add;

    private String m2add;

    private List<PrizeGradesBo> prizeGrades;
}
