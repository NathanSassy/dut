<?php
    class CoordinatesFormView {
        public function render($id) {
            $html = "
                <FORM method='POST' action=http://m3104.iut-info-vannes.net/m3104_1/index.php?page=coordinates_add>
                    latitude : <INPUT type=number step=0.000001 name='latitude'>
                    <BR>
                    longitude : <INPUT type=number step=0.000001 step=any name='longitude'>
                    <BR>
                    <input type='hidden' name='journey_id' value='" . $id . "' />
                    <INPUT type=submit value=Ajouter>
                </FORM>
            ";
            return $html;
        }
    }
?>
