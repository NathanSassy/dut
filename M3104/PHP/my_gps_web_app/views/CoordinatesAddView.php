<?php
    class CoordinatesAddView {
        public function render($ok) {
            $html = "Coordonnee enrengistrée : ";

            if($ok == true) {
                $html .= "OK";
            }
            else {
                $html .= "FAILED";
            }

            $html .= "
                <FORM method='POST' action='http://m3104.iut-info-vannes.net/m3104_1/index.php?page=journey_list'>
                    <INPUT type=submit value='Retour a la liste des trajets'>
                </FORM>
            ";
            return $html;
        }
    }
?>
