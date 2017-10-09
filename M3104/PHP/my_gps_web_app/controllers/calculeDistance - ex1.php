<?php

function compute($lat1, $long1, $lat2, $long2, &$distance)
{
    static $distanceTotale = 0;
    $r = 6378.137;

    $distanceCal = sin(deg2rad($lat2)) * sin(deg2rad($lat1));
    $distanceCal += cos(deg2rad($lat2)) * cos(deg2rad($lat1)) * cos(deg2rad($long2) - deg2rad($long1));
    $distanceCal = acos($distanceCal) * $r;

    $distanceTotale += $distanceCal;
    $distance = $distanceTotale;
}


function init(&$coords)
{
    $coords = array();
    $coords[] = array(47.644795, -2.776605);
    $coords[] = array(47.646870, -2.778911);
    $coords[] = array(47.646197, -2.780220);
    $coords[] = array(47.646992, -2.781068);
    $coords[] = array(47.647867, -2.781744);
    $coords[] = array(47.648510, -2.780145);
}

function main()
{
    $coords = array();
    init($coords);
    $d = 0;
    for ($i = 0; $i < (sizeof($coords)-1); $i++)
    {
        echo "d = ".$d."\n";
        compute($coords[$i][0], $coords[$i][1], $coords[$i+1][0], $coords[$i+1][1], $d);
    }
    echo "final d = ".$d."\n";
}

main();

?>
