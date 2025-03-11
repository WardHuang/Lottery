package io.lottery.resp;

import io.lottery.bo.DataInformationSubBo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataInformationResp {

    private Integer state;

    private String message;

    private Integer total;

    private Integer pageNum;

    private Integer pageSize;

    private Integer Tflag;

    private List<DataInformationSubBo> result;
}
