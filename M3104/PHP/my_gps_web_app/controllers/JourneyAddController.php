<?php

require_once('model/Journey.php');
require_once('model/JourneyDAO.php');
require_once('views/JourneyAddView.php');

class JourneyAddController implements Controller {
    public function handle($descr) {
        $journey = new Journey();
        $journey->setDescription($descr['journey_description']);
        $ok = JourneyDAO::getInstance()->insert($journey);
        $view = new JourneyAddView();
        $html = $view->render($ok);
        return $html;
    }
}

?>
