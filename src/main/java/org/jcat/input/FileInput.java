package org.jcat.input;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileInput extends AbstractInput {

    public FileInput() {
        super();
    }

    public FileInput(InputStreamReader is) throws FileNotFoundException {
        super(is);
    }

    public FileInput(InputStream is) throws FileNotFoundException {
        super(new InputStreamReader(is));
    }

    public FileInput(String path) throws FileNotFoundException {
        this("-".equals(path) ? new InputStreamReader(System.in) : new FileReader(path));
    }
}
