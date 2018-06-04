package com.viazovski.flowerauction.repository;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.model.id.Id;
import com.viazovski.flowerauction.specification.SqlSpecification;

import java.util.List;

/**
 * {@code Repository} is a basic repository interface filled with CRUD operations
 * and more elaborate operations for working with a database.
 * @param <T>
 */
public interface Repository<T> {

    Id<T> add(T item) throws RepositoryException;

    void remove(T item) throws RepositoryException;

    void update(T item) throws RepositoryException;

    List<T> selectAll() throws RepositoryException;

    void nonQuery(SqlSpecification spec) throws RepositoryException;

    List<T> query(SqlSpecification spec) throws RepositoryException;
}
