package com.viazovski.flowerauction.specification.buyer;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateBuyerProfileByLoginSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "UPDATE `buyer` " +
                    "SET `first_name` = ?, `last_name` = ?, `dob` = ? " +
                    "WHERE `login` = ?";

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String login;

    public UpdateBuyerProfileByLoginSpecification(String firstName, String lastName, Date dateOfBirth, String login) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.login = login;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        var preparedStatement = statementGetter.apply(QUERY);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setDate(3, dateOfBirth);
        preparedStatement.setString(4, login);
        return preparedStatement;
    }
}
