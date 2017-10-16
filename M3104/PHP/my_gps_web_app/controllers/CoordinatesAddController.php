<?php

require_once('Controller.php');
require_once('model/CoordinatesDAO.php');
require_once('model/Coordinates.php');
require_once('views/CoordinatesFormView.php');

class CoordinatesAddController implements Controller {
    public function handle($data) {
        $coordinates = new Coordinates();
        $pos = (int) CoordinatesDAO::getInstance()->getLastId();
        $coordinates->setJourneyId($data['journey_id']);
        $coordinates->setJourneyPos($pos + 1);
        $coordinates->setLatitude($data['latitude']);
        $coordinates->setLongitude($data['longitude']);
        $ok = CoordinatesDAO::getInstance()->insert($coordinates);
        $view = new CoordinatesAddAddView();
        $html = $view->render($ok);
        return $html;
    }
}

?>
