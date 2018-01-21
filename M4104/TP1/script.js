

function validateAction() {

	var aCity = document.getElementById('formVille').value;


    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://api.openweathermap.org/data/2.5/weather?q="+aCity+"&APPID=ee07e2bf337034f905cde0bdedae3db8");
   	xhttp.send(null);
   	xhttp.onreadystatechange = function() {
    	if(this.readyState == 4){
    		var jsonResult = eval('('+this.responseText+')');
		    document.getElementById('main').innerHTML = jsonResult.weather[0].main;
		    document.getElementById('city').innerHTML = jsonResult.name;
		    document.getElementById('degre').innerHTML = jsonResult.main.temp;
		    document.getElementById('pression').innerHTML = jsonResult.main.pressure;
		    document.getElementById('humidity').innerHTML = jsonResult.main.humidity;
		    document.getElementById('description').innerHTML = jsonResult.weather[0].description;
		    document.getElementById('degre_min').innerHTML = jsonResult.main.temp_min;
		    document.getElementById('degre_max').innerHTML = jsonResult.main.temp_max;
    	}
    }
}


function displayAction() {
	node = document.getElementById('details');
	if (node.style.visibility=="hidden")
	{
		// Contenu caché, le montrer
		node.style.visibility = "visible";
		node.style.height = "auto";			// Optionnel rétablir la hauteur

	}
	else
	{
		// Contenu visible, le cacher
		node.style.visibility = "hidden";
		node.style.height = "0";			// Optionnel libérer l'espace

	}
}

window.onload = function() {
  node = document.getElementById('details');
node.style.visibility = "hidden";
		node.style.height = "0";			// Optionnel libérer l'espace
};
