package io.lottery.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulletinInformationReq implements Serializable {

    private Integer pageNo;

    private Integer pageSize;

    private String name;

    private String systemType;

}
