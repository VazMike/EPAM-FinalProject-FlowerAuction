package com.viazovski.flowerauction.specification.flower;

import com.viazovski.flowerauction.repository.SqlCheckedFunction;
import com.viazovski.flowerauction.specification.SqlSpecification;
import org.intellij.lang.annotations.Language;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class UpdateFlowerAuctionIdAndAcceptedSpecification implements SqlSpecification {

    @Language("SQL")
    private static final String QUERY =
            "UPDATE `flower` " +
            "SET `auction_id` = ?, `flower_accepted` = ? " +
            "WHERE `flower_id` = ?";

    private int flowerId;

    private Integer auctionId;

    private boolean isAccepted;

    public UpdateFlowerAuctionIdAndAcceptedSpecification(int flowerId, Integer auctionId, boolean isAccepted) {
        this.flowerId = flowerId;
        this.auctionId = auctionId;
        this.isAccepted = isAccepted;
    }

    @Override
    public PreparedStatement getStatement(SqlCheckedFunction<String, PreparedStatement> statementGetter) throws SQLException {
        var preparedStatement = statementGetter.apply(QUERY);
        fillWithValues(preparedStatement);
        return preparedStatement;
    }

    private void fillWithValues(PreparedStatement preparedStatement) throws SQLException {
        if (auctionId != null) {
            preparedStatement.setInt(1, auctionId);
        } else {
            preparedStatement.setNull(1, Types.INTEGER);
        }
        preparedStatement.setBoolean(2, isAccepted);
        preparedStatement.setInt(3, flowerId);
    }


}
