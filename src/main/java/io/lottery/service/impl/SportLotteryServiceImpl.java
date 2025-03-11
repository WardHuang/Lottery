package io.lottery.service.impl;


import io.lottery.entity.LuckNum;
import io.lottery.mapper.LuckNumMapper;
import io.lottery.req.GetLuckReq;
import io.lottery.service.SportLotteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SportLotteryServiceImpl implements SportLotteryService {

    @Resource
    private LuckNumMapper luckNumMapper ;
    @Override
    public void getLuckNum(Integer group,String str,Integer selectorNum) {
        String[] s = str.split(",");
        calc(Arrays.stream(s).map(Integer::parseInt).collect(Collectors.toList()),group,selectorNum);
    }

    @Override
    public void getLuckNum(GetLuckReq req) {
        req.getLuckNums().forEach(item->{
            getLuckNum(item.getNum(),item.getLuckNum(), req.getSelectorNum());
        });
    }

    private static void generateCombinations(List<Integer> numbers, int r, int start, List<String> current, List<List<String>> result) {
        if (current.size() == r) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < numbers.size(); i++) {
            String s = "\"" + numbers.get(i) + "\"";
            current.add(s);
            generateCombinations(numbers, r, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    private void calc(List<Integer> numbers,Integer group,Integer selectorNum){
        System.out.println("开始计算。。。。");
        int r = 6;  // 每组选择的数字

        List<List<String>> combinations = new ArrayList<>();
        generateCombinations(numbers, selectorNum, 0, new ArrayList<>(), combinations);

        List<LuckNum> luckNums = new ArrayList<>();

        int count = 0;

        // 输出所有组合
        for (List<String> combo : combinations) {
            LuckNum luckNum = new LuckNum();
            luckNum.setNum(group);
            luckNum.setLuckNum(combo.toString());
            luckNums.add(luckNum);
            count++;
            if(luckNums.size() > 100 || count == combinations.size()){
                luckNumMapper.insertBatch(luckNums);
                //System.out.println(luckNums);
                luckNums.clear();
            }
        }

        // 输出组合数量
        System.out.println("Total combinations: " + combinations.size());
    }
}
