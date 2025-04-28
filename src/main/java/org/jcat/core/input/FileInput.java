package org.jcat.core.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileInput extends AbstractInput {

    public FileInput() {
        super();
    }

    public FileInput(InputStreamReader is) throws FileNotFoundException {
        super(new BufferedReader(is));
    }

    public FileInput(InputStream is) throws FileNotFoundException {
        super(new BufferedReader(new InputStreamReader(is)));
    }

    public FileInput(String path) throws FileNotFoundException {
        this("-".equals(path) ? new InputStreamReader(System.in) : new FileReader(path));
    }
}
