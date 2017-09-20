package control.listeners;

import java.util.ArrayList;
import javax.servlet.http.*;
import db.*;
import control.*;

public class JourneyAddListener implements ActionListener {

  public void handle(HttpServletRequest request) {

  	if(request.getAttribute("journey_description") == null)
  		System.out.println("pas d'argument recu");

  	String description = (String) request.getAttribute("journey_description");
  	if(description == null) {
  		System.out.println("description est null");
  		return;
  	}

  	JourneyDAO journeyDAO = JourneyDAO.getInstance();
  	int id = journeyDAO.insert(new Journey(description));
  	System.out.println("new id = " + id);

  	id = 666;
  	
  	request.setAttribute("journey_id", id);

  }

}
