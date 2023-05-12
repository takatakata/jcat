package org.jcat.core.output;

import java.io.IOException;

public interface IOutput {
    public void write(byte[] data) throws IOException;

    public void close() throws IOException;

    public String setEncode(String encode);

    public String getEncode();

    public void setLineFeed(String lineFeed);

    public String getLineFeed();
}
