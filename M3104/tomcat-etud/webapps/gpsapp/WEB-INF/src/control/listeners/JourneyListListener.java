package control.listeners;

import javax.servlet.http.*;
import db.*;
import control.*;

public class JourneyListListener implements ActionListener {

  public void handle(HttpServletRequest request){

    request.setAttribute("data", "JourneyListListener");
  }
  
}

