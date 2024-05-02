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


function recaptchaCallback() {
    var addButton = document.getElementById('signInBtn');
    addButton.removeAttribute('disabled');
}

var alertElement = document.getElementById('errorMessage');
var timeoutDuration = 6000;

setTimeout(function () {
    alertElement.classList.add('fade');
    setTimeout(function () {
        alertElement.remove();
    }, 2000);
}, timeoutDuration);


$(document).ready(function () {
    $("#usernameInput").on('input', function () {
        var username = $(this).val();
        if (username.trim() === '') { 
            $('#checkIcon').css('display', 'none');
            $('#unCheckIcon').css('display', 'none');
            return;
        }
        $.ajax({
            type: "GET",
            url: "queryUserServlet",
            data: {uname: username},
            success: function (response) {
                console.log(response);
                if (response === null) {
                    $('#checkIcon').css('display', 'inline');
                    $('#unCheckIcon').css('display', 'none');
                } else {
                    if ($.isEmptyObject(response)) {
                        $('#unCheckIcon').css('display', 'none');
                        $('#checkIcon').css('display', 'inline');
                    } else {
                        $('#checkIcon').css('display', 'none');
                        $('#unCheckIcon').css('display', 'inline');

                    }
                }
            },
            error: function (response) {
                console.log("Error response:", response.responseText);
                alert("Error: " + response.responseText);
            }

        });
    });
});
