<?php

require_once('Controller.php');

class ErrorPage implements Controller {
    public  function __construct() {
    }

    public function handle($request) {
        echo "Erreur";
    }
}

?>
