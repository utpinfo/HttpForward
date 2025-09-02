package com.ginko.httpforward.controller;

import com.ginko.httpforward.entity.IdmUser;
import com.ginko.httpforward.repository.IdmUserRepository;
import com.hankcs.hanlp.HanLP;

import java.io.IOException;

public class demo {
    private static IdmUserRepository repo;
    public static void main(String[] args) throws IOException {
        //CustomDictionary.add("楊馮凱", "nr 1024");
        System.out.println(HanLP.segment("你好楊馮凱先生，歡迎使用HanLP！"));


        /*String url = "jdbc:oracle:thin:@192.168.70.21:1521/mis.gs.com.cn";
        String user = "prg";
        String password = "prg7695";

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Oracle 連線成功！");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
