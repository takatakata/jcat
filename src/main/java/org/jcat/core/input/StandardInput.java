package org.jcat.core.input;

import java.io.FileNotFoundException;
import java.io.IOException;

public class StandardInput extends FileInput {

    public StandardInput() throws FileNotFoundException {
        super();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}
