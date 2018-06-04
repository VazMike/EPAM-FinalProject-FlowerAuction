package com.viazovski.flowerauction.repository;

import java.sql.SQLException;

/**
 * {@code SqlCheckedFunction} is a special case of a {@link CheckedFunction}.
 *
 * @param <T> is the type of a value function accepts.
 * @param <R> is the type of a value function returns.
 */
public interface SqlCheckedFunction<T, R> extends CheckedFunction<T, R, SQLException> {
}
