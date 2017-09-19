package db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoordinatesDAO implements DAO<Coordinates> {
    private static CoordinatesDAO inst;
    private db.SqliteConnection connection = db.SqliteConnection.getInstance();

    public static CoordinatesDAO getInstance() {
        if (CoordinatesDAO.inst == null) {
            CoordinatesDAO.inst = new CoordinatesDAO();
        }
        return CoordinatesDAO.inst;
    }

    public int insert(Coordinates coordinates) {
        int newId = -1;
        Connection con = null;

        String query = "INSERT INTO Coordinates"
                + "(journey_id, journey_pos, latitude, longitude) VALUES"
                + "(?,?,?,?)"
                + ";";

        String counter = "SELECT COUNT(*) FROM Coordinates;";

        try {
            con = connection.getConnection();
            int rows = con.prepareStatement(counter).executeQuery().getInt(1);
            PreparedStatement state = con.prepareStatement(query);

            state.setInt(1, rows+1);
            state.setInt(2, coordinates.getJourney_pos());
            state.setDouble(3, coordinates.getLatitude());
            state.setDouble(4, coordinates.getLongitude());

            state.executeUpdate();
            newId = rows + 1;
            coordinates.setJourney_id(newId);

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

    public boolean update(Coordinates coordinates) {
        boolean ret = false;
        Connection con = null;
        try {
            con = connection.getConnection();
            String query = "UPDATE Coordinates SET journey_pos = ?"
                    + ", latitude = ?"
                    + ", longitude = ?"
                    + " WHERE journey_id = ?";
            PreparedStatement state = con.prepareStatement(query);

            state.setInt(1, coordinates.getJourney_pos());
            state.setDouble(2, coordinates.getLatitude());
            state.setDouble(3, coordinates.getLongitude());
            state.setInt(4, coordinates.getJourney_id());

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

    public boolean delete(Coordinates coordinates) {
        boolean ret = false;
        Connection con = null;
        try {
            con = connection.getConnection();
            String query = "DELETE FROM Coordinates "
                    + " WHERE journey_pos = ?"
                    + ";";
            PreparedStatement state = con.prepareStatement(query);
            state.setInt(1,  coordinates.getJourney_pos());
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

    public Coordinates find(int id) {
        Coordinates coordinates = null;
        Connection con = null;
        try {
            con = connection.getConnection();
            String query = "SELECT * FROM Coordinates WHERE journey_id = ?";
            PreparedStatement state = con.prepareStatement(query);
            state.setInt(1, id);
            ResultSet res = state.executeQuery();
            coordinates = new Coordinates(
                    res.getInt("journey_pos"),
                    res.getDouble("latitude"),
                    res.getDouble("longitude")
            );
            coordinates.setJourney_id(res.getInt("journey_id"));
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
        return coordinates;
    }


}
