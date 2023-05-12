package org.jcat.core.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileInput extends AbstractInput {

    public FileInput() {
        super();
    }

    public FileInput(String path) throws FileNotFoundException {
        if ("-".equals(path)) {
            is = new BufferedReader(new InputStreamReader(System.in));
        } else {
            is = new BufferedReader(new FileReader(path));
        }
    }

    public FileInput(InputStream is) throws FileNotFoundException {
        this.is = new BufferedReader(new InputStreamReader(is));
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}
