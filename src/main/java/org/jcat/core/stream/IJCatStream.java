package org.jcat.core.stream;

import java.io.Closeable;
import java.io.IOException;

import org.jcat.core.input.IInput;
import org.jcat.core.output.IOutput;

public interface IJCatStream<I extends IInput, O extends IOutput> extends Closeable {
    public O getOutput();

    public I getInput();

    public void open();

    public void close() throws IOException;

    public boolean next() throws IOException;

    public boolean hasNext();

    public String readLine() throws IOException;

    public void write(String line) throws IOException;

    public void writeLine(String line) throws IOException;

    public StreamContext getContext();
}
