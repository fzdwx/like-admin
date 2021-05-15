package com.sika.code.gen;


import com.sika.code.gen.expand.GenerateCodeHandler;
import com.sika.code.gen.expand.GeneratorCodeDTO;

/**
 * 代码生成器启动类
 *
 * @author daiqi
 */
public class GenerateCodeApplication {
    private static String author = "like @email:980650920@qq.com";

    public static void main(String[] args) {
        String[] tableNames = {
                "t_user", "t_role", "t_user_role", "t_notice"
        };
        for (String tableName : tableNames) {
            GeneratorCodeDTO generatorCodeDTO = GenerateCodeConfig.buildGeneratorCodeDTO(tableName, author);
            new GenerateCodeHandler().generateCode(generatorCodeDTO);
        }
    }
}
