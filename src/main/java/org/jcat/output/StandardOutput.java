package org.jcat.output;

import java.io.FileNotFoundException;

public class StandardOutput extends AbstractOutput {

    public StandardOutput() throws FileNotFoundException {
        super(System.out);
    }

    public StandardOutput(String lineFeed) throws FileNotFoundException {
        this();
        setLineFeed(lineFeed);
    }
}
