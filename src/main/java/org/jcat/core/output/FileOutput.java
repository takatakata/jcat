package org.jcat.core.output;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileOutput extends AbstractOutput {

    public FileOutput() {
        super();
    }

    public FileOutput(String path) throws FileNotFoundException {
        if ("-".equals(path)) {
            os = new BufferedOutputStream(System.out);
        } else {
            os = new BufferedOutputStream(new FileOutputStream(path));
        }
    }

    public FileOutput(String path, String lineFeed) throws FileNotFoundException {
        this(path);
        setLineFeed(lineFeed);
    }

    public FileOutput(OutputStream bos) throws FileNotFoundException {
        super(bos);
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}
