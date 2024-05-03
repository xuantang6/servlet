
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="css/userManagement.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body style="background-color: #999;" >
        <jsp:include page="adminSidePanel.jsp"/>
        <%
            String tips = (String) session.getAttribute("tips");
            if (tips != null) {
        %>
        <%= tips%>
        <%
            }
            session.removeAttribute("tips");
        %>

            ${tips}
        <div id="main">
            <div class="head">  
                <div class="col-div-6"></div>
                <div class="col-div-6"></div>
                <div class="clearfix"></div>
                <div class="col-div-8">
                    <div class="box-8">
                        <div class="content-box">
                            <!-- Staff search form -->
                            <form action="staff_search.php" method="POST">
                                <p>Users
                                    <span style="cursor: pointer">
                                        <button type="button" class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                            Add Users
                                        </button>
                                    </span>

                                    <span><button class="btn btn-outline-light btnsearch" name="submit">Search</button></span>

                                    <span><input type="text" placeholder="Search..." name="search" autocomplete="off" class="iptsearch"></span>
                                </p>
                            </form>
                            <br>
                            <div class="table-responsive" >
                                <table>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Username</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Gender</th>
                                        <th scope="col">Contact</th>
                                        <th scope="col">Address</th>
                                        <th scope="col">User Type</th>
                                        <th scope="col">Status</th>
                                        <th scope="col">Avatar</th>
                                        <th scope="col">Operations</th>
                                    </tr>
                                    <c:forEach items="${userList}" var="user" varStatus="loop" >

                                        <tr>
                                            <th scope='row'><c:out value="${loop.index + 1}" /></th>
                                            <td>${user.username}</td>
                                            <td>${user.email}</td>  
                                            <td>
                                                <c:if test="${user.gender == 'M'}">Male</c:if>
                                                <c:if test="${user.gender == 'F' }">Female</c:if>
                                                </td>
                                                <td>${user.contact_no}</td>
                                            <td class="overflow-text">${user.address}</td>
                                            <td>${user.account_type}</td>
                                            <td>
                                                <c:if test="${user.status == true}">Active</c:if>
                                                <c:if test="${user.status == false}">Inactive</c:if>
                                                </td>
                                                <td><img height="40px" src="${user.profile_pic}"></td>

                                            <td>
                                                <button style="color: white" data-uname="${user.username}" data-bs-toggle="modal" data-bs-target="#updateBackdrop" class="btn btn-outline-primary editButton">Edit</button>
                                                <button id="delBtn" style="color: white" data-bs-toggle="modal" data-bs-target="#delBackdrop" class='btn btn-outline-danger btndel' onclick="setUsername('${user.username}')">Delete</button>

                                            </td>
                                        </tr>
                                    </c:forEach>

                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>

        <!-- Delete modal -->
        <div class="modal fade" id="delBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="delBackdropLabel">Remove Users</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body" id="modalBody">
                        Are you sure you want to delete ?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="button" class="btn btn-danger delBtn" onclick="deleteUser()" >Delete</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Add modal -->
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header ">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Adding New Users</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="addUserServlet" method="post" enctype="multipart/form-data">
                        <div class="modal-body" style="height: 72vh; overflow-y: auto;">
                            <div class="form-group mb-3">
                                <label for="">Username</label>
                                <input type="text" class="form-control" placeholder="Username" name="username" id="">
                            </div>

                            <div class="form-group mb-3">
                                <label for="">Email</label>
                                <input type="email" class="form-control" placeholder="Email" name="email" id="">
                            </div>

                            <label for="">Gender</label>
                            <select class="form-select mb-3" name="gender" aria-label="Default select example">
                                <option hidden disabled selected>Select Gender</option>
                                <option value="M">Male</option>
                                <option value="F">Female</option>
                            </select>

                            <div class="form-group mb-3">
                                <label for="">Contact Number</label>
                                <input type="text" class="form-control" placeholder="Contact" name="contact" id="">
                            </div>

                            <div class="form-group mb-3">
                                <label for="">Home Address</label>
                                <input type="text" class="form-control" placeholder="Address" name="address" id="">
                            </div>

                            <label for="">Avatar Image</label>
                            <div class="input-group mb-3">
                                <label class="input-group-text" for="inputGroupFile01">Avatar</label>
                                <input name="profile_pic" type="file" class="form-control" id="inputGroupFile01">
                            </div>

                            <label for="">User Type</label>
                            <select class="form-select mb-3" name="userType" aria-label="Default select example" required>
                                <option hidden disabled selected>Select User Type</option>
                                <option value="staff">Staff</option>
                                <option value="customer">Customer</option>
                            </select>

                            <label for="">Account Status</label>
                            <select class="form-select mb-3" name="accStatus" aria-label="Default select example">
                                <option hidden disabled selected>Select Account Status</option>
                                <option value="true">Active</option>
                                <option value="false">Inactive</option>
                            </select>

                            <div class="form-group mb-3">
                                <label for="">Password</label>
                                <input type="password" class="form-control" placeholder="Password" name="password" id="">
                            </div>

                            <div class="form-group mb-3">
                                <label for="">Confirm Password</label>
                                <input type="password" class="form-control" placeholder="Confirm Password" name="confPassword" id="">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- update modal -->
        <div class="modal fade" id="updateBackdrop" data-bs-backdrop="updatestatic" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="updateBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header ">
                        <h1 class="modal-title fs-5" id="updateBackdropLabel">Modifying Users</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="updateUserServlet" method="post" enctype="multipart/form-data">
                        <div class="modal-body" style="height: 72vh; overflow-y: auto;">
                            <div class="form-group mb-3">
                                <label for="">Username</label>
                                <input type="text" class="form-control" placeholder="Username" id="updateUsername" value="" name="username">
                            </div>

                            <div class="form-group mb-3">
                                <label for="">Email</label>
                                <input type="email" class="form-control" placeholder="Email" name="email" id="updateEmail">
                            </div>

                            <label for="">Gender</label>
                            <select class="form-select mb-3" name="gender" id="updateGender" aria-label="Default select example">
                                <option hidden disabled >Select Gender</option>
                                <option value="M">Male</option>
                                <option id="femaleOpt" value="F">Female</option>
                            </select>

                            <div class="form-group mb-3">
                                <label for="">Contact Number</label>
                                <input value="" type="text" value="" class="form-control" placeholder="Contact" name="contact" id="updateContact">
                            </div>

                            <div class="form-group mb-3">
                                <label for="">Home Address</label>
                                <input value="" type="text" value="" class="form-control" placeholder="Address" name="address" id="updateAddress">
                            </div>

                            <label for="">Avatar Image</label>
                            <div class="input-group mb-3">
                                <label class="input-group-text" for="inputGroupFile01">Avatar</label>
                                <img id="img" src="" height="100" alt="alt"/>
                                <input name="imgPath" type="hidden" class="form-control" id="imgPath">
                                <input name="profile_pic" type="file" class="form-control" id="updateAvatar">
                            </div>

                            <label for="">User Type</label>
                            <select class="form-select mb-3" name="userType" aria-label="Default select example" required>
                                <option hidden disabled>Select User Type</option>
                                <option value="staff">Staff</option>
                                <option id="custOpt" value="customer">Customer</option>
                            </select>


                            <label for="">Account Status</label>
                            <select class="form-select mb-3" name="accStatus" aria-label="Default select example">
                                <option hidden disabled>Select Account Status</option>
                                <option value="true">Active</option>
                                <option id="inactiveOpt" value="false">Inactive</option>
                            </select>

                            <div class="form-group mb-3">
                                <label for="">Password</label>
                                <input value="" type="password" class="form-control" placeholder="Password" name="password" id="updatePassword">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Update</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="js/userManagement.js" ></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script>
 $('#staticBackdrop form').submit(function(event) {
    // 遍历表单中的所有输入字段
    var isValid = true;
    $(this).find('input, select').each(function() {
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


        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

    </body>
</html>
