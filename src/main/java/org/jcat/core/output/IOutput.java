package org.jcat.core.output;

import java.io.Closeable;
import java.io.IOException;

public interface IOutput extends Closeable {
    public void write(byte[] data) throws IOException;
    
    public void write(String line) throws IOException;

    public void writeLine(String line) throws IOException;

    public void close() throws IOException;

    public String setEncode(String encode);

    public String getEncode();

    public void setLineFeed(String lineFeed);

    public String getLineFeed();
}
