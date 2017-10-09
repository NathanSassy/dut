<?php

interface CalculDistance {
    /**
    * Retourne la distance en mètres entre 2 points GPS exprimés en degrés.
    */
    public function calculDistance2PointsGPS($lat1, $long1, $lat2, $long2);

    /**
    * Retourne la distance en metres du trajet passé en paramètres
    * (tableau comparable au tableau coordonnees de l'exercice précédent).
    */
    public function calculDistanceTrajet(Array $trajet);
}

?>
