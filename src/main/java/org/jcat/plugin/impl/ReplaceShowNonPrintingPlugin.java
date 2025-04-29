package org.jcat.plugin.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jcat.context.FileContext;
import org.jcat.plugin.AbstractReplacePlugin;

public class ReplaceShowNonPrintingPlugin extends AbstractReplacePlugin {

    public ReplaceShowNonPrintingPlugin() {
    	super();
    }

    @Override
    public String replaceLine(FileContext context, String src) {
    	return convertAsBytes(src);
    }
    
	@Override
	public boolean isEnabled() {
		return option.isShowNonPrinting();
	}

    private String convertAsBytes(String src) {
        StringBuffer buf = new StringBuffer();
        byte[] bytes;
        try {
            bytes = src.getBytes("UTF-8");
            try (InputStream input = new ByteArrayInputStream(bytes)) {
                int ch;
                while ((ch = input.read()) != -1) {
                    if (ch >= 32) {
                        if (ch < 127) {
                            buf.appendCodePoint(ch);
                        } else if (ch == 127) {
                            buf.append("^?");
                        } else {
                            if (ch >= 128 + 32) {
                                if (ch < 128 + 127) {
                                    buf.append("M-");
                                    buf.appendCodePoint(ch - 128);
                                } else {
                                    buf.append("^?");
                                }
                            } else {
                                buf.append("M-");
                                buf.append("^").appendCodePoint(ch - 128 + 64);
                            }
                        }
                    } else if (ch == '\t' && !option.isShowTabs()) {
                        buf.append("\t");
                    } else if (ch == '\n') {
                        buf.append("\n");
                    } else {
                        buf.append("^").appendCodePoint(ch + 64);
                    }
                }
            }
            return buf.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return src;
        }
    }

    /**
     * https://github.com/coreutils/coreutils/blob/master/src/cat.c
     *   // If quoting, i.e., at least one of -v, -e, or -t specified,
     *   //   scan for chars that need conversion.
     *   if (show_nonprinting)
     *     {
     *       while (true)
     *         {
     *           if (ch >= 32)
     *             {
     *               if (ch < 127)
     *                 *bpout++ = ch;
     *               else if (ch == 127)
     *                 {
     *                   *bpout++ = '^';
     *                   *bpout++ = '?';
     *                 }
     *               else
     *                 {
     *                   *bpout++ = 'M';
     *                   *bpout++ = '-';
     *                   if (ch >= 128 + 32)
     *                     {
     *                       if (ch < 128 + 127)
     *                         *bpout++ = ch - 128;
     *                       else
     *                         {
     *                           *bpout++ = '^';
     *                           *bpout++ = '?';
     *                         }
     *                     }
     *                   else
     *                     {
     *                       *bpout++ = '^';
     *                       *bpout++ = ch - 128 + 64;
     *                     }
     *                 }
     *             }
     *           else if (ch == '\t' && !show_tabs)
     *             *bpout++ = '\t';
     *           else if (ch == '\n')
     *             {
     *               newlines = -1;
     *               break;
     *             }
     *           else
     *             {
     *               *bpout++ = '^';
     *               *bpout++ = ch + 64;
     *             }
     *
     *           ch = *bpin++;
     *         }
     *     }
     */

//	private String convertAsCodepoint(String src) {
//	    StringBuffer buf = new StringBuffer();
//        try (IntStream input = src.codePoints()) {
//            input.forEach(ch -> {
//                if (ch >= 32) {
//                    if (ch < 127) {
//                        buf.appendCodePoint(ch);
//                    } else if (ch == 127) {
//                        buf.append("^?");
//                    } else {
//                        if (ch >= 128 + 32) {
//                            if (ch < 128 + 127) {
//                                buf.append("M-");
//                                buf.appendCodePoint(ch - 128);
//                            } else {
//                                // treat ch as a Unicode character, not just an ASCII character 
//                                // original code: `buf.append("^?")`
//                                buf.appendCodePoint(ch);
//                            }
//                        } else {
//                            buf.append("M-");
//                            buf.append("^").appendCodePoint(ch - 128 + 64);
//                        }
//                    }
//                } else if (ch == '\t' && !option.isShowTabs()) {
//                    buf.append("\t");
//                } else if (ch == '\n') {
//                    buf.append("\n");
//                } else {
//                    buf.append("^").appendCodePoint(ch + 64);
//                }
//            });
//        }
//        return buf.toString();
//	}
}
