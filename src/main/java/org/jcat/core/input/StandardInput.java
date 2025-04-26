package org.jcat.core.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class StandardInput extends AbstractInput {

    public StandardInput() throws FileNotFoundException {
        super(new BufferedReader(new InputStreamReader(System.in)));
    }
}
