package com.ginko.httpforward.Service;
/**
 * 動態 hanlp.properties
 */
import com.hankcs.hanlp.HanLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.Paths;
import java.util.Properties;

@Component
public class HanlpDynamicRootConfig {

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void init() throws Exception {
        // 動態設定 HanLP root
        String hanlpRoot;

        // 嘗試讀 classpath:data
        Resource resource = context.getResource("classpath:");
        if (resource.exists()) {
            hanlpRoot = Paths.get(resource.getURI()).toAbsolutePath().toString();
        } else {
            // fallback: 使用當前工作目錄
            hanlpRoot = Paths.get("").toAbsolutePath().toString();
        }
        System.setProperty("hanlp.root", hanlpRoot);
        System.out.println("HanLP root 設定為: " + hanlpRoot);
        HanLP.Config.CoreDictionaryPath = hanlpRoot + "/data/dictionary/CoreNatureDictionary.txt";
        HanLP.Config.CustomDictionaryPath = new String[]{hanlpRoot + "/data/dictionary/custom/人名词典.txt"};
    }
}
