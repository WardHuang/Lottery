package io.lottery.service.impl;

import io.lottery.entity.LuckNum;
import io.lottery.mapper.LuckNumMapper;
import io.lottery.req.GetLuckReq;
import io.lottery.strategy.DealDataTStrategy;
import lombok.AllArgsConstructor;
import okhttp3.Interceptor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;


@AllArgsConstructor
@Component
public class DealDataTStrategyImpl<T> implements DealDataTStrategy<T> {


//    private T instance;
//
//    private Class<T> type;

    private static final Object lock = new Object();
    private static int count = 0;
    @Override
    public void execDealTData(List<T> result) {
//        Class<?> clazz = instance.getClass();
//        Field[] fields = clazz.getFields();
//        T t = null;
//        try {
//            t = type.getDeclaredConstructor().newInstance();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);
        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }

    public static int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }
    private static final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        
//        Enumeration<URL> urls = classLoader.getResources("/META-INF/services/" + "DealDataTStrategyImpl");
//        while (urls.hasMoreElements()){
//            URL url = urls.nextElement();
//
//            URLConnection urlConnection = url.openConnection();
//            urlConnection.setUseCaches(false);
//            InputStream inputStream = urlConnection.getInputStream();
//
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String className = bufferedReader.readLine();
//
//            while(className != null){
//                Class<?> clazz = Class.forName(className,false,classLoader);
//                if(ser)
//            }
//        }
        return false;
    }


    public boolean isChineseCharacter(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(c);
            if (unicodeBlock != Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS &&
                    unicodeBlock != Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS &&
                    unicodeBlock != Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Map<Integer,Integer> countMap = new HashMap<>();
        System.out.println("输入：");
        while(true) {
            String lineStr = scanner.nextLine();
            String[] strs = lineStr.split(",");
            if(strs[0].equals("q")){
                break;
            }
            for (String str : strs) {

                Integer temp = Integer.valueOf(str);
                if (countMap.containsKey(temp)) {
                    Integer count = countMap.get(temp);
                    count += 1;
                    countMap.put(temp, count);
                } else {
                    countMap.put(temp, 1);
                }

            }

        }
        countMap.keySet().stream().collect(Collectors.toList()).forEach(r-> {
            System.out.printf("%d 出现了 %d 次 \n",r,countMap.get(r));
        });
        Map<Integer,List<Integer>> countMap1 = new HashMap<>();
        countMap.values().stream().distinct().sorted().collect(Collectors.toList()).forEach(r->{
            List<Integer> countNum = new ArrayList<>();
            for(Map.Entry entry : countMap.entrySet()){
                if (entry.getValue() == r ){
                    countNum.add((Integer) entry.getKey());
                }
            }
            countMap1.putIfAbsent(r,countNum);
        });
        System.out.println("==============================================");
        countMap1.keySet().stream().collect(Collectors.toList()).forEach(r-> {
            for (Integer i : countMap1.get(r)){
                System.out.printf("%d 出现了 %d 次 \n",i,r);
            }
        });
    }
}
