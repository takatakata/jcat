package org.jcat.core.stream;

import java.io.IOException;

import org.jcat.core.CatOption;
import org.jcat.core.input.IInput;
import org.jcat.core.output.IOutput;

public abstract class AbstractJCatStream<I extends IInput, O extends IOutput> implements IJCatStream<I, O> {

    protected I input;
    protected O output;
    protected StreamContext context;

    public AbstractJCatStream() {
    }

    public AbstractJCatStream(GlobalContext context, CatOption option, I input, O output) {
        this();
        this.input = input;
        this.output = output;
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
    public void write(String line) throws IOException {
        output.write(line.getBytes(output.getEncode()));
    }

    @Override
    public void writeLine(String line) throws IOException {
        write(line + output.getLineFeed());
    }

    @Override
    public void open() {
    }

    @Override
    public void close() throws IOException {
        input.close();
        output.close();
    }

    @Override
    public I getInput() {
        return this.input;
    }

    @Override
    public O getOutput() {
        return this.output;
    }

    public StreamContext getContext() {
        return context;
    }
}