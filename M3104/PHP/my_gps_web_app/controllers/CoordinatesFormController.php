<?php

require_once('Controller.php');
require_once('model/CoordinatesDAO.php');
require_once('model/Coordinates.php');
require_once('views/CoordinatesFormView.php');

class CoordinatesFormController implements Controller {
    public function handle($request) {
        $view = new CoordinatesFormView();
        $html = $view->render();
        return $html;
    }
}

?>
