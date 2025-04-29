package org.jcat.output;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileOutput extends AbstractOutput {

    public FileOutput() {
        super();
    }

    public FileOutput(String path) throws FileNotFoundException {
    	super(new BufferedOutputStream("-".equals(path) ? System.out : new FileOutputStream(path)));
    }

    public FileOutput(OutputStream bos) throws FileNotFoundException {
        super(bos);
    }
    
    public FileOutput(String path, String lineFeed) throws FileNotFoundException {
        this(path);
        setLineFeed(lineFeed);
    }

    public FileOutput(OutputStream bos, String lineFeed) throws FileNotFoundException {
        super(bos);
        setLineFeed(lineFeed);
    }
}
