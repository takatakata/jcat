package org.jcat.core.stream;

import java.io.IOException;

import org.jcat.core.CatOption;
import org.jcat.core.context.GlobalContext;
import org.jcat.core.context.StreamContext;
import org.jcat.core.input.IInput;

public abstract class AbstractJCatStream<I extends IInput> implements IJCatStream<I> {

    protected I input;
    protected StreamContext context;

    public AbstractJCatStream() {
    }

    public AbstractJCatStream(GlobalContext context, CatOption option, I input) {
        this();
        this.input = input;
        this.context = new StreamContext(context, option);
    }

    @Override
    public boolean next() throws IOException {
        if (context.isSeekFinished()) {
            //バッファーは空なので何もしない
            return false;
        } else if (context.getLineNumInput() == 0) {
            String line = input.read();
            if (line == null) {
                context.setSeekFinished(true);
                return false;
            } else {
                context.incrementLineNumInput();
                context.setLineCurrent(line);
                line = input.read();
                if (line == null) {
                    context.setLineNext(null);
                    context.setSeekFinished(true);
                } else {
                    context.incrementLineNumInput();
                    context.setLineNext(line);
                }
                return true;
            }
        } else {
            //Next→Currentへバッファーから読み込んだテキストを→Nextへシフトする
            String line = input.read();
            context.setLineCurrent(context.getLineNext());
            context.setLineNext(line);
            if (line != null) {
                context.incrementLineNumInput();
                return true;
            } else {
                //バッファーからテキストが読み込めなかった（空だった）⇒前回Nextを使って最後の処理を行わせる
                context.setSeekFinished(true);
                return true;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !context.isSeekFinished();
    }

    @Override
    public String readLine() throws IOException {
        return context.getLineCurrent();
    }

    @Override
    public void open() {
    }

    @Override
    public void close() throws IOException {
        input.close();
    }

    @Override
    public I getInput() {
        return this.input;
    }

    public StreamContext getContext() {
        return context;
    }
}