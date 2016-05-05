package jwf.debugport.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;

import bsh.ConsoleInterface;

/**
 *
 */
class TelnetConsoleInterface implements ConsoleInterface, Closeable {
    private final InputStream mIn;
    private final OutputStream mOut;
    private InputStreamReader mReader;
    private PrintStream mPrintStream;

    public TelnetConsoleInterface(InputStream inputStream, OutputStream outputStream) {
        mIn = inputStream;
        mOut = outputStream;
    }

    @Override
    public Reader getIn() {
        if (mReader == null) {
            mReader = new InputStreamReader(mIn);
        }
        return mReader;
    }

    @Override
    public PrintStream getOut() {
        if (mPrintStream == null) {
            mPrintStream = new PrintStream(mOut, true);
        }
        return mPrintStream;
    }

    @Override
    public PrintStream getErr() {
        return getOut();
    }

    @Override
    public void println(Object o) {
        getOut().println(o == null ? "null" : o.toString());
    }

    @Override
    public void print(Object o) {
        getOut().print(o == null ? "null" : o.toString());
    }

    @Override
    public void error(Object o) {
        getErr().print(o == null ? "null" : o.toString());
    }

    @Override
    public void close() throws IOException {
        mIn.close();
        mOut.close();
    }
}
