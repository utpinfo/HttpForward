package com.ginko.httpforward.controller;
/* 測試範例
* curl --location 'http://127.0.0.1:8080/api/v0/hanlp' \
--header 'Proxy-Url: http://www.baidu.com' \
--header 'Content-Type: application/json' \
--data '{"word":"你好楊馮凱先生，歡迎使用HanLP！"}'
* # 方法比較
* HanLP.segment() → “只切词 + 标词性”
* NLPTokenizer.segment() → “切词 + 标词性 + NER + 时间/地点识别”
* */

import com.ginko.httpforward.entity.HanlpTerm;
import com.ginko.httpforward.entity.UfmHanlpDict;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v0/hanlp", produces = "application/json;charset=UTF-8")
public class HanlpTermController {
    @PostMapping(produces = "application/json;charset=UTF-8")
    public List<HanlpTerm> getWord(@RequestBody UfmHanlpDict requestbody) {
        String sentence = requestbody.getWord();
        sentence = sentence.replaceAll("\\s+", "");//避免多餘空白, 導致解析異常
        //System.out.println(HanLP.segment(sentence));
        //List<HanlpTerm> resp = HanLP.segment(sentence).stream()
        List<HanlpTerm> resp = NLPTokenizer.segment(sentence).stream()
                .map(term -> {
                    // 正則檢測日期 (2025-01-05 / 2025/01/05 / 20250105 / 2025年01月05)
                    if (term.word.matches("\\d{4}([-/.年])\\d{1,2}([-/.月])\\d{1,2}日?")
                            || term.word.matches("\\d{8}")) {
                        term.nature = com.hankcs.hanlp.corpus.tag.Nature.t; // 強制改成時間詞
                    }

                    return new HanlpTerm(
                            term.word,
                            term.nature.toString(),
                            term.offset,
                            term.length()
                    );
                })
                .collect(Collectors.toList());

        return resp;
    }
}
