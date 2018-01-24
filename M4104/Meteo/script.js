function fetchMeteoData(){
    var input = document.getElementById("inputCity").value;

    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://api.openweathermap.org/data/2.5/weather?q="+input+"&APPID=ee07e2bf337034f905cde0bdedae3db8");
   	xhttp.send(null);
   	xhttp.onreadystatechange = function() {
    	if(this.readyState == 4 && this.status == 200) {
			var result = eval('('+this.responseText+')');
			var icon = "http://openweathermap.org/img/w/" + result.weather[0].icon + ".png";

			document.getElementById('meteo').innerHTML = result.weather[0].main + "<img src='" + icon + " \'/>";
		    document.getElementById('city').innerHTML = result.name;
		    document.getElementById('degree').innerHTML = result.main.temp;
			document.getElementById('pression').innerHTML = result.main.pressure;
			
			document.getElementById('windSpeed').innerHTML = result.wind.speed;
			document.getElementById('degreeMax').innerHTML = result.main.temp_max;
			document.getElementById('degreeMin').innerHTML = result.main.temp_min;
			document.getElementById('sunrise').innerHTML = result.sys.sunrise;
			document.getElementById('sunset').innerHTML = result.sys.sunset;
    	}
    }
}

function displayDetail() {
	var details = document.getElementById("details");
    if (details.style.display === "none") {
        details.style.display = "block";
    } else {
        details.style.display = "none";
	}
}

$(document).ready(function () {
	$('#validate').on('click', function () {
		var input = $("#inputCity").val();

		$.ajax({
			url : "http://api.openweathermap.org/data/2.5/weather?q="+input+"&APPID=ee07e2bf337034f905cde0bdedae3db8",
			method : 'GET',
			dataType: "JSON",
			success : function(data) {
				var icon = "http://openweathermap.org/img/w/" + data.weather[0].icon + ".png";

				$('#meteo').html(data.weather[0].main + "<img src='" + icon + " \'/>");
				$('#city').html(data.name);
				$('#degree').html(data.main.temp);
				$('#pression').html(data.main.pressure);
				
				$('#windSpeed').html(data.wind.speed);
				$('#degreeMax').html(data.main.temp_max);
				$('#degreeMin').html(data.main.temp_min);
				$('#sunrise').html(data.sys.sunrise);
				$('#sunset').html(data.sys.sunset);
			}
		});
	});
});