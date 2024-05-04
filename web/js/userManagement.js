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


var alertElement = document.getElementById('alertContainer');
var closeButton = document.getElementById('closeButton');


var timeoutDuration = 5000;

setTimeout(function () {

    alertElement.classList.add('fade');
    alertElement.classList.remove('show');


    setTimeout(function () {
        alertElement.remove();
    }, 1000);
}, timeoutDuration);


document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector('form');

    form.addEventListener('input', function () {
        const inputs = form.querySelectorAll('input');
        const selects = form.querySelectorAll('select');
        let disableSubmit = false;

        inputs.forEach(input => {
            if (!input.value.trim()) {
                disableSubmit = true;
            }
        });

        selects.forEach(select => {
            if (!select.value.trim() || select.value === 'Select Gender' || select.value === 'Select User Type' || select.value === 'Select Account Status') {
                disableSubmit = true;
            }
        });

        const submitButton = form.querySelector('button[type="submit"]');
        submitButton.disabled = disableSubmit;
    });
});

$('#staticBackdrop form').submit(function (event) {
    // 遍历表单中的所有输入字段
    var isValid = true;
    $(this).find('input, select').each(function () {
        // 检查是否有必填字段为空值
        if ($(this).prop('required') && !$(this).val()) {
            isValid = false;
        }
        // 检查是否有必选项未选择
        if ($(this).is('select') && $(this).prop('required') && !$(this).val()) {
            isValid = false;
        }
    });

    // 如果有必填字段为空值或必选项未选择，则阻止表单提交
    if (!isValid) {
        event.preventDefault();
        alert('Please fill in all required fields.');
    }
});


$(document).ready(function () {
   
    $(".btndel").click(function () {
        $.ajax({
            type: "GET",
            url: "delUserServlet",
            success: function (response) {

                if(response.account_type === 'staff'){
                    $(".delBtn").removeAttr("onclick");
                    alert("You don't have permission to delete users.");
                    
                    $(".delBtn").click(function () {
                        alert("nono");
                    });
                }

                
            },
            error: function (xhr, status, error) {
                console.error("Error:", error);
            }
        });
    });
});

$(document).ready(function () {
   
    $(".editButton").click(function () {
        $.ajax({
            type: "GET",
            url: "updateUserServlet",
            success: function (response) {

                if (response.account_type === 'staff') {
                    $("#upBtn").prop("disabled", true);
                    alert("You don't have permission to edit users.");
                }

                
            },
            error: function (xhr, status, error) {
                console.error("Error:", error);
            }
        });
    });
});
function checkUserType() {
    var userType = document.getElementById("userType").value;
    var passwordSection = document.getElementById("passwordSection");
    var confirmPasswordSection = document.getElementById("confirmPasswordSection");

    if (userType === "staff") {
        passwordSection.style.display = "none";
        confirmPasswordSection.style.display = "none";
    } else {
        passwordSection.style.display = "block";
        confirmPasswordSection.style.display = "block";
    }

    // 如果是 staff 类型，则自动填充密码
    if (userType === "staff") {
        document.getElementById("password").value = "iloveGreenBasket4ever!";
        document.getElementById("confirmPassword").value = "iloveGreenBasket4ever!";
    }
}

// 监听用户类型变化
document.getElementById("userType").addEventListener("change", function() {
    checkUserType();
});

// 初始化检查用户类型
checkUserType();