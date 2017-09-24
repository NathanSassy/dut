package control.listeners;

import java.util.ArrayList;
import javax.servlet.http.*;
import db.*;
import control.*;

public class CoordinateAddListener implements ActionListener {

    public void handle(HttpServletRequest request) {
        Boolean success = true;
        ArrayList<Coordinates> coordinates = (ArrayList<Coordinates>) request.getSession().getAttribute("new_coordinates");

        if(coordinates == null) {
            success = false;
        }
        else {
            for(Coordinates c : coordinates) {
                CoordinatesDAO.getInstance().insert(c);
            }
        }

        request.setAttribute("success", success);

    }
}
