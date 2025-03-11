package io.lottery.service.impl;


import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 太高了
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class testImpl {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();

        Integer sumNumber = 33;
        Integer groupNumber = 6;
        Integer groupLists = 6;
        Boolean ssqFlag = Boolean.TRUE;

        Random r = new Random();

        for (int i = 1 ; i <= sumNumber; i ++){
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        List<List<Integer>> groups = new ArrayList<>();

        for (int i = 0; i < groupLists; i++){
            List<Integer> groupNumbers = new ArrayList<>();

            for (int j = 0; j < groupNumber ; j++){
                if( (i * 6 + j) > 32 && ssqFlag){
                    groupNumbers.add(numbers.get( r.nextInt(33)  ));
                    continue;
                }
                groupNumbers.add(numbers.get(i * groupNumber + j));
            }
            groups.add(groupNumbers.stream().sorted().collect(Collectors.toList()));
        }

        for (int i = 0; i < groups.size() ; i ++){
            System.out.println("第 " + (i + 1 ) + " 组："+groups.get(i));
        }
    }

}
