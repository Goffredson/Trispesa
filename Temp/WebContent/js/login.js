function updateNavbarDOM(operation, animDelay) {
	if (operation == "login") {
		$("#loginDropdown").hide(animDelay);
		$("#logoutButton").show(animDelay);
		$("#dieta").show(animDelay);
		$("#ordini").show(animDelay);
		$("#profilo").show(animDelay);
	} else {
		$("#logoutButton").hide(animDelay);
		$("#dieta").hide(animDelay);
		$("#ordini").hide(animDelay);
		$("#profilo").hide(animDelay);
		$("#loginDropdown").show(animDelay);
	}
}

function ajaxLog(operation, animDelay) {

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
					$("#toastMessage").html("Bentornato in trispesa, " + $("#inputUsername").val());
				} else {
					$("#toastMessage").html("A presto " + $("#inputUsername").val());
				}
				$('#welcomeToast').toast('show');
				updateNavbarDOM(operation, animDelay);
				$('#credenzialiErrate').hide();
			}

		},
		error : function(httpObj, textStatus) {
			if (httpObj.status == 401) {
				if ($("#credenzialiErrate").css('display') == 'none') {
					$("#credenzialiErrate").toggle(animDelay);
				} else {
					$("#credenzialiErrate").animate({
						opacity : 0
					}, 200, "linear", function() {
						$(this).animate({
							opacity : 1
						}, 200);
					});
				}
			}
		}
	});
}
