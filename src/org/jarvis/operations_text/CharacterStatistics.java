package org.jarvis.operations_text;

/**
 * 类：characterstatistics
 * 作用：统计某个字符在一段字符串中的出现次数
 */

public class CharacterStatistics {
    /* 参数说明：
     * inputStrintCharacter:字符串
     * inputCharacter:字符
     *
     */
    public String getCharacterStatistics(String inputStrintCharacter, String inputCharacter) {
        String retrurnData = "";
        int count = 0;
        int start = 0;
        while (inputStrintCharacter.indexOf(inputCharacter, start) >= 0 && start < inputStrintCharacter.length()) {
            count++;
            start = inputStrintCharacter.indexOf(inputCharacter, start) + inputCharacter.length();

        }
        System.out.println(inputCharacter + "," + count);
        retrurnData = inputCharacter + "," + count;

        return retrurnData;

    }
}
