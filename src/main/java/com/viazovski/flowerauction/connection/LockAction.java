package com.viazovski.flowerauction.connection;

import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * {@code LockAction} is a helper class to hide ugly try-finally clauses
 * and write compact thread-safe code using locks. The class contains
 * functional interfaces allowing non-standard functions to be passed to
 * method wrappers.
 */
class LockAction {

    private ReentrantLock lock = new ReentrantLock();

    /**
     * Accepts functions taking no parameters and returning nothing.
     */
    interface VoidFunction {

        void apply();
    }

    /**
     * Accepts supplier functions throwing SQL exceptions.
     *
     * @param <T> is a whatever value produced by a function.
     */
    interface CheckedSqlSupplier<T> {

        T get() throws SQLException;
    }

    void apply(VoidFunction function) {
        lock.lock();
        try {
            function.apply();
        } finally {
            lock.unlock();
        }
    }

    <T> T get(Supplier<T> supplier) {
        lock.lock();
        try {
            return supplier.get();
        } finally {
            lock.unlock();
        }
    }

    <T> T sqlGet(CheckedSqlSupplier<T> supplier) throws SQLException {
        lock.lock();
        try {
            return supplier.get();
        } finally {
            lock.unlock();
        }
    }
}
