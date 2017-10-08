package control.listeners;

import java.util.ArrayList;
import javax.servlet.http.*;
import db.*;
import control.*;

public class JourneyListListener implements ActionListener {

  public void handle(HttpServletRequest request){
      JourneyDAO journeyDAO = JourneyDAO.getInstance();
      ArrayList<Journey> list = journeyDAO.find();
      request.setAttribute("data", list);
  }

}
