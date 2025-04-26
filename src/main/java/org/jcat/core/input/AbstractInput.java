package org.jcat.core.input;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class AbstractInput implements IInput {

    protected String encode = "UTF-8";
    protected BufferedReader is;

    public AbstractInput() {
    }

    public AbstractInput(BufferedReader is) {
    	this.is = is;
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
