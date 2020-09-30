<%-- 
    Document   : TokenVerification
    Created on : Aug 26, 2019, 10:40:43 AM
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
            window.location.assign('<%= session.getAttribute("path")%>');
           </script>
    </body>
</html>
