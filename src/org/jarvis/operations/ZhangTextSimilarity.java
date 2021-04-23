package org.jarvis.operations;

import com.baidu.aip.nlp.AipNlp;
import org.apdplat.word.analysis.*;
import org.apdplat.word.segmentation.Word;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * 文本相似度计算
 * 说明：底层使用百度文本相似度计算能力
 */
public class ZhangTextSimilarity {
    //设置APPID/AK/SK
    private final String APP_ID = "17147276";
    private final String API_KEY = "fa0rFXjcuyK5RViDRlohaW3U";
    private final String SECRET_KEY = "ust3R8uLBUOAwprhGqxyesFuGxAy9fs7";

    /**
     * 百度文本相似度计算
     *
     * @param text1
     * @param text2
     * @return
     */
    public String getBaiDuTextSimilarity(String text1, String text2) {
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("model", "CNN");

        // 短文本相似度
        JSONObject res = client.simnet(text1, text2, options);
        return res.toString(2);
    }


    /**
     * 文本相似度计算判定方式：SimHash +汉明距离（汉明距离）
     * @param text1
     * @param text2
     * @return
     */
    public Double getWordTextSimilarity(String text1, String text2) {
        SimHashPlusHammingDistanceTextSimilarity simHashPlusHammingDistanceTextSimilarity =
                new SimHashPlusHammingDistanceTextSimilarity();
        double score = simHashPlusHammingDistanceTextSimilarity.similarScore(text1,text2);
        return score;

    }

    public static void main(String[] args) throws Exception {
        //"score": 0.773647,
        System.out.println(new ZhangTextSimilarity().getWordTextSimilarity("世茂集团控股有限公司(香港)", "上海世茂股份有限公司"));


    }
}
