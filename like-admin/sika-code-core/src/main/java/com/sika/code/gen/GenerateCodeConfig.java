package com.sika.code.gen;


import com.sika.code.gen.expand.GeneratorCodeDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daiqi
 * @create 2019-06-07 8:48
 */
@Slf4j
public class GenerateCodeConfig {

    /** 路径配置 */
    private static String projectPrefix = "";
    private static String basePackagePathPrefixForJava = "src/main/java/cn/like/code/server/business";
    private static String basePackagePathPrefixForXml = "src/main/resources/mapper";
    private static String modulePackage = "cn.like.code.server";

    /** 数据库配置 */
    private static String tablePrefix = "t_";
    private static String dataBaseUrl = "127.0.0.1";
    private static String dataBaseName = "like-admin";
    private static String dataBasePort = "3306";
    private static String username = "root";
    private static String password = "root";

    public static GeneratorCodeDTO buildGeneratorCodeDTO(String tableName, String author) {
        GeneratorCodeDTO generatorCodeDTO = new GeneratorCodeDTO();

        /** 父包 */
        generatorCodeDTO.setParentPackage(modulePackage + ".business")
                .setModulePackage(modulePackage)
                /** common的path */
                .setPathForDataaccess(buildFullPathForJava("gen"))
                /** common类的path */
                .setPathForCommon(buildFullPathForJava("gen"))
                /** service类path */
                .setPathForCore(buildFullPathForJava("gen"))
                /** web的path */
                .setPathForWeb(buildFullPathForJava("gen"))
                /** api的path */
                .setPathForApi(buildFullPathForJava("gen"))
                /** xml的path */
                .setPathForXml(buildFullPathForXml("gen"))

                .setDataBaseUrl(dataBaseUrl)
                .setDataBaseName(dataBaseName)
                .setDataBasePort(dataBasePort)
                .setUsername(username)
                .setPassword(password)

                /** 表名 */
                .setTableName(tableName)
                /** 表前缀 */
                .setSubPrefix(tablePrefix)
                /** 作者 */
                .setAuthor(author);

        return generatorCodeDTO;
    }

    private static String buildFullPathForJava(String projectSuffix) {
        StringBuilder stringBuilder = buildFullPathCore(projectSuffix, basePackagePathPrefixForJava);
        log.info(projectSuffix + "的全路径为：" + stringBuilder);
        return stringBuilder.toString();
    }

    private static String buildFullPathForXml(String projectSuffix) {
        StringBuilder stringBuilder = buildFullPathCore(projectSuffix, basePackagePathPrefixForXml);
        log.info(projectSuffix + "的全路径为：" + stringBuilder);
        return stringBuilder.toString();
    }

    protected static StringBuilder buildFullPathCore(String projectSuffix, String basePackagePathPrefix) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/")
                // .append(projectPrefix)
                // .append("-")
                .append(projectSuffix)
                .append("/")
                .append(basePackagePathPrefix);
        return stringBuilder;
    }
}
