import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Auth {
    public Session login(String username, String password) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            Session session = findUser(connection, username, password);
            if (session == null) {
                return null;
            }
            updateLastAccess(connection, session);
            return session;
        }
    }

    private Session findUser(Connection connection, String username, String password) throws SQLException {
        String sql = "SELECT id, name, username, grade, absences, last_accessed_at FROM users"
                + " WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    return null;
                }
                return new Session(
                        UUID.fromString(result.getString("id")),
                        result.getString("name"),
                        result.getString("username"),
                        result.getDouble("grade"),
                        result.getInt("absences"),
                        readDate(result, "last_accessed_at"));
            }
        }
    }

    private void updateLastAccess(Connection connection, Session session) throws SQLException {
        String sql = "UPDATE users SET last_accessed_at = ?, updated_at = ? WHERE id = ?";
        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, now);
            statement.setTimestamp(2, now);
            statement.setString(3, session.getId().toString());
            statement.executeUpdate();
        }
    }

    private Date readDate(ResultSet result, String column) throws SQLException {
        Timestamp timestamp = result.getTimestamp(column);
        if (timestamp == null) {
            return null;
        }
        return new Date(timestamp.getTime());
    }
}
