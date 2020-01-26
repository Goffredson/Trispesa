function updateNavbarDOM(operation, animDelay) {
	if (operation == "login") {
		$("#loginDropdown").hide(animDelay);
		$("#logoutButton").show(animDelay);
		$("#dieta").show(animDelay);
		$("#ordini").show(animDelay);
		$("#profilo").show(animDelay);
		$("#orderButton").prop("onclick", null).off("click");
		$("#orderAnchor").attr("href", "makeOrder");
	} else {
		$("#logoutButton").hide(animDelay);
		$("#dieta").hide(animDelay);
		$("#ordini").hide(animDelay);
		$("#profilo").hide(animDelay);
		$("#loginDropdown").show(animDelay);
		$("#orderButton").prop("onclick", null).on("click");
		$("#orderAnchor").attr("href", "");
	}
}

function fillCartAfterLogin(cartHashMap) {
	var totalPrice = 0;
	for (var product in cartHashMap) {
		totalPrice += cartHashMap[product][0].price;
		$("#listaProdottiCarrello").append(
				'<tr id="product_"' + cartHashMap[product][0].id + '>'
						+ '<th scope="row" id="productQuantity">'
						+ cartHashMap[product][1] + '</th>'
						+ '<td id="productName">'
						+ cartHashMap[product][0].name + '</td>'
						+ '<td id="productPrice">'
						+ cartHashMap[product][0].price + '</td>'
						+ '<td><a><i class="fas fa-times"></i></a></td>'
						+ '<td><button type="button"' + 'onclick="updateCart('
						+ cartHashMap[product][0].id + ', \''
						+ cartHashMap[product][0].name + '\', \''
						+ cartHashMap[product][0].superMarket.name + '\', '
						+ cartHashMap[product][0].price + ', \'remove\');"'
						+ 'class="btn btn-danger">Rimuovi</button></td>'
						+ '</tr>');
	}
	$("#totalCartPrice").html(totalPrice);
}

function emptyCartAfterLogout() {
	$("#listaProdottiCarrello").empty();
	$("#totalCartPrice").empty();
}

function ajaxLog(operation, animDelay, onIndex) {

	var servletUrl = ((onIndex === true) ? "user/effettuaLogin"
			: "effettuaLogin");

	$.ajax({
		type : "POST",
		// TODO: rimappare da user/effettuaLogin
		url : servletUrl,
		datatype : "JSON",
		data : JSON.stringify([ $("#inputUsername").val(),
				$("#inputPassword").val(), operation ]),
		success : function(response) {
			if (operation == "login") {
				if (response === "")
					window.location.href = "administration";
				else {
					$("#toastMessage").html(
							"Bentornato in trispesa, "
									+ $("#inputUsername").val());
					fillCartAfterLogin(response);
					startTimer(30*60, $("#timer"));
				}
			} else {
				$("#toastMessage")
						.html("A presto " + $("#inputUsername").val());
				emptyCartAfterLogout(response);
				clearInterval(intervalId);
				$("#timer").empty();
				sessionStorage.removeItem("remainingTime");
			}
			$('#welcomeToast').toast('show');
			updateNavbarDOM(operation, animDelay);
			$('#credenzialiErrate').hide();
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
