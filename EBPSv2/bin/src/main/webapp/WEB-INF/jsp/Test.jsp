<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Welcome User</title>
        <link rel="stylesheet" href=" http://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.css"/>
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
            <fieldset>  <legend></legend> 
                <br><Br>
                <form method='POST' id='fileFrom' onsubmit="return false;"> 
                    <div class="row">


                        <div class='col-lg-12 col-sm-12 col-md-12 col-xs-12'>
                            <label>API</label>
                            <input type='text' id='api' class='form-control'/>
                        </div>
                        <div class='col-lg-12 col-sm-12 col-md-12 col-xs-12'>
                            <label>Token</label>
                            <input type='text' id='token' value="${token}" class='form-control'/>
                        </div>
                        <div class='col-lg-4 col-sm-4 col-md-4 col-xs-6'>
                            <label>Method</label>
                            <select  id='method' class='form-control'>
                                <option>GET</option>
                                <option>POST</option>
                                <option>PUT</option>
                            </select>
                        </div>
                        <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>

                            <label id='ActionMSG'>&nbsp;</label><br>
                            <input type='submit' onclick='doSave()' id='Save' value='Go' class='btn btn-success'/>
                        </div>
                        <div class='col-lg-12 col-sm-12 col-md-12 col-xs-12'>
                            <textarea id="data"  class="form-control" rows="10"></textarea>
                        </div>
                        <br>
                        <div class='col-lg-12 col-sm-12 col-md-12 col-xs-12' id="output">

                        </div>
                    </div>
                </form>
            </fieldset> 
            <script>
                var dd = localStorage.getItem("PHOENIX-TOKEN-BACK");
                if (dd) {
                    document.getElementById("token").value = "Bearer " + dd;
                }
                function callApi(URL, requestData, apiMethod)
                {
                    $('.btn').button('loading');
                    $.ajax({type: apiMethod, url: URL, headers: {'Authorization': document.getElementById('token').value}, data: JSON.stringify(requestData), contentType: "application/json; charset=utf-8", dataType: "json",
                        success: function (data) {
                            document.getElementById("output").innerHTML = JSON.stringify(data);
                            $('.btn').button('reset');
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            $('.btn').button('reset');
                            messages(XMLHttpRequest + " Status: " + textStatus + " Error: " + errorThrown);
                        }});
                }
                function doSave()
                {
                    var dataForm = $('form').serializeArray();
                    var requestData = {};
                    $.each(dataForm, function (i, v) {
                        requestData[v.name] = v.value;
                    });
                    var URL = document.getElementById('api').value;
                    var data = document.getElementById('data').value;
                    if (data.length > 20) {
                        data = JSON.parse(document.getElementById('data').value);
                    } else {
                        data = "";
                    }
                    var method = document.getElementById('method').value;
                    if (method === "GET")
                        data = "";
                    callApi(URL, data, method);
                }

            </script>
        </div>
        <script>function messages(msg) {
                alert(msg);
            }</script>  
        <footer id="footer" >
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12">
                        <p>&nbsp;</p>
                        <p>Copy Right &copy; 2014-2018 </p>
                    </div>
                </div>
            </div>
        </footer> 
    </body>
</html>

