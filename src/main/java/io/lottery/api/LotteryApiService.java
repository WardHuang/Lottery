package io.lottery.api;


import io.lottery.config.FeignConfig;
import io.lottery.req.BulletinInformationReq;
import io.lottery.resp.BulletinInformationResp;
import io.lottery.resp.DataInformationResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "io.lottery", url = "${win.endpoint.lottery}",configuration = FeignConfig.class)
public interface LotteryApiService {


    @GetMapping(value = "/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice")
    DataInformationResp findDrawNotice(@SpringQueryMap BulletinInformationReq req);

    @GetMapping("/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice")
    String findDrawNotice(@RequestParam("pageNo") int pageNo,
                          @RequestParam("systemType") String systemType,
                          @RequestParam("name") String name,
                          @RequestParam("pageSize") int pageSize);
}
