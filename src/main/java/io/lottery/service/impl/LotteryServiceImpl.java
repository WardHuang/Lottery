package io.lottery.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.lottery.api.LotteryApiService;
import io.lottery.bo.BulletinDataSubBo;
import io.lottery.bo.DataInformationSubBo;
import io.lottery.bo.PrizeGradesBo;
import io.lottery.entity.SsqBonusDetailEntity;
import io.lottery.entity.SsqHistoryEntity;
import io.lottery.req.BulletinInformationReq;
import io.lottery.resp.BulletinInformationResp;
import io.lottery.resp.DataInformationResp;
import io.lottery.service.LotteryService;
import io.lottery.service.SsqBonusDetailService;
import io.lottery.service.SsqHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LotteryServiceImpl implements LotteryService {

    @Resource
    private LotteryApiService lotteryApiService;

    @Resource
    private SsqHistoryService ssqHistoryService;

    @Resource
    private SsqBonusDetailService ssqBonusDetailService;


    @Override
    public DataInformationResp getHappy8LotteryWinInformation(BulletinInformationReq req){
        return lotteryApiService.findDrawNotice(req);
    }

    @Override
    public BulletinInformationResp queryDrawNotice(BulletinInformationReq req){
        PageHelper.startPage(req.getPageNo(),req.getPageSize());
        List<SsqHistoryEntity> ssqHistoryEntities = ssqHistoryService.selectAll();
        PageInfo<SsqHistoryEntity> pageInfo = new PageInfo<>(ssqHistoryEntities);
        List<SsqBonusDetailEntity> ssqBonusDetailEntities = ssqBonusDetailService.selectByCode(
                ssqHistoryEntities.stream()
                        .map(SsqHistoryEntity::getCode)
                        .collect(Collectors.toList())
        );
        BulletinInformationResp resp = new BulletinInformationResp();
        resp.setMessage("success");
        Long total = pageInfo.getTotal();
        resp.setTotal(total.intValue());
        resp.setPageNum(pageInfo.getPageNum());
        Map<String,List<PrizeGradesBo>> prizeGradesMap = new HashMap<>();
        ssqBonusDetailEntities.forEach(item ->{
            List<SsqBonusDetailEntity> s = ssqBonusDetailEntities.stream().filter(r -> r.getCode().equals(item.getCode())).collect(Collectors.toList());
            prizeGradesMap.put(
                    item.getCode(),
                    s.stream()
                            .map(t -> new PrizeGradesBo(t.getType(),t.getTypeNum(),t.getTypeMoney()))
                            .collect(Collectors.toList())
            );
        });
        List<BulletinDataSubBo> bulletinDataSubBos = new ArrayList<>();
        for (SsqHistoryEntity ssqHistoryEntity : ssqHistoryEntities){
            BulletinDataSubBo bulletinDataSubBo = new BulletinDataSubBo();
            BeanUtils.copyProperties(ssqHistoryEntity,bulletinDataSubBo);
            bulletinDataSubBo.setPrizeGrades(prizeGradesMap.get(bulletinDataSubBo.getCode()));
            bulletinDataSubBos.add(bulletinDataSubBo);
        }
        resp.setResult(bulletinDataSubBos);
        return resp;
    }
}
