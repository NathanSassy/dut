package db;

import java.io.Serializable;
import java.sql.Date;

public class Journey implements Serializable {
	private int id;
	private String description;
	private Date date_creation;
	private double distance;

    public Journey(String description) {
        this.id = id;
        this.description = description;
        this.date_creation = date_creation;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        String ret = "--- Journey ---";
        ret += "\nID = " + id;
        ret += "\nDESCRIPTION = " + description;
        ret += "\ndate_creation = " + date_creation.toString();
        ret += "\nDISTANCE = " + distance;
        ret += "\n---------------\n";
        return ret;
    }
}
