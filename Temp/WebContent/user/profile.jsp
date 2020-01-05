<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Trispesa</title>

<!-- Bootstrap  -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- css -->
<link href="css/main.css" rel="stylesheet">

</head>
<body>

	<!-- Navigation NON TOCCARE!!! -->
	<nav id="nav"
		class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">Trispesa</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Dieta</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Ordini</a></li>
					<li class="nav-item"><a class="nav-link"
						href="user?page=profile">Profilo</a></li>
					<li class="nav-item"><a class="nav-link" href="#"><img
							src="images/cart.png" width="30" /></a></li>
					<!-- Menu form login -->
					<div class="dropdown">
						<a class="btn btn-secondary dropdown-toggle login" href="#"
							role="button" id="buttonLogin" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">Login</a>

						<div class="dropdown-menu">
							<form class="px-4 py-3">
								<div class="form-group">
									<label for="exampleDropdownFormEmail1">Indirizzo Email</label>
									<input type="email" class="form-control"
										id="exampleDropdownFormEmail1" placeholder="email@example.com">
								</div>
								<div class="form-group">
									<label for="exampleDropdownFormPassword1">Password</label> <input
										type="password" class="form-control"
										id="exampleDropdownFormPassword1" placeholder="Password">
								</div>
								<button type="submit" class="btn btn-primary">Autenticati</button>
							</form>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="#">Effettua registrazione</a> <a
								class="dropdown-item" href="#">Password dimenticata?</a>
						</div>
					</div>
					<!--Chiusura Menu form login -->
					<li class="nav-item"><a class="nav-link" href="administration">Parte
							admin (NON TOCCARE!)</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<div id="colonna" class="col-sm-8 col-md-5 col-lg-5">
				<div>
					<h3>Dati personali</h3>
				</div>
				<div>
					<h4>Username</h4>
					<p>${sessionScope.customer.username}</p>
				</div>
				<div>
					<h4>Password</h4>
					<p>${sessionScope.customer.password}</p>
				</div>
				<div>
					<h4>Nome</h4>
					<p>${sessionScope.customer.name}</p>
				</div>
				<div>
					<h4>Cognome</h4>
					<p>${sessionScope.customer.surname}</p>
				</div>
				<div>
					<h4>Email</h4>
					<p>${sessionScope.customer.email}</p>
				</div>
				<div>
					<h4>Data di nascita</h4>
					<p>${sessionScope.customer.birthDate}</p>
				</div>
			</div>

			<div id="colonna" class="col-sm-8 col-md-5 col-lg-6">
				<div class="row">
					<h4>Indirizzi di consegna</h4>
					<table class="table table-hover table-responsive">
						<tr>
							<th>Paese</th>
							<th>Città</th>
							<th>Indirizzo</th>
							<th colspan="2"><a href="" class="btn btn-success"
								role="button">+ Aggiungi indirizzo di consegna</a></th>
						</tr>
						<c:forEach items="${sessionScope.customer.deliveryAddresses}"
							var="deliveryAddress">
							<tr>
								<td>${deliveryAddress.country}</td>
								<td>${deliveryAddress.city}</td>
								<td>${deliveryAddress.address}</td>
								<td width="10%"><a href="" class="btn btn-info"
									role="button">modifica</a></td>
								<td width="10%"><a
									href="user/manage?type=deliveryAddress&action=del&deliveryAddress=(${deliveryAddress.country},${deliveryAddress.city},${deliveryAddress.address})"
									class="btn btn-danger" role="button">elimina</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>

				<div class="row">
					<h4>Metodi di pagamento</h4>
					<table class="table table-hover table-responsive">
						<tr>
							<th>Numero carta</th>
							<th>Proprietario</th>
							<th>Data scadenza</th>
							<th colspan="2"><a href="" class="btn btn-success"
								role="button">+ Aggiungi metodo di pagamento</a></th>
						</tr>
						<c:forEach items="${sessionScope.customer.paymentMethods}"
							var="paymentMethod">
							<tr>
								<td>${paymentMethod.cardNumber}</td>
								<td>${paymentMethod.owner}</td>
								<td>${paymentMethod.expirationMonth}/${paymentMethod.expirationYear}</td>
								<td width="10%"><a href="" class="btn btn-info"
									role="button">modifica</a></td>
								<td width="10%"><a
									href="user/manage?type=paymentMethod&action=del&card=${paymentMethod.cardNumber}"
									class="btn btn-danger" role="button">elimina</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- MODALE -->
	<div class="modal" id="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<c:if test="${sessionScope.result == true}">
						<h4 class="modal-title">Operazione eseguita con successo</h4>
					</c:if>
					<c:if test="${sessionScope.result == false}">
						<h4 class="modal-title">Operazione annullata</h4>
					</c:if>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
					<c:if test="${sessionScope.result == true}">
						<div id="success-message" class="jumbotron">
							<p>${sessionScope.message}</p>
						</div>
					</c:if>
					<c:if test="${sessionScope.result == false}">
						<div id="error-message" class="jumbotron">
							<p>${sessionScope.message}</p>
						</div>
					</c:if>
				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="close" data-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</div>
	</div>


	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Trispesa
				2020</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<script>
		$(document).ready( e => {
			if (${sessionScope.result != null}) {
				$('#modal').modal('show');
			}
		});
	</script>

	<%
		session.removeAttribute("result");
		session.removeAttribute("message");
	%>

</body>

</html>