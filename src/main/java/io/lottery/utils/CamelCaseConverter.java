package io.lottery.utils;

import java.util.ArrayList;
import java.util.List;

public class CamelCaseConverter {
    private static final List<String> dictionary = new ArrayList<>();
    static {
        dictionary.add("money");
        dictionary.add("num");
        dictionary.add("grades");
    }

    public static String toCamelCase(String input) {
        StringBuilder camelCaseString = new StringBuilder();
        int i = 0;
        char[] inputChar = input.toCharArray();
        // 在输入字符串中匹配字典中的单词
        while (i < input.length()) {
            String matchedWord = matchWordFromIndex(input, i);
            if (matchedWord != null) {
                if (camelCaseString.length() == 0) {
                    camelCaseString.append(matchedWord);  // 第一个单词小写
                } else {
                    camelCaseString.append(capitalize(matchedWord));  // 其余单词首字母大写
                }
                i += matchedWord.length();  // 移动索引
            } else {
                // 如果没有匹配到字典中的单词，继续前进
                camelCaseString.append(inputChar[i]);
                i++;
            }
        }

        return camelCaseString.length() == 0? input:camelCaseString.toString();
    }

    // 从索引 i 开始匹配字典中的单词
    private static String matchWordFromIndex(String input, int index) {
        for (String word : dictionary) {
            if (input.startsWith(word, index)) {
                return word;
            }
        }
        return null;
    }

    // 将单词的首字母大写
    private static String capitalize(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
