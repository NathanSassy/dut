<?php

class JourneyListView {

	public function render($arg) {
		$html = "
			<FORM method='POST' action='http://m3104.iut-info-vannes.net/m3104_1/index.php?page=journey_form'>
					 <INPUT type=submit value='Ajouter un trajet'>
			</FORM>

			 <table border=1>
				<tr>
					<th>id</th>
					<th>description</th>
					<th>date_creation</th>
					<th>distance</th>
				</tr>
		";

		foreach ($arg as $journey)
		{
			$html .= "<tr>";
			$html .= "  <td> <a href='http://m3104.iut-info-vannes.net/m3104_1/index.php?page=journey&journey_id=" . $journey->getId() . "' >" . $journey->getId() . "</a> </td>";
			$html .= "  <td>" . $journey->getDescription() . "</td>";
			$html .= "  <td>" . $journey->getDateCreation() . "</td>";
			$html .= "  <td>" . $journey->getDistance() .  "</td>";
			$html .= "</tr>";
		}
		$html .= "</table>";
		return $html;

	}
}
