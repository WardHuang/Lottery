package io.lottery.bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataInformationBo implements Serializable {
    private Integer state;

    private String message;

    private Long total;

    private Integer pageNo;

    private Integer pageNum;

    private Integer pageSize;

    private Integer Tflag;

    private List<DataInformationSubBo> result;
}
