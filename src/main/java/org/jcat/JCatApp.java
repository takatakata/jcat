package org.jcat;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jcat.core.CatOption;
import org.jcat.core.input.FileInput;
import org.jcat.core.output.StandardOutput;
import org.jcat.core.stream.IJCatStream;
import org.jcat.core.stream.JCatStream;
import org.jcat.plugin.PluginHolder;

/**
 * JCatApp
 */
public class JCatApp {
    private static final int EXIT_SUCCESS = 0;
    private static final int EXIT_FAILURE = 1;

    private static final String HELP_MESSAGE =
        "Usage: java -cp /path/to/classes org.jcat.JCatApp [OPTION]... [FILE]...\n"+
        "Concatenate FILE(s) to standard output.\n"+
        "\n"+
        "With no FILE, or when FILE is -, read standard input.\n"+
        "\n"+
        "  -A, --show-all           equivalent to -vET (**Not yet supported)\n"+
        "  -b, --number-nonblank    number nonempty output lines, overrides -n\n"+
        "  -e                       equivalent to -vE (**Not yet supported)\n"+
        "  -E, --show-ends          display $ at end of each line\n"+
        "  -n, --number             number all output lines\n"+
        "  -s, --squeeze-blank      suppress repeated empty output lines\n"+
        "  -t                       equivalent to -vT (**Not yet supported)\n"+
        "  -T, --show-tabs          display TAB characters as ^I\n"+
        "  -u                       (ignored)\n"+
        "  -v, --show-nonprinting   use ^ and M- notation, except for LFD and TAB (**Not yet supported)\n"+
        "      --help     display this help and exit\n"+
        "      --version  output version information and exit\n"+
        "\n"+
        "Examples:\n"+
        "  java -cp /path/to/classes org.jcat.JCatApp f - g  Output f's contents, then standard input, then g's contents.\n"+
        "  java -cp /path/to/classes org.jcat.JCatApp        Copy standard input to standard output.";

    private static final String VERSION =
        "jcat 1.0-SNAPSHOT\n"+
        "Copyright (C) 2023 takatakata.\n"+
        "License GPLv3+: GNU GPL version 3 or later <https://gnu.org/licenses/gpl.html>.\n"+
        "This is free software: you are free to change and redistribute it.\n"+
        "There is NO WARRANTY, to the extent permitted by law.\n"+
        "            \n"+
        "Written by takatakata.";

    private CatOption option;
    private StandardOutput output;
    private PluginHolder plugins;

    public JCatApp(String[] args) throws FileNotFoundException {
        this.option = new CatOption(args);
        this.output = new StandardOutput("\r\n");
        this.plugins = new PluginHolder(option);
    }

    public void exec() throws IOException {
        if (option.isShowHelp()) {
            System.out.println(HELP_MESSAGE);
            return;
        }
        if (option.isShowVersion()) {
            System.out.println(VERSION);
            return;
        }
        if (option.getFileList().isEmpty()) {
            option.getFileList().add("-");
        }
        for (String path : option.getFileList()) {
            FileInput input = new FileInput(path);
            try (IJCatStream<FileInput, StandardOutput> stream = new JCatStream<FileInput, StandardOutput>(this.option, input, this.output)) {
                while (stream.next()) {
                    String line = stream.readLine();
                    line = plugins.processPlugins(stream.getContext(), line);
                    if (line == null) {
                        continue;
                    }
                    stream.writeLine(line);
                    stream.getContext().incrementLineNumOutput();
                }
            } catch (IOException e) {
                throw e;
            }
        }
    }

    public static void main(String[] args) {
        try {
            JCatApp cat = new JCatApp(args);
            cat.exec();
            System.exit(EXIT_SUCCESS);
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(EXIT_FAILURE);
        }
    }
}
