package org.jarvis.desktop;

import org.jarvis.operations.RARExtractor;

import java.io.File;

public class Unzip {

    public static void main(String[] args) throws Exception {
        File sourceRar=new File("/Users/zhangyibin/Downloads/8月产品资料同步.rar");
        File destDir=new File("/Users/zhangyibin/Downloads/8月产品资料同步/");
        RARExtractor.getUntieRARFile(sourceRar,destDir);

    }

}
