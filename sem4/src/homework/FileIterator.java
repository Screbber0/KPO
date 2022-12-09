package homework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

class FileIterator implements Iterator<String> {
    private final BufferedReader bufferedReader;
    private boolean isClosed = false;
    private String cacheResult;
    private boolean isHasUncheckedCache = false;

    private IOException lastException;

    public FileIterator(String path) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(path));
    }

    @Override
    public boolean hasNext() {
        if (isHasUncheckedCache) {
            return true;
        }
        ensureOpen();
        try {
            cacheResult = bufferedReader.readLine();
            if (cacheResult == null) {
                bufferedReader.close();
                isClosed = true;
                return false;
            }
            isHasUncheckedCache = true;
        } catch (IOException e) {
            lastException = e;
            return false;
        }
        return true;
    }

    @Override
    public String next() {
        if (isHasUncheckedCache) {
            isHasUncheckedCache = false;
            return cacheResult;
        }
        ensureOpen();
        String result = null;
        try {
            result = bufferedReader.readLine();
            if (result == null) {
                bufferedReader.close();
                isClosed = true;
            }
        } catch (IOException e) {
            lastException = e;
        }
        if (result == null) {
            throw new NoSuchElementException("No line found");
        }
        return result;
    }

    private void ensureOpen() {
        if (isClosed) {
            throw new IllegalStateException("Scanner closed");
        }
    }

    public IOException getLastException() {
        return lastException;
    }
}