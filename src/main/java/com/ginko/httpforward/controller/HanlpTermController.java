package com.ginko.httpforward.controller;
/* 測試範例
* curl --location 'http://127.0.0.1:8080/api/v0/hanlp' \
--header 'Proxy-Url: http://www.baidu.com' \
--header 'Content-Type: application/json' \
--data '{"word":"你好楊馮凱先生，歡迎使用HanLP！"}'
* */
import com.ginko.httpforward.entity.HanlpTerm;
import com.ginko.httpforward.entity.UfmHanlpDict;
import com.hankcs.hanlp.HanLP;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v0/hanlp", produces = "application/json;charset=UTF-8")
public class HanlpTermController {
    @PostMapping(produces = "application/json;charset=UTF-8")
    public List<HanlpTerm> getWord(@RequestBody UfmHanlpDict requestbody) {
        String sentence = requestbody.getWord();
        System.out.println(HanLP.segment(sentence));
        List<HanlpTerm> resp = HanLP.segment(sentence).stream()
                .map(term -> new HanlpTerm(
                        term.word,
                        term.nature.toString(),
                        term.offset,
                        term.length()
                ))
                .collect(Collectors.toList());

        return resp;
    }
/*
    @PostMapping
    public String addWord(@RequestBody List<UfmHanlpDict> words) {
        // TODO: CustomDictionary.add(entry.getWord() + " " + entry.getNature() + " " + entry.getFrequency());
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
            }
        }
        return "Added";
    }

    @DeleteMapping("/{word}")
    public String deleteWord(@PathVariable String word) {
        // TODO: 移除詞 (可能需要自定義管理，因為 HanLP 原生不支持刪除)
        return "Deleted: " + word;
    }*/
}
