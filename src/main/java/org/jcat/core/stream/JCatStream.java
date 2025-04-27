package org.jcat.core.stream;

import org.jcat.core.CatOption;
import org.jcat.core.context.GlobalContext;
import org.jcat.core.input.IInput;

public class JCatStream<I extends IInput> extends AbstractJCatStream<I> {

    public JCatStream() {
        super();
    }

    public JCatStream(GlobalContext context, CatOption option, I input) {
        super(context, option, input);
    }
}
