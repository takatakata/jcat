package org.jcat.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class AbstractInput implements IInput {

    protected String encode = "UTF-8";
    protected BufferedReader is;
    protected StringBuilder buffer = new StringBuilder();
    protected boolean streamFinished = false;

    public AbstractInput() {
    }

    public AbstractInput(InputStreamReader is) {
        this.is = new BufferedReader(is);
    }

    /**
     * is.readLine()の代わりにLF改行単位で読み込む。
     */
    @Override
    public String read() throws IOException {
        String line = null;
        while (true) {
            //バッファーから1行取り出す（バッファーに複数行含まれているときはここが繰り返される）
            line = pickoutLine();
            if (line != null) {
                break;
            }
            //バッファーを使い切ったらファイルを読みとりバッファーに加える
            int len = appendBuffer();
            if (len <= 0) {
                //ファイル読み込みが0バイト以下になったら読み込みを終える
                streamFinished = true;
                break;
            }
        }
        //ファイル末端がLFでないバッファーの余りを出力
        if (line == null && streamFinished && buffer.length() > 0) {
            line = buffer.toString();
            buffer = new StringBuilder("");
        }
        return line;
    }

    /**
     * ファイルから所定文字数読み込み、バッファーへ追加する。
     * @throws IOException 
     */
    private int appendBuffer() throws IOException {
        char[] charbuf = new char[1024];
        int len = is.read(charbuf);
        if (len > 0) {
            buffer.append(charbuf, 0, len);
        }
        return len;
    }

    /*
     * LFを探索しLFが見つかったら行頭からLFの1つ前までの文字列を返却する。
     * LFが見つからなかったらnullを返す。
     * バッファーをLFの1つ先に進める。
     */
    private String pickoutLine() {
        int newlineIndex = buffer.indexOf("\n");
        if (newlineIndex > -1) {
            int begin = 0;
            int end = newlineIndex + 1;//LF文字を含めた終末位置
            String line = buffer.substring(begin, end);
            int startNew = end;
            int length = buffer.length();
            if (length > startNew) {
                buffer = new StringBuilder(buffer.substring(startNew, length));
            } else if (length == startNew) {
                //バッファー末尾がLFの場合はバッファーをクリアする
                buffer = new StringBuilder("");
            }
            return line;
        }
        return null;
    }

    @Override
    public String getEncode() {
        return encode;
    }

    @Override
    public String setEncode(String encode) {
        return this.encode = encode;
    }

    @Override
    public void close() throws IOException {
        is.close();
    }
}
