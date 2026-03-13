package nz.h4t.common.utils;

import java.util.concurrent.locks.ReentrantLock;

/**
 * A custom lock implementation that extends {@link ReentrantLock} and provides additional functionality.
 * This class simplifies the usage of locks by implementing the {@link AutoCloseable} interface,
 * allowing it to be used with try-with-resources statements for better resource management and
 * ensuring locks are always properly released.
 */
public class MyLock extends ReentrantLock implements AutoCloseable {
    public MyLock lockBlock() {
        lock();
        return this;
    }

    @Override
    public void close() {
        unlock();
    }
}