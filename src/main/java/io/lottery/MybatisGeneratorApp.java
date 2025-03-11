package io.lottery;


import org.mybatis.generator.api.ShellRunner;

public class MybatisGeneratorApp {

    public static void main(String[] args) {
//        注意 windows 使用 "\\"，每次执行会覆盖文件，执行前检查好！
//        注意 windows 使用 "\\"，每次执行会覆盖文件，执行前检查好！
//        注意 windows 使用 "\\"，每次执行会覆盖文件，执行前检查好！
        args = new String[]{"-configfile", "src/main/resources/generatorConfig.xml", "-overwrite"};
        ShellRunner.main(args);
    }
}
