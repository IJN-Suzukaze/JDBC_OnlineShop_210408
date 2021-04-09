package com.IJN.util;

import java.util.Random;

public class RandomCode {
    public String randCode(){
        Random lenRand = new Random();
        Random rand = new Random();
        String codePool = "012345679abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int poolLength = codePool.length();

        int strLen = lenRand.nextInt(4)+4;  //生成随机长度
        char codeSeq[] = new char[strLen];          //存放随机字符串的数组
        for(int i=0;i<strLen;i++){
            int index = rand.nextInt(poolLength);   //生成随机字符或数字
            codeSeq[i] = codePool.charAt(index);    //随机字符或数字插入数组中
        }
       return new String(codeSeq);          //字符数组构建验证码字符串

    }

}
