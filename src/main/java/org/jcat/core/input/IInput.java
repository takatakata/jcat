package org.jcat.core.input;

import java.io.IOException;

public interface IInput {
    public String read() throws IOException;

    public void close() throws IOException;

    public String setEncode(String encode);

    public String getEncode();
}
