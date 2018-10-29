package com.hundsun.votesystem.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class VoteUtils {

    public static void kuayuSolution(HttpServletRequest request, HttpServletResponse response){
            String origin = request.getHeader("Origin");
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Methods","GET,POST,PUT,PATCH,OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With, Access-Control");
            response.setHeader("Allow","POST,GET");
            response.setHeader("Access-Control-Allow-Credentials","true");
    }
    //将参数中带逗号的str转换为list
    public static List<Integer> str2Integerlist(String str){
        List<Integer> list=new ArrayList<>();
        String[] strs=str.split(",");
        for(String s:strs)
            list.add(Integer.parseInt(s));
        return list;
    }

    //将参数中带逗号的str转换为list
    public static List<String> str2Stringlist(String str){
        List<String> list=new ArrayList<>();
        String[] strs=str.split(",");
        for(String s:strs)
            list.add(s);
        return list;
    }
}
