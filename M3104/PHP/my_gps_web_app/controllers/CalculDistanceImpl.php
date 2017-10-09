<?php

require("CalculDistance.php");

class CalculDistanceImpl implements CalculDistance {
    public function calculDistance2PointsGPS($lat1, $long1, $lat2, $long2)
    {
        static $distanceTotale = 0;
        $r = 6378.137;

        $distanceCal = sin(deg2rad($lat2)) * sin(deg2rad($lat1));
        $distanceCal += cos(deg2rad($lat2)) * cos(deg2rad($lat1)) * cos(deg2rad($long2) - deg2rad($long1));
        $distanceCal = acos($distanceCal) * $r;

        return $distanceCal;
    }

    public function calculDistanceTrajet(Array $trajet)
    {
        $d = 0;
        for ($i = 0; $i < (sizeof($trajet)-1); $i++)
        {
            $d += self::calculDistance2PointsGPS($trajet[$i][0], $trajet[$i][1], $trajet[$i+1][0], $trajet[$i+1][1]);
        }
        return $d;
    }

}

?>
