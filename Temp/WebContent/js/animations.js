$(document).ready(function() {

	$('#productCarousel').owlCarousel({
		loop : true,
		margin : 10,
		nav : true,
		autoplay : true,

		responsive : {
			0 : {
				items : 1
			},
			600 : {
				items : 3
			},
			1000 : {
				items : 5
			}
		}
	})

	$('#categoryCarousel').owlCarousel({
		loop : false,
		margin : 10,
		nav : true,
		autoplay : false,
		dots : false,

		responsive : {
			0 : {
				items : 1
			},
			600 : {
				items : 6
			},
			1000 : {
				items : 7
			}
		}
	})

	$('#loginDropdown').on('show.bs.dropdown', function() {
		$(this).find('.dropdown-menu').first().stop(true, true).slideDown();
	});

	$('#loginDropdown').on('hide.bs.dropdown', function() {
		$(this).find('.dropdown-menu').first().stop(true, true).slideUp();
	});

});