<?php

class Student{
    private $nom;
    private $prenom;

    public function  __construct() {
    }

    public function init($n, $p) {
        $this->nom = $n;
        $this->prenom = $p;
    }

    public function getLastname() {
        return $this->nom;
    }

    public function getFirstname() {
        return $this->prenom;
    }

    public function  __toString() {
        return $this->nom. " ". $this->prenom;
    }
}

?>
