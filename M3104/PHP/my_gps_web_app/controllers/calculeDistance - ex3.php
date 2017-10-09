<?php

require("CalculDistanceImpl.php");

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

    $calculDist = new CalculDistanceImpl();
    $d = $calculDist->calculDistanceTrajet($coords);
    echo "distance = ".$d."\n";
}

main();

?>
