package org.jcat.core.stream;

import org.jcat.core.CatOption;
import org.jcat.core.input.IInput;
import org.jcat.core.output.IOutput;

public class JCatStream<I extends IInput, O extends IOutput> extends AbstractJCatStream<I, O> {

    public JCatStream() {
        super();
    }

    public JCatStream(CatOption option, I input, O output) {
        super(option, input, output);
    }
}
