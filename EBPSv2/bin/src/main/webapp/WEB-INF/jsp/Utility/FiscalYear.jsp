<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../login/header.jsp"%>
<fieldset>  <legend>Fiscal Year</legend> 
    <form method='POST' id='dataFrom'> 
        <div class="row">
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Year Code</label>
                <input type='text' name='yearCode' id='yearCode' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Year Name</label>
                <input type='text' name='yearName' id='yearName' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Start Date</label>
                <input type='text' name='startDate' id='startDate' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>End Date</label>
                <input type='text' name='endDate' id='endDate' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Status</label>
                <select name='status' id='status' class='form-control'>
                    <option value="Y">Active</option>
                    <option value="N">Passive</option>
                </select>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>

                <label id='ActionMSG'>&nbsp;</label><br>
                <input type='button' onclick='doSave()' id='Save' value='Save' class='btn btn-success'/>
                <input type='button' onclick='doUpdate()'  id='Update' value='Update' class='btn btn-success' style='display: none'/></div>
        </div>
    </form>
</fieldset>  <br> <fieldset>  <legend>Fiscal Year Data</legend> 
    <div class='row' id='table'></div>
</fieldset>
<script>
    var yearCode = "";
    function edit(sn) {
        var data = document.getElementById(sn);
        var id = ['yearCode', 'yearName', 'startDate', 'endDate', 'status'];
        for (var i = 0; i < id.length; i++)
            document.getElementById(id[i]).value = data.children[i].innerHTML;
        $('#Update').show();
        $('#Save').hide();
        document.getElementById('Update').focus();
        yearCode = document.getElementById('yearCode').value;
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
        var URL = "<%=path%>/api/Utility/FiscalYear";
        callApi(URL, requestData, "POST");
    }
    function doUpdate()
    {
        var dataForm = $('form').serializeArray();
        var requestData = {};
        $.each(dataForm, function (i, v) {
            requestData[v.name] = v.value;});
        var URL = "<%=path%>/api/Utility/FiscalYear/" + yearCode;
        callApi(URL, requestData, "PUT");
    }
    function getRecord()
    {
        var URL = "<%=path%>/api/Utility/FiscalYear";
        $.ajax({type: "GET", url: URL, headers: {'Authorization': '<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function (data) {
                if (data.length == 0) {
                    messages('Record Not Found');
                    document.getElementById('table').innerHTML = '';
                    return false;
                }
                document.getElementById('table').innerHTML = `<table class='table table-bordered table-hover table-striped' id='dataTable'><thead>
<tr><th>Year Code</th><th>Year Name</th><th>Start Date</th><th>End Date</th><th>Status</th><th>Action</th></tr></thead><tbody></tbody></table>`;
                var tableData;
                for (var i = 0; i < data.length; i++)
                {
                    tableData = "<tr id='" + i + "'><td >" + data[i].yearCode + "</td><td>" + data[i].yearName + "</td><td>" + data[i].startDate + "</td><td>" + data[i].endDate + "</td><td>" + data[i].status + "</td><td><a onclick='edit(" + (i) + ")' class='glyphicon glyphicon-edit'></a> | <a onclick='recordDelete(" + data[i].yearCode + ")' class='glyphicon glyphicon-remove-circle'></a></td></tr>";
                    $('#dataTable').append(tableData);
                }
                $('#dataTable').DataTable();
            }
        });
    }
    function recordDelete(id) {
        if (!confirm('Are you sure'))
            return;
        var URL = "<%=path%>/api/Utility/FiscalYear/" + id;
        callApi(URL, "", "DELETE");
    }
    getRecord();
</script>

<%@include file="../login/footer.jsp" %>

<%--
return "\n{\"yearCode\": \""+yearCode+"\",\"yearName\": \""+yearName+"\",\"startDate\": \""+startDate+"\",\"endDate\": \""+endDate+"\",\"status\": \""+status+"\"}";
--%>