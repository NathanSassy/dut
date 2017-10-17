<?php

class JourneyView {

    public function render($id, $coords) {
    	$html = "Coordonnees du trajet : " . $id;
        $html .= "
            <table>
                <tr>
                    <th>
                        <FORM method='POST' action='http://m3104.iut-info-vannes.net/m3104_1/index.php?page=coordinates_form'>
                            <INPUT type=submit value='Ajouter une coordonne'>
                            <INPUT type='hidden' name='journey_id' value='" . $id . "' />
                        </FORM>
                    </th>
                </tr>

                <tr>
                    <th>
                        <FORM method='POST' action='http://m3104.iut-info-vannes.net/m3104_1/index.php?page=journey_list'>
                            <INPUT type=submit value='Retour a la liste des trajets'>
                        </FORM>
                    </th>
                </tr>
            </table>
    	";

        $html .= "
    		 <table border=1>
    			<tr>
    				<th>journey_id</th>
    				<th>journey_pos</th>
    				<th>latitude</th>
    				<th>longitude</th>
    			</tr>
    	";
    	foreach ($coords as $coord)
    	{
    		$html .= "<tr>";
    		$html .= "  <td>" . $coord->getJourneyId() . "</td>";
    		$html .= "  <td>" . $coord->getJourneyPos() . "</td>";
    		$html .= "  <td>" . $coord->getLatitude() . "</td>";
    		$html .= "  <td>" . $coord->getLongitude() .  "</td>";
    		$html .= "</tr>";
    	}
    	$html .= "</table>";
    	return $html;

    }
}
