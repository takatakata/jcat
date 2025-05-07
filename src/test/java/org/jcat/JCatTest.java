package org.jcat;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for JCat.
 */
public class JCatTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public JCatTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(JCatTest.class);
    }

    /**
     * Help。
     * 
     * @throws IOException 
     */
    public void testUsageHelp() throws IOException {
        String options[] = new String[] { "--help" };
        String result[] = retreiveCatAndJCat(options);
        assertNotSame(result[0], result[1]);
    }

    /**
     * Version。
     * 
     * @throws IOException 
     */
    public void testUsageVersion() throws IOException {
        String options[] = new String[] { "--version" };
        String result[] = retreiveCatAndJCat(options);
        assertNotSame(result[0], result[1]);
    }

    /**
     * オプションなし。
     * 
     * @throws IOException 
     */
    public void testNothingOptions() throws IOException {
        String options[] = new String[] {};
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 行番号（短縮オプション）。
     * 
     * @throws IOException 
     */
    public void testShowNumberShortOption() throws IOException {
        String options[] = new String[] { "-n" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 行番号（詳細オプション）。
     * 
     * @throws IOException 
     */
    public void testShowNumberLongOption() throws IOException {
        String options[] = new String[] { "--number" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 行番号ブランク以外（短縮オプション）。
     * 
     * @throws IOException 
     */
    public void testShowNumberNonblankShortOption() throws IOException {
        String options[] = new String[] { "-b" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 行番号ブランク以外（詳細オプション）。
     * 
     * @throws IOException 
     */
    public void testShowNumberNonblankLongOption() throws IOException {
        String options[] = new String[] { "--number-nonblank" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 行番号連続空行圧縮（短縮オプション）。
     * 
     * @throws IOException 
     */
    public void testShowNumberSqueezeShortOption() throws IOException {
        String options[] = new String[] { "-s" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 行番号連続空行圧縮（詳細オプション）。
     * 
     * @throws IOException 
     */
    public void testShowNumberSqueezeLongOption() throws IOException {
        String options[] = new String[] { "--squeeze-blank" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * タブ（短縮オプション）。
     * 
     * @throws IOException 
     */
    public void testShowTabsShortOption() throws IOException {
        String options[] = new String[] { "-t" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * タブ（詳細オプション）。
     * 
     * @throws IOException 
     */
    public void testShowTabsLongOption() throws IOException {
        String options[] = new String[] { "--show-tabs" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 行末（短縮オプション）。
     * 
     * @throws IOException 
     */
    public void testShowEndsShortOption() throws IOException {
        String options[] = new String[] { "-E" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 行末（詳細オプション）。
     * 
     * @throws IOException 
     */
    public void testShowEndsLongOption() throws IOException {
        String options[] = new String[] { "--show-ends" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 制御文字（短縮オプション）。
     * 
     * @throws IOException 
     */
    public void testShowNonprintingShortOption() throws IOException {
        String options[] = new String[] { "-v" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 制御文字（詳細オプション）。
     * 
     * @throws IOException 
     */
    public void testShowNonprintingLongOption() throws IOException {
        String options[] = new String[] { "--show-nonprinting" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 組み合わせ「-A」。
     * 
     * @throws IOException 
     */
    public void testComplexAOption() throws IOException {
        String options[] = new String[] { "-A" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 組み合わせ「-e」。
     * 
     * @throws IOException 
     */
    public void testComplexEOption() throws IOException {
        String options[] = new String[] { "-e" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 組み合わせ「-T」。
     * 
     * @throws IOException 
     */
    public void testComplexTOption() throws IOException {
        String options[] = new String[] { "-T" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 組み合わせ「-vET」。
     * 
     * @throws IOException 
     */
    public void testComplexVETOption() throws IOException {
        String options[] = new String[] { "-vET" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 組み合わせ「-vE」。
     * 
     * @throws IOException 
     */
    public void testComplexVEOption() throws IOException {
        String options[] = new String[] { "-vE" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * 組み合わせ「-vT」。
     * 
     * @throws IOException 
     */
    public void testComplexVTOption() throws IOException {
        String options[] = new String[] { "-vT" };
        String result[] = retreiveCatAndJCat(options);
        assertEquals(result[0], result[1]);
    }

    /**
     * cat、jcatそれぞれ実行して結果を返却する。
     * 
     * @param options コマンドオプション
     * @return 期待値（catの結果）、実際の値（jcatの結果）
     * @throws IOException
     */
    private String[] retreiveCatAndJCat(String options[]) throws IOException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        String result[] = new String[2];
        List<String> optionList = new ArrayList<>();
        optionList.addAll(Arrays.asList(options));
        optionList.addAll(Arrays.asList(new String[] { "./sample1.txt", "./sample2.txt", "./sample3.txt", "./sample4.txt" }));
        JCat app = new JCat(optionList.toArray(new String[optionList.size()]), bao);
        app.exec();
        bao.flush();
        String catOutput = execAndGetCatCommand(optionList.toArray(new String[optionList.size()]));
        String jcatOutput = bao.toString();
        result[0] = catOutput;
        result[1] = jcatOutput;
        return result;
    }

    /**
     * catコマンドを実行して結果を返す。
     * 
     * @param options コマンドオプション
     * @return catコマンドの結果
     * @throws IOException
     */
    private String execAndGetCatCommand(String options[]) throws IOException {

        // 1. ProcessBuilderインスタンスを生成する
        List<String> commands = new ArrayList<>();
        commands.addAll(Arrays.asList(new String[] { "wsl.exe", "cat" }));
        commands.addAll(Arrays.asList(options));
        ProcessBuilder p = new ProcessBuilder(commands.toArray(new String[commands.size()]));

        // 2. プロセスを開始する
        Process process = p.start();

        // 3. 結果を受け取る
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try (BufferedReader r = new BufferedReader(
                new InputStreamReader(process.getInputStream(), Charset.defaultCharset()))) {
            char[] charbuf = new char[1024];
            int len;
            while ((len = r.read(charbuf)) > 0) {
                bao.write(new String(charbuf, 0, len).getBytes("UTF-8"));
            }
        }
        return bao.toString();
    }
}
