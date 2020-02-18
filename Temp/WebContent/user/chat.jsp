<!doctype html>
<html lang="en">
<head>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"
	integrity="sha384-a5N7Y/aK3qNeh15eJKGWxsqtnX/wWdSZSKp+81YjTmS15nvnvxKHuzaWwXHDli+4"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"
	integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/chat.css">
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<script src="../vendor/owl.carousel.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Script -->
<script src="../js/cart.js"></script>
<script src="../js/login.js"></script>
<script src="../js/animations.js"></script>
<script src="../js/order.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/chat.css">
<!-- CSS -->
<link href="../css/owl.carousel.css" rel="stylesheet" />
<link href="../css/footer.css" rel="stylesheet" />
<link href="../css/owl.theme.default.css" rel="stylesheet" />
<link href="../css/main.css" rel="stylesheet">
<jsp:include page="navbar.jsp"></jsp:include>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<script>
	$(function() {
		$("#footerDiv").load("footer.html");
	});
</script>
</head>
<body>
	<div class="container">
		<div class="row mt-5">
			<div
				class="col-md-6 offset-md-3 col-sm-6 offset-sm-3 col-12 comments-main pt-4 rounded">
				<ul class="p-0">
					<li>
						<div class="row comments mb-2">
							<div class="col-md-2 col-sm-2 col-3 text-center user-img">
								<img id="profile-photo"
									src="http://nicesnippets.com/demo/man01.png"
									class="rounded-circle" />
							</div>
							<div class="col-md-9 col-sm-9 col-9 comment rounded mb-2">
								<h4 class="m-0">
									<a href="#">Jacks David</a>
								</h4>
								<time class="text-white ml-3">1 hours ago</time>
								<like></like>
								<p class="mb-0 text-white">Thank you for visiting all the
									way from New York.</p>
							</div>
						</div>
					</li>
					<ul class="p-0">
						<li>
							<div class="row comments mb-2">
								<div
									class="col-md-2 offset-md-2 col-sm-2 offset-sm-2 col-3 offset-1 text-center user-img">
									<img id="profile-photo"
										src="http://nicesnippets.com/demo/man02.png"
										class="rounded-circle" />
								</div>
								<div class="col-md-7 col-sm-7 col-8 comment rounded mb-2">
									<h4 class="m-0">
										<a href="#">Steve Alex</a>
									</h4>
									<time class="text-white ml-3">1 week ago</time>
									<like></like>
									<p class="mb-0 text-white">Thank you for visiting all the
										way from NYC.</p>
								</div>
							</div>
						</li>
					</ul>
				</ul>
				<ul class="p-0">
					<li>
						<div class="row comments mb-2">
							<div class="col-md-2 col-sm-2 col-3 text-center user-img">
								<img id="profile-photo"
									src="http://nicesnippets.com/demo/man03.png"
									class="rounded-circle" />
							</div>
							<div class="col-md-9 col-sm-9 col-9 comment rounded mb-2">
								<h4 class="m-0">
									<a href="#">Andrew Filander</a>
								</h4>
								<time class="text-white ml-3">7 day ago</time>
								<like></like>
								<p class="mb-0 text-white">Thank you for visiting all the
									way from New York.</p>
							</div>
						</div>
					</li>
					<ul class="p-0">
						<li>
							<div class="row comments mb-2">
								<div
									class="col-md-2 offset-md-2 col-sm-2 offset-sm-2 col-3 offset-1 text-center user-img">
									<img id="profile-photo"
										src="http://nicesnippets.com/demo/man04.png"
										class="rounded-circle" />
								</div>
								<div class="col-md-7 col-sm-7 col-8 comment rounded mb-2">
									<h4 class="m-0">
										<a href="#">james Cordon</a>
									</h4>
									<time class="text-white ml-3">1 min ago</time>
									<like></like>
									<p class="mb-0 text-white">Thank you for visiting from an
										unknown location.</p>
								</div>
							</div>
						</li>
					</ul>
				</ul>
				<ul class="p-0">
					<li>
						<div class="row comments mb-2">
							<div class="col-md-2 col-sm-2 col-3 text-center user-img">
								<img id="profile-photo"
									src="http://nicesnippets.com/demo/man01.png"
									class="rounded-circle" />
							</div>
							<div class="col-md-9 col-sm-9 col-9 comment rounded mb-2">
								<h4 class="m-0">
									<a href="#">Tye Simmon</a>
								</h4>
								<time class="text-white ml-3">1 month ago</time>
								<like></like>
								<p class="mb-0 text-white">Thank you for visiting all the
									way from New York.</p>
							</div>
						</div>
					</li>
				</ul>
				<div class="row comment-box-main p-3 rounded-bottom">
					<div class="col-md-9 col-sm-9 col-9 pr-0 comment-box">
						<input type="text" class="form-control" placeholder="comment ...." />
					</div>
					<div class="col-md-3 col-sm-2 col-2 pl-0 text-center send-btn">
						<button class="btn btn-info">Send</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footerDiv"></div>

</body>
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/vue/0.12.14/vue.min.js'></script>
</html>