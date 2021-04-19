package org.jarvis.operations;

import java.io.*;

/**
 * 存储临时文件内容
 *
 */
public class TemporaryFiles {

    private static File file = null;
    private static OutputStream outputStream = null;
    private static OutputStreamWriter outputStreamWriter = null;
    private static BufferedWriter bufferedWriter = null;

    /**
     * 存储临时文件内容；如：文本日志。
     * @param msg
     */
    public void stLog(Object msg) {
        try {
            file = new File("/Users/zhangyibin/Downloads/log.txt");
            outputStream = new FileOutputStream(file, true);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(String.valueOf(msg) + "\r\n");

            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
    public static void main(String[] args) throws Exception {
        TemporaryFiles temporaryFiles=new TemporaryFiles();

        temporaryFiles.stLog("1");
        temporaryFiles.stLog("2");

    }


}
