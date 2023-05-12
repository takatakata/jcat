package org.jcat.core.output;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public abstract class AbstractOutput implements IOutput {

    protected String encode = "UTF-8";
    protected String lineFeed = "\n";
    protected OutputStream os;

    public AbstractOutput() {
        os = new BufferedOutputStream(System.out);
    }

    public AbstractOutput(OutputStream bos) throws FileNotFoundException {
        os = bos;
    }

    @Override
    public void write(byte[] data) throws IOException {
        os.write(data);
    }

    @Override
    public void close() throws IOException {
        os.flush();
        os.close();
    }

    @Override
    public String setEncode(String encode) {
        return this.encode = encode;
    }

    @Override
    public String getEncode() {
        return encode;
    }

    @Override
    public String getLineFeed() {
        return lineFeed;
    }

    @Override
    public void setLineFeed(String lineFeed) {
        this.lineFeed = lineFeed;
    }
}
