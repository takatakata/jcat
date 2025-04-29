package org.jcat.input;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class StandardInput extends AbstractInput {

    public StandardInput() throws FileNotFoundException {
        super(new InputStreamReader(System.in));
    }
}
