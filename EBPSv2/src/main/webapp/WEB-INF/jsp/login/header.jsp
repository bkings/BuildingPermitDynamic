<%
    String path = request.getContextPath();
    String token = "";
    try {
        token = session.getAttribute("token").toString();

        if (token.length() < 100) {
            out.println("<script>window.location.assign('" + request.getContextPath() + "/Emp/LoginVerification?path='+window.location.pathname);</script>");
        }
    } catch (Exception e) {
        out.println("<script>window.location.assign('" + request.getContextPath() + "/Emp/LoginVerification?path='+window.location.pathname);</script>");

    }

%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Welcome User</title>
        <link rel="stylesheet" href=" http://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.js"></script>
        <script src="http://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
        <style>
            .btn-success{height: 26px;}
            .form-control{height: 29px;}
            fieldset {
                border: 1px solid #f89800!important;
                display: table-cell !important;
                padding: 0 15px 25px !important;
                border-top: 1px solid  #f89800 !important;
            }
            legend {
                padding: 0 15px;
                border: none;
                width: auto;
                font-weight: bold;
                margin: 0;
                font-family: Arial;
                color: #000;
                font-size: 16px;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <br><br><br>