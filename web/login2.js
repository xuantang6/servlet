const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});

function togglePasswordVisibility(inputId) {
	var passwordInput = document.getElementById(inputId);
	var toggleIcon = document.querySelector("#" + inputId).nextElementSibling.querySelector("i");

	if (passwordInput.type === "password") {
			passwordInput.type = "text";
			toggleIcon.classList.remove("fa-eye");
			toggleIcon.classList.add("fa-eye-slash");
	} else {
			passwordInput.type = "password";
			toggleIcon.classList.remove("fa-eye-slash");
			toggleIcon.classList.add("fa-eye");
	}
}