package org.lework.core.service.yuicompressor;

import org.junit.Ignore;
import org.junit.Test;
import org.lework.core.common.taglib.yui.YUICompressorUtils;

import java.io.*;

/**
 * 测试使用yuicompressor压缩.js,.css
 * User: Gongle
 * Date: 13-11-26
 */
public class YUICompressorTest {
    String inputFilename = "F:/Workspaces-idea/hope-cms/local-aggregator/src/main/webapp/static/assets/css/lework.component.css";
    String outputFilename = "D:/lework.component.min.css";
    String inputFilenameJS = "F:/Workspaces-idea/hope-cms/local-aggregator/src/main/webapp/static/assets/js/jquery-1.10.2.js";
    String outputFilenameJS = "D:/jquery-1.10.2.min.js";
    @Ignore
    @Test
    public void compressorJs() {


        try {
            InputStream input = new FileInputStream(inputFilenameJS);
            OutputStream output = new FileOutputStream(outputFilenameJS);
            YUICompressorUtils.compressJavascript(input, output);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test()
    public void compressorCss() {
        try {
            InputStream input = new FileInputStream(inputFilename);
            OutputStream output = new FileOutputStream(outputFilename);
            YUICompressorUtils.compressStylesheet(input, output);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Ignore
    @Test()
    public void compressorStreamCss() {
        try {
            InputStream input = new FileInputStream(inputFilename);
            OutputStream output = new FileOutputStream(outputFilename);
            YUICompressorUtils.compressStylesheet(input, output);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test()
    public void test() {
        try {
            InputStream in = new SequenceInputStream(new FileInputStream(inputFilenameJS),new FileInputStream(inputFilenameJS));
            YUICompressorUtils.compressJavascript(in, new FileOutputStream(outputFilenameJS));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
