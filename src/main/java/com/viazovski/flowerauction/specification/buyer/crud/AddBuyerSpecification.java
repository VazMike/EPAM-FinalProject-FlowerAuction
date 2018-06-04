package com.viazovski.flowerauction.specification.buyer.crud;

import com.viazovski.flowerauction.model.Buyer;
import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBuyerSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "INSERT INTO `buyer` " +
            "(`first_name`, `last_name`, `dob`, `login`, `password_hash`, `password_salt`, `lang`, `role`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private Buyer buyer;

    public AddBuyerSpecification(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        var preparedStatement = statementGetter.apply(QUERY);
        fillWithValues(preparedStatement);
        return preparedStatement;
    }

    private void fillWithValues(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, buyer.getFirstName());
        preparedStatement.setString(2, buyer.getLastName());
        preparedStatement.setDate(3, buyer.getDateOfBirth());
        preparedStatement.setString(4, buyer.getLogin());
        preparedStatement.setString(5, buyer.getPasswordHash());
        preparedStatement.setString(6, buyer.getPasswordSalt());
        preparedStatement.setString(7, buyer.getLanguage().toString());
        preparedStatement.setString(8, buyer.getRole().toString());
    }
}
