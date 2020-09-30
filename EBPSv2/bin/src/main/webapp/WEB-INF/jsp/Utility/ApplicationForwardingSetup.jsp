<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../login/header.jsp"%>
<fieldset>  <legend>ApplicationForwardingSetup</legend> 
    <form method='POST' id='dataFrom'> 
        <div class="row">
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden="">
                <label>Id</label>
                <input type='text' name='id' id='id' class='form-control'/>
            </div>

            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>form Group</label>
                <select name='formGroup' id='formGroup' class='form-control' onchange="getRecord(this.value)"></select>

            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Forward By</label>
                <select name='forwardBy' id='forwardBy' class='form-control userType'>
                    <option value="D">Designer</option>
                    <option value="B">Sub Engineer</option>
                    <option value="A">Engineer</option>
                    <option value="AD">Amin</option>
                    <option value="R">Rajasow</option>
                    <option value="C">Chief</option>
                </select>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Forward To</label>
                <select name='forwardTo' id='forwardTo' class='form-control userType'>
                    <option value="D">Designer</option>
                    <option value="B">Sub Engineer</option>
                    <option value="A">Engineer</option>
                    <option value="AD">Amin</option>
                    <option value="R">Rajasow</option>
                    <option value="C">Chief</option>
                </select>

            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>

                <label id='ActionMSG'>&nbsp;</label><br>
                <input type='button' onclick='doSave()' id='Save' value='Save' class='btn btn-success'/>
                <input type='button' onclick='doUpdate()'  id='Update' value='Update' class='btn btn-success' style='display: none'/></div>
        </div>
    </form>
</fieldset>  <br> <fieldset>  <legend>ApplicationForwardingSetup Data</legend> 
    <div class='row' id='table'></div>
</fieldset>
<script>
    function edit(sn) {
        var id = ['id', 'forwardBy', 'forwardTo', 'formGroup'];
        for (var i = 0; i < id.length; i++)
            document.getElementById(id[i]).value = document.getElementById("dataTable").rows[sn].cells.item(i).innerHTML;
        $('#Update').show();
        $('#Save').hide();
        document.getElementById('Update').focus();
    }

    function getUserType(userType) {
        if (userType === "C")
            return "Chief";
        else if (userType === "R")
            return "Rajasow";
        else if (userType === "AD")
            return "Amin";
        else if (userType === "A")
            return "Engineer";
        else if (userType === "B")
            return "Sub Engineer";
        else if (userType === "D")
            return "Designer";
        else
            userType;
    }
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
                    getRecord(document.getElementById('formGroup').value);
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
        var URL = "<%=path%>/api/Utility/ApplicationForwardingSetup";
        callApi(URL, requestData, "POST");
    }
    function doUpdate()
    {
        var dataForm = $('form').serializeArray();
        var requestData = {};
        $.each(dataForm, function (i, v) {
            requestData[v.name] = v.value;
        });
        var URL = "<%=path%>/api/Utility/ApplicationForwardingSetup/" + document.getElementById('id').value;
        callApi(URL, requestData, "PUT");
    }
    function getRecord(formGroup)
    {
        var URL = "<%=path%>/api/Utility/ApplicationForwardingSetup?formGroup=" + formGroup;
        $.ajax({type: "GET", url: URL, headers: {'Authorization': '<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function (data) {
                if (data.length === 0) {
                    messages('Record Not Found');
                    document.getElementById('table').innerHTML = '';
                    return false;
                }
                document.getElementById('table').innerHTML = `<table class='table table-bordered table-hover table-striped' id='dataTable'><thead>
<tr><th hidden>Id</th><th>Form Group</th><th>Forward By</th><th>Forward To</th><th>Action</th></tr></thead><tbody></tbody></table>`;
                var tableData;
                for (var i = 0; i < data.length; i++)
                {
                    tableData = "<tr><td hidden>" + data[i].id + "</td><td hidden>" + data[i].forwardBy + "</td><td hidden>" + data[i].forwardTo + "</td><td hidden>" + data[i].formGroup.id + "</td><td>" + data[i].formGroup.name + "</td><td>" + getUserType(data[i].forwardBy) + "</td><td>" + getUserType(data[i].forwardTo) + "</td><td><a onclick='edit(" + (i + 1) + ")' class='glyphicon glyphicon-edit'></a> | <a onclick='recordDelete(" + data[i].id + ")' class='glyphicon glyphicon-remove-circle'></a></td></tr>";
                    $('#dataTable').append(tableData);
                }
            }
        });
    }
    function recordDelete(id) {
        if (!confirm('Are you sure'))
            return;
        var URL = "<%=path%>/api/Utility/ApplicationForwardingSetup/" + id;
        callApi(URL, "", "DELETE");
    }
    function getFormGroupMasterRecord()
    {
        var URL = "<%=path%>/api/Utility/FormGroupMaster";
        $.ajax({type: "GET", url: URL, headers: {'Authorization': '<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function (data) {
                $('#formGroup').empty();
                for (var i = 0; i < data.length; i++)
                {
                    tableData = `<option value='` + data[i].id + `'>` + data[i].name + `</option>`;
                    $('#formGroup').append(tableData);
                }
                if (data.length > 0) {
                    getRecord(data[0].id);
                }
            }

        });
    }
    getFormGroupMasterRecord();
    function getUserTypeMaster()
    {
        var URL = "<%=path%>/api/Utility/UserTypeMaster";
        $.ajax({type: "GET", url: URL, headers: {'Authorization': '<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function (data) {
                $('.userType').empty();
                for (var i = 0; i < data.length; i++)
                {
                    $('.userType').append("<option value='" + data[i].id + "'>" + data[i].designationNepali + "</option>");
                }
            }
        });
    }
    getUserTypeMaster();
</script>

<%@include file="../login/footer.jsp" %>

<%--
return "\n{\"id\": \""+id+"\",\"forwardBy\": \""+forwardBy+"\",\"forwardTo\": \""+forwardTo+"\"}";
--%>