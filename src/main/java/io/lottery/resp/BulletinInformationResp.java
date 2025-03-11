package io.lottery.resp;


import io.lottery.bo.BulletinDataSubBo;
import io.lottery.bo.DataInformationSubBo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulletinInformationResp implements Serializable {

    private String message;

    private Integer total;

    private Integer pageNum;

    private Integer pageSize;

    private List<BulletinDataSubBo> result;
}
