package control.listeners;

import java.util.ArrayList;
import javax.servlet.http.*;
import db.*;
import control.*;

public class JourneyAddListener implements ActionListener {

  public void handle(HttpServletRequest request) {
  	String description = (String) request.getParameter("journey_description");
  	if(description == null) {
  		System.out.println("description est null");
  		return;
  	}

  	JourneyDAO journeyDAO = JourneyDAO.getInstance();
  	int id = journeyDAO.insert(new Journey(description));

  	request.setAttribute("journey_id", id);

  }
}
