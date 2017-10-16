<?php

require_once('Controller.php');
require_once('model/JourneyDAO.php');
require_once('views/JourneyListView.php');

class JourneyListController implements Controller {

    public function handle($request) {
        $view = new JourneyListView();
        $html = $view->render(JourneyDAO::getInstance()->findAll());
        return $html;
    }
}

?>
