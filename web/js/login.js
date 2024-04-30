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

document.getElementById("passwordInput1").addEventListener("input", function () {
    var password = this.value;
    var passwordWarning = document.getElementById("passwordWarning");

    var validPassword = true;
    if (password.length < 8) {
        validPassword = false;
        passwordWarning.textContent = "Password must be at least 8 characters long.";
    } else if (!/[a-z]/.test(password) || !/[A-Z]/.test(password)) {
        validPassword = false;
        passwordWarning.textContent = "Password must contain both lowercase and uppercase letters.";
    } else if (!/[0-9]/.test(password)) {
        validPassword = false;
        passwordWarning.textContent = "Password must contain at least one digit.";
    } else if (!/[^a-zA-Z0-9]/.test(password)) {
        validPassword = false;
        passwordWarning.textContent = "Password must contain at least one special character.";
    } else {
        passwordWarning.textContent = "";
    }

    if (validPassword) {
        passwordWarning.style.display = "none";
    } else {
        passwordWarning.style.display = "block";
    }
});

function togglePasswordVisibility(inputId) {
    var passwordInput = document.getElementById(inputId);
    var passwordToggle = document.querySelector("#" + inputId + " + .password-toggle i");

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        passwordToggle.classList.remove("fa-eye");
        passwordToggle.classList.add("fa-eye-slash");
    } else {
        passwordInput.type = "password";
        passwordToggle.classList.remove("fa-eye-slash");
        passwordToggle.classList.add("fa-eye");
    }
}
