package com.ginko.httpforward.Service;
/*
 * 啟動自動調用數據庫分詞
 * */

import com.ginko.httpforward.entity.UfmHanlpDict;
import com.ginko.httpforward.repository.UfmHanlpDictRepository;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class HanlpCustomDictService {

    private final UfmHanlpDictRepository repo;

    public HanlpCustomDictService(UfmHanlpDictRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void initCustomDictionary() {
        int i = 0;
        List<UfmHanlpDict> words = repo.findAll();
        for (UfmHanlpDict dict : words) {
            // 只加入啟用的詞（假設 hanlpDictStatus = "Y" 表示啟用）
            if (!"99".equals(dict.getHanlpDictStatus())) {
                String nature = dict.getNature() != null ? dict.getNature() : "nz"; // 預設詞性
                int freq = 0;
                try {
                    freq = dict.getFrequency() != null ? Integer.parseInt(dict.getFrequency()) : 0;
                } catch (NumberFormatException e) {
                    // 如果頻率不合法就用 0
                }
                // 添加到 CustomDictionary
                CustomDictionary.add(dict.getWord(), nature + " " + freq);
                //System.out.println("加入詞：" + dict.getWord() + " " + nature + " " + freq);
                i++;
            }
        }
        System.out.println("自定義詞典加載完成，共 " + i + " 條詞");
    }
}
