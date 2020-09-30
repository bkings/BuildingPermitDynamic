<%-- 
    Document   : LoginVerification
    Created on : Aug 26, 2019, 10:18:55 AM
    Author     : kamal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <script>
            var token = localStorage.getItem('PHOENIX-TOKEN-BACK');
            console.log(token);
            if (token.length > 100) {
                window.location.assign("<%= request.getContextPath()%>/Emp/LoginVerification/" + token);
            } else {
                window.location.assign("<%= request.getContextPath()%>/");
            }
        </script>
    </body>
</html>
