package com.hundsun.votesystem.util;

import org.apache.log4j.Logger;

public class Log4jTest{

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Log4jTest.class);

        //logger使用的方法:
        //1.直接打印字符串
        logger.info("this is an info");
        logger.warn("this is a warn");
        logger.error("this is an error");
        logger.fatal("this is a fatal");

        //2.字符串和变量混合使用
        String user = "Tom";
        logger.info("当前登录用户user为" + user + "!" );
    }
}