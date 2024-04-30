<%-- 
    Document   : test
    Created on : Apr 29, 2024, 1:39:54 AM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        <title>Fetch Data</title>
    </head>
    <body>
        <input type="text" id="nameInput" placeholder="Name">
        <input type="text" id="ageInput" placeholder="Age">
        <button onclick="fetchData()">Fetch Data</button>

        <script type="text/javascript">
            function fetchData() {
                $.ajax({
                    type: "GET",
                    url: "TestServlet", // 这里是你的 Servlet 的 URL
                    success: function (response) {
                        alert(response); // 在收到响应时弹出响应内容
                    }
                });
            }

        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    </body>
</html>

