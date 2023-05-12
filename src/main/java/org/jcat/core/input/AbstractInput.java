package org.jcat.core.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class AbstractInput implements IInput {

    protected String encode = "UTF-8";
    protected BufferedReader is;

    public AbstractInput() {
        is = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String read() throws IOException {
        return is.readLine();
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
