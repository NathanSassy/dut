<?php

class Journey {
    private $id;
    private $description;
    private $date_creation;
    private $distance;

    public function getId() {
        return $this->id;
    }

    public function setId($newId) {
        $this->id = $newId;
    }

    public function getDescription() {
        return $this->description;
    }

    public function setDescription($newDescription) {
        $this->description = $newDescription;
    }

    public function getDateCreation() {
        return $this->date_creation;
    }

    public function setDateCreation($newDate_creation) {
        $this->date_creation = $newDate_creation;
    }

    public function getDistance() {
        return $this->distance;
    }

    public function setDistance($newDistance) {
        $this->distance = $newDistance;
    }
}

?>
