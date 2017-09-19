import java.sql.Date;
import java.util.ArrayList;

import db.*;

public class TestDAO {
    public static void main(String args[]) {
        testJourneyDAO();
        testCoordinatesDAO();
    }

    private static void testJourneyDAO() {
        System.out.println("****** testJourneyDAO ******");

        JourneyDAO journeyDAO = JourneyDAO.getInstance();

        // try insert
        System.out.println("Try insert journey");
        int newId = journeyDAO.insert(new Journey("toto"));

        if(newId > 0)
            System.out.println("[OK] New journey id = " + newId);
        else
            System.out.println("[FAILED] journey not inserted");

        // try find
        System.out.println("Try find journey");
        Journey j = journeyDAO.find(newId);
        if(j != null) {
            System.out.println("[OK] Found journey = \n" + j);

            // try update
            String newDescription = "titi";
            j.setDescription(newDescription);
            if(journeyDAO.update(j))
                System.out.println("[OK] journey updated");
            else
                System.out.println("[FAILED] journey not updated");


            // try delete
            System.out.println("Try delete journey no " + j.getId());
            if(journeyDAO.delete(j))
                System.out.println("[OK] journey deleted");
            else
                System.out.println("[FAILED] journey not deleted");

        }
        else{
            System.out.println("[FAILED] No journey found journey");
        }

        // try find()
        System.out.println("Insert 5 rows into Journey");
        journeyDAO.insert(new Journey("toto"));
        journeyDAO.insert(new Journey("toto2"));
        journeyDAO.insert(new Journey("toto3"));
        journeyDAO.insert(new Journey("toto4"));
        System.out.println("Put in ArrayList");
        ArrayList<Journey> list = journeyDAO.find();
        if(list != null && list.size() != 0) {
            System.out.println("[OK] list = \n" + list);
        }
        else {
            System.out.println("[FAILED] list empty");
        }

        System.out.println("\n*********************\n");
    }

    private static void testCoordinatesDAO() {
        System.out.println("****** testCoordinatesDAO ******");

        CoordinatesDAO coordinatesDAO = CoordinatesDAO.getInstance();

        // try insert
        System.out.println("Try insert coordinates");
        int newId = coordinatesDAO.insert(new Coordinates( 1, 1.4795,-2.776605));

        if(newId > 0)
            System.out.println("[OK] New coordinates id = " + newId);
        else
            System.out.println("[FAILED] coordinates not inserted");

        // try find
        System.out.println("Try find coordinates");
        Coordinates c = coordinatesDAO.find(newId);
        if(c != null) {
            System.out.println("[OK] Found coordinates = \n" + c);

            // try update
            Double newLat = 666.666;
            c.setLatitude(newLat);
            if(coordinatesDAO.update(c))
                System.out.println("[OK] coordinates updated");
            else
                System.out.println("[FAILED] coordinates not updated");


            // try delete
            System.out.println("Try delete coordinates no " + c.getJourney_id());
            if(coordinatesDAO.delete(c))
                System.out.println("[OK] coordinates deleted");
            else
                System.out.println("[FAILED] coordinates not deleted");

        }
        else{
            System.out.println("[FAILED] No coordinates found coordinates");
        }

        System.out.println("\n*********************\n");
    }
}
