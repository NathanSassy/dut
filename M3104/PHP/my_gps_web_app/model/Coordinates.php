<?php

class Coordinates {
    private $journey_id;
    private $journey_pos;
    private $latitude;
    private $longitude;

    public function getJourneyId() {
        return $this->journey_id;
    }

    public function setJourneyId($newJourneyId) {
        $this->journey_id = $newJourneyId;
    }

    public function getJourneyPos() {
        return $this->journey_pos;
    }

    public function setJourneyPos($newJourneyPos) {
        $this->$journey_pos = $newJourneyPos;
    }

    public function getLatitude() {
        return $this->latitude;
    }

    public function setLatitude($newLatitude) {
        $this->latitude = $newLatitude;
    }

    public function getLongitude() {
        return $this->longitude;
    }

    public function setLongitude($newLongitude) {
        $this->longitude = $newLongitude;
    }
}

?>
