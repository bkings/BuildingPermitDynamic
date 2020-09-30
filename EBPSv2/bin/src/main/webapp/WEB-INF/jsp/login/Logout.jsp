<%-- 
    Document   : Logout
    Created on : Aug 27, 2019, 3:57:49 PM
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
        <script>localStorage.removeItem('PHOENIX-TOKEN');
        window.location.assign('<%= request.getContextPath() %>');
        </script>
    </body>
</html>
