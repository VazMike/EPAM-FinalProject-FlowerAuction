package com.viazovski.flowerauction.repository;

/**
 * {@code CheckedFunction} is a util interface for a more secure
 * {@link com.viazovski.flowerauction.specification.SqlSpecification}
 * realization where connection isn't passed to a foreign layer, but connection's methods are.
 *
 * @param <T> is the type of a value function accepts.
 * @param <R> is the type of a value function returns.
 * @param <E> is the type of an exception function throws.
 */
public interface CheckedFunction<T, R, E extends Exception> {

    R apply(T t) throws E;
}
