<%-- 
    Document   : index
    Created on : Aug 13, 2019, 4:13:46 PM
    Author     : kamal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <input type="submit" id="submitButton" style="display: none">
</head>
<body>
    <script>
        $.ajax({type: "POST", url: "<%= request.getContextPath()%>/api/CheckDB", contentType: "application/json; charset=utf-8", dataType: "json",
            success: function (data) {
                if (!data['error']) {
                    $('#submitButton').show();
                } else {
                    $.ajax({type: "POST", url: "<%= request.getContextPath()%>/api/DatabaseConfigration", contentType: "application/json; charset=utf-8", dataType: "json",
                        success: function (data)
                        {
                            if (!data['error']) {
                                $('#submitButton').show();
                            } else
                            {
                                alert('Database connect');

                            }
                        }
                    });
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $('.btn').button('reset');
                messages(XMLHttpRequest + " Status: " + textStatus + " Error: " + errorThrown);
            }});


    </script>
</body>
</html>
