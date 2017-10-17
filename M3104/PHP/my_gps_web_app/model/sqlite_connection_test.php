<?php

require_once("JourneyDAO.php");
require_once("CoordinatesDAO.php");
require_once("Journey.php");

function testStudentDAO() {
    $dao = JourneyDAO::getInstance();
    /*$journey = new Journey();
    $journey->setDescription("toto test");
    $dao->insert($journey);
    */
    $journeys = $dao->findAll();
    foreach ($journeys as $journey) {
        echo $journey->getId() . " | " . $journey->getDescription() . " | " . $journey->getDateCreation() . " | " . $journey->getDistance() . "\n";
    }

    $updatedJourney = $dao->find(138);
    $updatedJourney->setDescription("updated");
    $dao->update($updatedJourney);

    $journeyById = $dao->find(49);
    echo $journeyById->getDescription() . "\n";

    $deletedJourney = new Journey();
    $deletedJourney->setId("139");
    $dao->delete($deletedJourney);

    $coordao = CoordinatesDAO::getInstance();
    $coords = $coordao->findWithJourneyId(49);
    echo sizeof($coords);

    $lastId = $coordao->getLastId();
    echo "\nlast id = " . $lastId . "\n";
}

testStudentDAO();

?>
