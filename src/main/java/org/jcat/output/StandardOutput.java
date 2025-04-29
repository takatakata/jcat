package org.jcat.output;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;

public class StandardOutput extends AbstractOutput {

    public StandardOutput() throws FileNotFoundException {
        super(new BufferedOutputStream(System.out));
    }

    public StandardOutput(String lineFeed) throws FileNotFoundException {
        this();
        setLineFeed(lineFeed);
    }
}
