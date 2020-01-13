function effettuaLogin() {

	var cliente = {
		username : $("#inputUsername").val(),
		password : $("#inputPassword").val()
	};

	$.ajax({
				type : "POST",
				url : "user/effettuaLogin",
				datatype : "JSON",
				data : JSON.stringify(cliente),
				success : function(response) {

					if (response.redirect) {
						window.location.href = response.redirect_url;
					}
					
					$("#buttonLogin").remove();
					var ordini = $('<li class="nav-item"><a class="nav-link" href="#">Ordini</a></li>\n');
					var profilo = $('<li class="nav-item"><a class="nav-link" href="user?page=profile">Profilo</a></li>\n');
					var dieta = $('<li class="nav-item"><a class="nav-link" href="#">Dieta</a></li>\n');
					$("#ulNavBar").append(dieta);
					$("#ulNavBar").append(ordini);
					$("#ulNavBar").append(profilo);
					$("#buttonLogin").attr("aria-expanded", "false");
					
					// E' grezzo, riguardalo dopo
					$("#iduno").removeClass("dropdown show");
					$("#iddue").removeClass("dropdown-menu show");
					$("#iduno").addClass("dropdown");
					$("#iddue").addClass("dropdown-menu");
					


				},
				error : function(httpObj, textStatus) {
					if (httpObj.status == 401) {
						alert("No!");
					}
				}
			});
	
}

//$('#submitButton').on('submit', function(e) { // use on if jQuery 1.7+
//	alert("ciaooo");
//	effettuaLogin();
//	e.preventDefault();
//});
