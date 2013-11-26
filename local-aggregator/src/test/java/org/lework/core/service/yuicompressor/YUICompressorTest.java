package org.lework.core.service.yuicompressor;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import java.io.*;

/**
 * 测试使用yuicompressor压缩.js,.css
 * User: Gongle
 * Date: 13-11-26
 */
public class YUICompressorTest {
    @Test
    public void compressorCss() {
        Reader in = null;
        Writer out = null;
        String inputFilename = "F:/Workspaces-idea/hope-cms/local-aggregator/src/main/webapp/static/assets/js/jquery-1.10.2.js";
        String outputFilename = "D:/jquery-1.10.2.min.js";
        Options o = new Options();
        try {
            in = new InputStreamReader(new FileInputStream(inputFilename), o.charset);

            JavaScriptCompressor compressor = new JavaScriptCompressor(in, new YuiCompressorErrorReporter());
            in.close();
            in = null;

            out = new OutputStreamWriter(new FileOutputStream(outputFilename), o.charset);
            compressor.compress(out, o.lineBreakPos, o.munge, o.verbose, o.preserveAllSemiColons, o.disableOptimizations);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }

    public static class Options {
        public String charset = "UTF-8";
        public int lineBreakPos = -1;
        public boolean munge = true;
        public boolean verbose = false;
        public boolean preserveAllSemiColons = false;
        public boolean disableOptimizations = false;
    }

    private static class YuiCompressorErrorReporter implements ErrorReporter {
        public void warning(String message, String sourceName, int line, String lineSource, int lineOffset) {
            if (line < 0) {
                System.out.println("message = [" + message + "], sourceName = [" + sourceName + "], line = [" + line + "], lineSource = [" + lineSource + "], lineOffset = [" + lineOffset + "]");
            }
        }

        public void error(String message, String sourceName, int line, String lineSource, int lineOffset) {
            if (line < 0) {
                System.out.println("message = [" + message + "], sourceName = [" + sourceName + "], line = [" + line + "], lineSource = [" + lineSource + "], lineOffset = [" + lineOffset + "]");
            }
        }

        public EvaluatorException runtimeError(String message, String sourceName, int line, String lineSource, int lineOffset) {
            error(message, sourceName, line, lineSource, lineOffset);
            return new EvaluatorException(message);
        }
    }
}
