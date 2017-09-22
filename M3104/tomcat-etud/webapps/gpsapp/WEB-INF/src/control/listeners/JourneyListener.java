package control.listeners;

import java.util.ArrayList;
import javax.servlet.http.*;
import db.*;
import control.*;

public class JourneyListener implements ActionListener {

  public void handle(HttpServletRequest request) {
      int journey_id = -1;
      ArrayList<Coordinates> coordinates = null;

      if(request.getParameter("journey_id") != null) {
          try {
              journey_id = Integer.parseInt(request.getParameter("journey_id"));
          }
          catch(NumberFormatException e) {
              System.out.println("journey_id invalid");
          }
      }

      if(journey_id != -1)
          coordinates = CoordinatesDAO.getInstance().findFromJourneyId(journey_id);

      request.setAttribute("journey_id", journey_id);
      request.setAttribute("coordinates", coordinates);
  }

}
