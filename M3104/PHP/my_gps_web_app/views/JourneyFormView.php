<?php
    class JourneyFormView {
        public function render() {
            $html = "
                <FORM method='POST' action=http://m3104.iut-info-vannes.net/m3104_1/index.php?page=journey_add>
                    Description : <INPUT type=text size=50 name='journey_description'>
                    <BR>
                    <INPUT type=submit value=Envoyer>
                </FORM>
            ";
            return $html;
        }
    }
?>
