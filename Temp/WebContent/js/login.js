function updateNavbarDOM(operation, toggleDelay) {
	if (operation == "login") {
		$("#buttonLogin").toggle(toggleDelay);
		$("#buttonLogout").toggle(toggleDelay);
		$("#dieta").toggle(toggleDelay);
		$("#ordini").toggle(toggleDelay);
		$("#profilo").toggle(toggleDelay);
	} else {
		$("#buttonLogout").toggle(toggleDelay);
		$("#dieta").toggle(toggleDelay);
		$("#ordini").toggle(toggleDelay);
		$("#profilo").toggle(toggleDelay);
		$("#buttonLogin").toggle(toggleDelay);
	}
}

function ajaxLog(operation, toggleDelay) {

	$.ajax({
		type : "POST",
		url : "user/effettuaLogin",
		datatype : "JSON",
		data : JSON.stringify([ $("#inputUsername").val(),
				$("#inputPassword").val(), operation ]),
		success : function(response) {

			if (response.redirect) {
				window.location.href = response.redirect_url;
			} else {
				if (operation == "login") {
					// Chiusura menu di login
					$("#iduno").removeClass("dropdown show");
					$("#iddue").removeClass("dropdown-menu show");
					$("#iduno").addClass("dropdown");
					$("#iddue").addClass("dropdown-menu");
					$("#toastMessage").html("Bentornato in trispesa, " + $("#inputUsername").val());
				}
				else {
					$("#toastMessage").html("Arrivederci, " + $("#inputUsername").val());
				}
				$('#welcomeToast').toast('show');
				updateNavbarDOM(operation, toggleDelay);
			}

		},
		error : function(httpObj, textStatus) {
			if (httpObj.status == 401) {
				alert("No!");
			}
		}
	});

}
