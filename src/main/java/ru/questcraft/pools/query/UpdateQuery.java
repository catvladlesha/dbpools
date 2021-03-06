package ru.questcraft.pools.query;

import lombok.NonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateQuery extends AbstractQuery {

    public UpdateQuery(@NonNull String query, @NonNull Object... objects) {
        this.query = query;
        this.objects = objects;
    }

    private final String query;
    private final Object[] objects;

    @Override
    public ResultSet execute(Connection connection) throws SQLException {
        PreparedStatement ps = fillObjects(connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS), objects);
        ps.executeUpdate();
        return ps.getGeneratedKeys();
    }
}
