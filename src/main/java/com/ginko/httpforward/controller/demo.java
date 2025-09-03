package com.ginko.httpforward.controller;

import com.ginko.httpforward.HttpForwardApplication;
import com.ginko.httpforward.entity.IdmUser;
import com.ginko.httpforward.entity.UfmHanlpDict;
import com.ginko.httpforward.repository.IdmUserRepository;
import com.ginko.httpforward.repository.UfmHanlpDictRepository;
import com.hankcs.hanlp.HanLP;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class demo {
    private static UfmHanlpDictRepository repo;
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
        /*ApplicationContext ctx = SpringApplication.run(HttpForwardApplication.class, args);
        UfmHanlpDictRepository repo = ctx.getBean(UfmHanlpDictRepository.class);
        Date now = new Date(); // java.util.Date
        List<UfmHanlpDict> list = repo.findTrDateAfter(now);
        for (UfmHanlpDict dict : list) {
            System.out.println(dict.getWord());
        }*/

    }
}
