package org.jcat.input;

import java.io.Closeable;
import java.io.IOException;

public interface IInput extends Closeable {

    public String read() throws IOException;

    public void close() throws IOException;

    public String setEncode(String encode);

    public String getEncode();
}
