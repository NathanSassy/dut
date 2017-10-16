<?php

require_once('ErrorPage.php');
require_once('JourneyListController.php');
require_once('JourneyAddController.php');
require_once('JourneyFormController.php');
require_once('JourneyController.php');
require_once('CoordinatesAddController.php');
require_once('CoordinatesFormController.php');

function rooting($request) {
    $journey_list = new JourneyListController();
    $err = new ErrorPage();

    if(isset($request['page'])) {
        $page = $request['page'];
        if($page == "journey_list") {
            return $journey_list->handle("");
        }
        else if($page == "journey") {
            if(isset($request['journey_id'])) {
                $journey = new JourneyController();
                return $journey->handle($request['journey_id']);
            }
            else {
                $err->handle("invalid id");
            }
        }
        else if($page == "journey_form") {
            $journeyForm = new JourneyFormController();
            return $journeyForm->handle("");
        }
        else if($page == "journey_add") {
            $journeyForm = new JourneyAddController();
            return $journeyForm->handle($request);
        }
        else if($page == "coordinates_form") {
            $journeyForm = new CoordinatesFormController();
            return $journeyForm->handle("");
        }
        else if($page == "coordinates_add") {
            $journeyForm = new CoordinatesAddController();
            return $journeyForm->handle($request);
        }
        else {
            $err->handle("invalid argument");
        }
    }
    else {
        return $journey_list->handle("");
    }
}
?>
