package io.lottery.service.impl;

import io.lottery.api.LotteryApiService;
import io.lottery.req.BulletinInformationReq;
import io.lottery.resp.DataInformationResp;
import io.lottery.service.GetSsqDataService;
import io.lottery.strategy.DealDataStrategy;
import io.lottery.strategy.DealDataStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Slf4j
@Service
public class GetSsqDataServiceImpl implements GetSsqDataService {

    @Resource
    private LotteryApiService lotteryApiService;

    @Resource
    private DealDataStrategyFactory dealDataStrategyFactory;

    @Override
    public void getAllSsqData() {

        BulletinInformationReq req = new BulletinInformationReq();
        Integer pageNo = 0;
        Integer pageSize = 100;
        Long total = 0L;
        Long pageNum = 0L;
        do {
            req.setPageSize(pageSize);
            req.setName("ssq");
            req.setSystemType("PC");
            try{
                pageNo++ ;
                req.setPageNo(pageNo);
                DataInformationResp resp = lotteryApiService.findDrawNotice(req);
                pageNum = resp.getPageNum().longValue();
                if(resp.getTotal().equals(total)){
                    //break;
                }
                log.info("已获取：{}条数据",pageSize);
                DealDataStrategy dealDataStrategy = dealDataStrategyFactory.getDealDataStrategy(req.getName());
                dealDataStrategy.execDealData(resp.getResult());
            } catch (Exception e){
                e.printStackTrace();
                log.error("获取数据不一致：{}",e.getMessage());
            }
            try {
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }

        }while (pageNum > pageNo);
    }
}
