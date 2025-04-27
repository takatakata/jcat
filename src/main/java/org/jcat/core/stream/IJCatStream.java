package org.jcat.core.stream;

import java.io.IOException;

import org.jcat.core.context.StreamContext;
import org.jcat.core.input.IInput;

public interface IJCatStream<I extends IInput> {
    public I getInput();

    public void open();

    public void close() throws IOException;

    public boolean next() throws IOException;

    public boolean hasNext();

    public String readLine() throws IOException;

    public StreamContext getContext();
}
