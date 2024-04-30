var usernameToDelete;

function setUsername(username) {
    usernameToDelete = username;
    document.getElementById("modalBody").innerText = "Are you sure you want to delete " + username + " ?";
}

function deleteUser() {
    if (usernameToDelete) {
        window.location.href = "delUserServlet?uname=" + usernameToDelete;
    }
}

$(document).ready(function () {
    $(".editButton").click(function () {
        var username = $(this).data("uname");
        $.ajax({
            type: "GET",
            url: "queryUserServlet",
            data: {uname: username},
            success: function (response) {

                var inputUname = document.getElementById("updateUsername");
                var inputPassword = document.getElementById("updatePassword");
                var inputEmail = document.getElementById("updateEmail");

                var inputContact = document.getElementById("updateContact");
                var inputAddress = document.getElementById("updateAddress");


                inputUname.value = response.username;
                inputEmail.value = response.email;
                inputContact.value = response.contact_no;
                inputAddress.value = response.address;
                inputPassword.value = response.password;
                var genderOption = document.getElementById("femaleOpt");
                if (response.gender === 'F') {
                    genderOption.setAttribute("selected", "selected");
                } else {
                    genderOption.removeAttribute("selected");
                }

                var userOption = document.getElementById("custOpt");
                if (response.account_type === 'customer') {
                    userOption.setAttribute("selected", "selected");
                } else {
                    userOption.removeAttribute("selected");
                }

                var statusOption = document.getElementById("inactiveOpt");
                if (response.status === false) {
                    statusOption.setAttribute("selected", "selected");
                } else {
                    statusOption.removeAttribute("selected");
                }

                var imgElement = document.getElementById("img");
                imgElement.src = response.profile_pic;

            },
            error: function (response) {
                console.log("Error response:", response.responseText);
                alert("Error: " + response.responseText);
            }

        });
    });
});