function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#image').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	} else {
		$('#image')
				.attr('src',
						"https://drive.google.com/uc?export=view&id=1DbMKHR-mObaG56QAVDqGHoO4XoXStC2M");
	}
}
$("#image-chooser").change(function() {
	readURL(this);
});

// Disable form submissions if there are invalid fields
(function() {
	'use strict';
	window.addEventListener('load', function() {
		// Get the forms we want to add validation styles to
		var forms = document.getElementsByClassName('needs-validation');
		// Loop over them and prevent submission
		var validation = Array.prototype.filter.call(forms, function(form) {
			form.addEventListener('submit', function(event) {
				if (form.checkValidity() === false) {
					event.preventDefault();
					event.stopPropagation();
				}
				form.classList.add('was-validated');
			}, false);
		});
	}, false);
})();