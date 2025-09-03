package com.ginko.httpforward.Service;

import com.ginko.httpforward.entity.UfmHanlpDict;
import com.ginko.httpforward.repository.UfmHanlpDictRepository;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class HanlpCustomDictService {

    private final UfmHanlpDictRepository repo;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public HanlpCustomDictService(UfmHanlpDictRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void initCustomDictionary() {
        loadWords(repo.findAll(), null); // 初始化時沒有過濾時間
    }

    // 每30秒掃描一次
    @Scheduled(cron = "0/30 * * * * ?")
    public void initCustomDictionaryBySchedule() {
        Date thirtySecondsAgo = new Date(System.currentTimeMillis() - 30 * 1000);
        List<UfmHanlpDict> words = repo.findTrDateAfter(thirtySecondsAgo);
        loadWords(words, thirtySecondsAgo);
    }

    /**
     * 加載詞到自定義詞典
     *
     * @param words      要加載的詞列表
     * @param filterTime 過濾時間，如果為 null 表示初始化加載
     */
    private void loadWords(List<UfmHanlpDict> words, Date filterTime) {
        int count = 0;
        for (UfmHanlpDict dict : words) {
            if (!"99".equals(dict.getHanlpDictStatus())) {
                String nature = dict.getNature() != null ? dict.getNature() : "nz";
                int freq = 0;
                try {
                    freq = dict.getFrequency() != null ? Integer.parseInt(dict.getFrequency()) : 0;
                } catch (NumberFormatException ignored) {
                }
                CustomDictionary.insert(dict.getWord(), nature + " " + freq);
                count++;
            }
        }
        String msg = "自定義詞典加載完成, 共 " + count + " 條詞 (當前時間: " + sdf.format(new Date()) + ")";
        if (filterTime != null) {
            msg += " (過濾時間: " + sdf.format(filterTime) + ")";
        }
        System.out.println(msg);
    }
}
