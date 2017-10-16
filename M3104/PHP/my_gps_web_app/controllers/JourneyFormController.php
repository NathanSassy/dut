<?php

require_once('Controller.php');
require_once('model/JourneyDAO.php');
require_once('model/Journey.php');
require_once('views/JourneyFormView.php');

class JourneyFormController implements Controller {
    public function handle($request) {
        $view = new JourneyFormView();
        $html = $view->render();
        return $html;
    }
}

?>
