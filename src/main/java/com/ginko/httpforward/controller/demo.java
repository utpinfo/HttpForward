package com.ginko.httpforward.controller;

import com.ginko.httpforward.HttpForwardApplication;
import com.ginko.httpforward.entity.IdmUser;
import com.ginko.httpforward.entity.UfmHanlpDict;
import com.ginko.httpforward.repository.IdmUserRepository;
import com.ginko.httpforward.repository.UfmHanlpDictRepository;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

public class demo {
    private static UfmHanlpDictRepository repo;

    public static void main(String[] args) throws IOException {
        String hanlpRoot = "./src/main/resources";
        System.setProperty("hanlp.root", hanlpRoot);
        System.out.println("HanLP root 設定為: " + hanlpRoot);
        HanLP.Config.CoreDictionaryPath = hanlpRoot + "/data/dictionary/CoreNatureDictionary.txt";
        HanLP.Config.CustomDictionaryPath = new String[]{hanlpRoot + "/data/dictionary/custom/人名词典.txt"};
        //CustomDictionary.add("楊馮凱", "nr 1024");
        String sentence = "查询莊承豪, 2025-01-01到2025-01-30的对账单";
        sentence = sentence.replaceAll("\\s+", "");
        for (Term term : NLPTokenizer.segment(sentence)) {
            if (term.word.matches("\\d{4}([-/.年])\\d{1,2}([-/.月])\\d{1,2}日?")
                    || term.word.matches("\\d{8}")) {
                term.nature = com.hankcs.hanlp.corpus.tag.Nature.t;
            }

            System.out.printf("word=%s, nature=%s%n", term.word, term.nature);
        }

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
