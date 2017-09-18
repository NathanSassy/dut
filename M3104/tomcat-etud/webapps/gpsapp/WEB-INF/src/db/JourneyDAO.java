import db.SqliteConnection;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class JourneyDAO implements DAO<Journey>{
    private static JourneyDAO inst;
    private SqliteConnection connection = SqliteConnection.getInstance();

    public static JourneyDAO getInstance() {
        if (JourneyDAO.inst == null) {
            JourneyDAO.inst = new JourneyDAO();
        }
        return JourneyDAO.inst;
    }

    public int insert(Journey journey) {
        int newId = -1;
        Connection con = null;

        String query = "INSERT INTO Journey"
                + "(description) VALUES"
                + "(?)"
                + ";";

        try {
            con = connection.getConnection();
            PreparedStatement state = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            state.setString(1, journey.getDescription());
            state.executeUpdate();

            ResultSet rs = state.getGeneratedKeys();
            if (rs.next()) {
                newId = rs.getInt(1);
                journey.setId(newId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return  newId;
    }

    public boolean update(Journey journey) {
        boolean ret = false;
        Connection con = null;
        try {
            con = connection.getConnection();
            String query = "UPDATE Journey SET description = ?"
                    + ", distance = ?"
                    + " WHERE id = ?";
            PreparedStatement state = con.prepareStatement(query);

            state.setString(1, journey.getDescription());
            state.setFloat(2, journey.getDistance());
            state.setInt(3, journey.getId());

            state.executeUpdate();
            ret = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public boolean delete(Journey journey) {
        boolean ret = false;
        Connection con = null;
        try {
            con = connection.getConnection();
            String query = "DELETE FROM journey "
                    + " WHERE id = ?"
                    + ";";
            PreparedStatement state = con.prepareStatement(query);
            state.setInt(1,  journey.getId());
            state.executeUpdate();
            ret = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public Journey find(int id) {
        Journey journey = null;
        Connection con = null;
        try {
            con = connection.getConnection();
            String query = "SELECT * FROM Journey WHERE id = ?";
            PreparedStatement state = con.prepareStatement(query);
            state.setInt(1, id);
            ResultSet res = state.executeQuery();
            journey = new Journey(res.getString("description"));
            journey.setId(res.getInt("id"));
            journey.setDate_creation(new Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("date_creation")).getTime()));
            journey.setDistance(res.getFloat("distance"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return journey;
    }

}
