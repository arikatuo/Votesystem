package com.hundsun.votesystem.util;

import java.util.ArrayList;
import java.util.List;

public class VoteUtils {
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
