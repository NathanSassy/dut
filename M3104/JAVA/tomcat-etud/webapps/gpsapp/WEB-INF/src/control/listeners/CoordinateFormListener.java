package control.listeners;

import java.util.ArrayList;
import javax.servlet.http.*;
import db.*;
import control.*;

public class CoordinateFormListener implements ActionListener {

    public void handle(HttpServletRequest request) {
        ArrayList<Coordinates> coordinates = new ArrayList<>();

        Integer journey_id = (Integer) request.getSession().getAttribute("journey_id");
        request.setAttribute("journey_id", journey_id);

        ArrayList<Coordinates> new_coordinates = (ArrayList<Coordinates>) request.getSession().getAttribute("new_coordinates");
        if(new_coordinates != null) {
            coordinates.addAll(new_coordinates);
        }

        String param = request.getParameter("latitude");
        Double latitude = param != null && !param.isEmpty() ? Double.parseDouble(param) : null;

        param = request.getParameter("longitude");
        Double longitude = param != null && !param.isEmpty() ? Double.parseDouble(param) : null;

        if(journey_id != null && latitude != null && longitude != null)
            coordinates.add(new Coordinates(journey_id, latitude, longitude));

        request.setAttribute("coordinates", coordinates);

    }
}
