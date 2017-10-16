<?php

require_once('Controller.php');
require_once('model/CoordinatesDAO.php');
require_once('views/JourneyView.php');

class JourneyController implements Controller {
    public function handle($request) {
        $id = (int) $request;
        $view = new JourneyView();
        $coords = CoordinatesDAO::getInstance()->findWithJourneyId($id);
        $html = $view->render($id, $coords);
        return $html;
    }
}

?>
