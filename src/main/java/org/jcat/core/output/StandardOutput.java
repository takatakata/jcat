package org.jcat.core.output;

import java.io.FileNotFoundException;
import java.io.IOException;

public class StandardOutput extends FileOutput {

    public StandardOutput() throws FileNotFoundException {
        super();
    }

    public StandardOutput(String lineFeed) throws FileNotFoundException {
        this();
        setLineFeed(lineFeed);
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}
