package com.ginko.httpforward.utils;
import com.hankcs.hanlp.HanLP;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class LanguageUtils {

    private static LanguageDetector languageDetector;
    private static TextObjectFactory textObjectFactory;

    static {
        try {
            List<LanguageProfile> languageProfiles = new LanguageProfileReader().readAllBuiltIn();
            languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
                    .withProfiles(languageProfiles)
                    .build();
            textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回語言代碼，例如 zh-CN / zh-TW / en / ja / ko / unknown
     */
    public static String detectLanguageCode(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "unknown";
        }

        // 先判斷是否含中文
        if (text.matches(".*[\\u4e00-\\u9fff].*")) { // 含中文
            String toSimp = HanLP.convertToSimplifiedChinese(text);
            String toTrad = HanLP.convertToTraditionalChinese(text);

            if (text.equals(toSimp)) {
                return "zh-CN"; // 全部是簡體
            } else if (text.equals(toTrad)) {
                return "zh-TW"; // 全部是繁體
            } else {
                return "zh"; // 混合簡繁
            }
        }

        // 對非中文文字用 language-detector 判斷
        TextObject textObject = textObjectFactory.forText(text);
        List<com.optimaize.langdetect.DetectedLanguage> probabilities = languageDetector.getProbabilities(textObject);

        if (probabilities.isEmpty()) {
            return "unknown";
        }

        Optional<com.optimaize.langdetect.DetectedLanguage> bestOpt = probabilities.stream()
                .max(Comparator.comparingDouble(com.optimaize.langdetect.DetectedLanguage::getProbability));

        // 可設定閾值避免短文本誤判
        if (bestOpt.isPresent() && bestOpt.get().getProbability() > 0.6) {
            return bestOpt.get().getLocale().getLanguage();
        } else {
            return "unknown";
        }
    }

    public static void main(String[] args) {
        String text = "Prefer English for common phrases";
        System.out.println(detectLanguageCode(text)); // zh-CN 或 zh-TW
    }
}
