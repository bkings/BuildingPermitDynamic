<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../login/header.jsp"%>
<fieldset>  <legend>File Storage Application</legend> 
    <form method='POST' id='dataFrom'> 
        <div class="row">
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Application No</label>
                <input value="7677000001" type='text' name='applicationNo' id='applicationNo' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>File Type</label>
                <select  name='fileType' id='fileType' class='form-control'>
                    <option>1</option> 
                    <option>2</option> 
                    <option>3</option>
                </select>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>File </label>
                <input type='file' name='file' id='file' multiple/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>

                <label id='ActionMSG'>&nbsp;</label><br>
                <input type='submit' id='Save' value='Save' class='btn btn-success'/>

            </div>
        </div>
    </form>
</fieldset>  <br> <fieldset>  <legend>FileStorageApplication Data</legend> 
    <div class='row' id='table'></div>
</fieldset>
<script>
    $('#dataFrom').submit(function (event) {
        event.preventDefault();
        var formData = new FormData(this);
//        $('.btn').button('loading');
        var URL = "<%=path%>/api/Processing/FileStorageApplication/" + document.getElementById('applicationNo').value;
        $.ajax({type: "POST", url: URL,
            headers: {'Authorization': '<%=token%>'}, contentType: false, data: formData, catch : false, processData: false,
            success: function (data) {
                var msg = "";
                if (data['error'])
                    msg = data['error'].message;
                else
                    msg = data.message;
                messages(msg);
                $('.btn').button('reset');
            }
        });
    });
</script>
<script>
    function edit(sn) {
        var id = ['applicationNo', 'fileType', 'fileNo', 'fileUrl', 'enterDate', 'enterBy'];
        for (var i = 0; i < id.length; i++)
            document.getElementById(id[i]).value = document.getElementById("dataTable").rows[sn].cells.item(i).innerHTML;
        $('#Update').show();
        $('#Save').hide();
        document.getElementById('Update').focus();
    }</script>
<script>
    function callApi(URL, requestData, apiMethod)
    {
        $('.btn').button('loading');
        var msg = '';
        $.ajax({type: apiMethod, url: URL, headers: {'Authorization': '<%=token%>'}, data: JSON.stringify(requestData), contentType: "application/json; charset=utf-8", dataType: "json",
            success: function (data) {
                $('.btn').button('reset');
                if (!data['error']) {
                    $('#Update').hide();
                    $('#Save').show();
                    msg = data['message'];
                    document.getElementById('dataFrom').reset();
                    getRecord();
                } else {
                    msg = data['error'].message;
                }
                messages(msg);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $('.btn').button('reset');
                messages(XMLHttpRequest + " Status: " + textStatus + " Error: " + errorThrown);
            }});
    }
</script>
<script>
    function doSave()
    {
        var dataForm = $('form').serializeArray();
        var requestData = {};
        $.each(dataForm, function (i, v) {
            requestData[v.name] = v.value;
        });
        var URL = "<%=path%>/api/Processing/FileStorageApplication/7677000001";
        callApi(URL, requestData, "POST");
    }
    function doUpdate()
    {
        var dataForm = $('form').serializeArray();
        var requestData = {};
        $.each(dataForm, function (i, v) {
            requestData[v.name] = v.value;
        });
        var URL = "<%=path%>/api/Processing/FileStorageApplication/" + document.getElementById('applicationNo').value;
        callApi(URL, requestData, "PUT");
    }
    function getRecord()
    {
        var URL = "<%=path%>/api/Processing/FileStorageApplication/" + document.getElementById('applicationNo').value;
        $.ajax({type: "GET", url: URL, headers: {'Authorization': '<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function (data) {
                if (data.length == 0) {
                    messages('Record Not Found');
                    document.getElementById('table').innerHTML = '';
                    return false;
                }
                document.getElementById('table').innerHTML = `<table class='table table-bordered table-hover table-striped' id='dataTable'><thead>
<tr><th>Application No</th><th>File Type</th><th>File No</th><th>File Url</th><th>Enter Date</th><th>Enter By</th><th>Action</th></tr></thead><tbody></tbody></table>`;
                var tableData;
                for (var i = 0; i < data.length; i++)
                {
                    tableData = "<tr><td>" + data[i].applicationNo + "</td><td>" + data[i].fileType.name + "</td><td>" + data[i].fileNo + "</td><td>" + data[i].fileUrl + "</td><td>" + data[i].enterDate + "</td><td>" + data[i].enterBy + "</td><td> <a onclick='recordDelete(\""+data[i].fileUrl+"\")' class='glyphicon glyphicon-remove-circle'></a></td></tr>";
                    $('#dataTable').append(tableData);
                }
            }
        });
    }
    function recordDelete(id) {
        if (!confirm('Are you sure'))
            return;
        var URL = "<%=path%>/api/Processing/FileStorageApplication?id=" + id;
        callApi(URL, "", "DELETE");
    }
    getRecord();
</script>

<%@include file="../login/footer.jsp" %>

<%--
return "\n{\"applicationNo\": \""+applicationNo+"\",\"fileType\": \""+fileType+"\",\"fileNo\": \""+fileNo+"\",\"fileUrl\": \""+fileUrl+"\",\"enterDate\": \""+enterDate+"\",\"enterBy\": \""+enterBy+"\"}";
--%>