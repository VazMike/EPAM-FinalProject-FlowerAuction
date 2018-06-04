package com.viazovski.flowerauction.repository;

import com.viazovski.flowerauction.connection.ConnectionPool;
import com.viazovski.flowerauction.exception.ConnectionException;
import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.model.id.Id;
import com.viazovski.flowerauction.specification.SelectLastInsertIdSpecification;
import com.viazovski.flowerauction.specification.SqlSpecification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code AbstractRepository} implements {@link Repository} interface. It serves as a bridge
 * between database and app. It contains similar elements for processing data. The code
 * related to the specifics of a model must be implemented in subclasses.
 * @param <T>
 */
abstract class AbstractRepository<T> implements Repository<T> {

    /**
     * Creates model instance of T based on values retrieved from the database.
     *
     * @param resultSet is the data collected from T table describing it's whole row.
     * @return model instance of T.
     * @throws SQLException if values aren't retrieved from resultSet successfully.
     */
    abstract T takeItem(ResultSet resultSet) throws SQLException;

    /**
     * Creates Id instance of model of type T.
     *
     * @param resultSet is the data collected from T table describing it's PK.
     * @return model T's id.
     * @throws SQLException  if values aren't retrieved from resultSet successfully.
     */
    abstract Id<T> takeId(ResultSet resultSet) throws SQLException;

    /**
     * Modifies table describing T.
     *
     * @param spec contains non select script.
     * @throws RepositoryException if transaction wasn't successful.
     */
    @Override
    public final void nonQuery(SqlSpecification spec) throws RepositoryException {
        try (var connection = ConnectionPool.getInstance().acquireConnection();
             var preparedStatement = spec.getStatement(connection::prepareStatement)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Unable to execute non-query", e);
        } catch (ConnectionException e) {
            throw new RepositoryException("Unable to create connection", e);
        }
    }

    /**
     * Retrieves values from table describing T.
     *
     * @param spec contains select script.
     * @return list of model T instances.
     * @throws RepositoryException if transaction wasn't successful.
     */
    @Override
    public final List<T> query(SqlSpecification spec) throws RepositoryException {
        try (var connection = ConnectionPool.getInstance().acquireConnection();
             var preparedStatement = spec.getStatement(connection::prepareStatement)) {
            var resultSet = preparedStatement.executeQuery();
            var items = new ArrayList<T>();
            while (resultSet.next()) {
                items.add(takeItem(resultSet));
            }
            return items;
        } catch (SQLException e) {
            throw new RepositoryException("Unable to execute query", e);
        } catch (ConnectionException e) {
            throw new RepositoryException("Unable to create connection", e);
        }
    }

    /**
     * {@code nonQueryId} is used specifically with add scripts to retrieve newly generated
     * autoincremented ids.
     *
     * @param spec contains add script.
     * @return model T's id
     * @throws RepositoryException if transaction wasn't successful.
     */
    Id<T> nonQueryId(SqlSpecification spec) throws RepositoryException {
        SqlSpecification selectIdSpec = new SelectLastInsertIdSpecification();
        try (var connection = ConnectionPool.getInstance().acquireConnection();
             var preparedStatement = spec.getStatement(connection::prepareStatement);
             var preparedStatementId = selectIdSpec.getStatement(connection::prepareStatement)) {
            preparedStatement.execute();
            var resultSet = preparedStatementId.executeQuery();
            return takeId(resultSet);
        } catch (SQLException e) {
            throw new RepositoryException("Unable to execute non-query-id", e);
        } catch (ConnectionException e) {
            throw new RepositoryException("Unable to create connection", e);
        }
    }
}
