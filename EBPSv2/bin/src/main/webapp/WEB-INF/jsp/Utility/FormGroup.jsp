<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../login/header.jsp"%>
<fieldset>  <legend>Form Group</legend> 
    <form method='POST' id='dataFrom'> 
        <div class="row">
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Group Type</label>
                <select name='groupType' id='groupType' class='form-control' onchange="getRecord()">
                    <option value="1">नयाँ</option>
                    <option value="2">पुरानो</option>
                    <option value="3">तला थप</option>
                </select>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>User Type</label>
                <select name='userType' id='userType' class='form-control' onchange="getRecord()">
                    <option value="C">Chief</option>
                    <option value="A">Engineer</option>
                    <option value="B">Sub Engineer</option>
                    <option value="D" selected>Designer</option>
                    <option value="AD">Amin</option>
                    <option value="R">Rajasow</option>
                </select>
            </div>

            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Form Group </label>
                <select name='groupId' id='groupId' class='form-control'></select>
            </div>

            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Form</label>
                <input type="hidden" name="id" id="id">
                <select name='formId' id='formId' class='form-control formId'></select>
            </div>

            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Position</label>
                <input type="number" style="any" name="groupPosition" id="groupPosition" class="form-control">

            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Previous Form</label>
                <select name='previousForm' id='previousForm' class='form-control formId'></select>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>

                <label id='ActionMSG'>&nbsp;</label><br>
                <input type='button' onclick='doSave()' id='Save' value='Save' class='btn btn-success'/>
                <input type='button' onclick='doUpdate()'  id='Update' value='Update' class='btn btn-success' style='display: none'/></div>
        </div>
    </form>
</fieldset>  <br> <fieldset>  <legend>Form Group Data</legend> 
    <div class='row' id='table'></div>
</fieldset>
<script>



    function getRecord() {
        var gtype = document.getElementById('groupType').value;
        var utype = document.getElementById('userType').value;
        var combined = gtype + '-' + utype;
        var URL = "<%=path%>/api/Utility/FormGroup/" + combined;
        $.ajax({type: "GET", url: URL, headers: {'Authorization': '<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function (data) {
                if (data.length == 0) {
                    messages('Record Not Found');
                    document.getElementById('table').innerHTML = '';
                    return false;
                }
                document.getElementById('table').innerHTML = `<table class='table table-bordered table-hover table-striped' id='dataTable'><thead>
<tr><th>Form Id</th><th>User Type</th><th>FOrm Group</th><th>Form Name</th><th>Possion</th><th>Action</th></tr></thead><tbody></tbody></table>`;
                var tableData;
                for (var i = 0; i < data.length; i++)
                {
                    tableData = "<tr><td hidden>" + data[i].id + "</td><td>" + data[i].formId.id + "</td><td hidden>" + data[i].groupId.id + "</td><td hidden>" + data[i].userType.id + "</td><td hidden>" + data[i].groupPosition + "</td><td hidden>" + data[i].previousForm + "</td><td hidden>" + data[i].groupType + "</td><td>" + data[i].userType.designation + "</td><td>" + data[i].groupId.name + "</td><td>" + data[i].formId.name + "</td><td>" + data[i].groupPosition + "</td><td><a onclick='edit(" + (i + 1) + ")' class='glyphicon glyphicon-edit'></a> | <a onclick='recordDelete(\"" + data[i].id + "\")' class='glyphicon glyphicon-remove-circle'></a></td></tr>";
                    $('#dataTable').append(tableData);
                }
            }
        });
    }
</script>
<script>
    function edit(sn) {
        var id = ['id', 'formId', 'groupId', 'userType', 'groupPosition', 'previousForm', 'groupType'];
        for (var i = 0; i < id.length; i++) {
            console.log(id[i] + " " + document.getElementById("dataTable").rows[sn].cells.item(i).innerHTML);
            document.getElementById(id[i]).value = document.getElementById("dataTable").rows[sn].cells.item(i).innerHTML;
        }
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
        var URL = "<%=path%>/api/Utility/FormGroup";
        callApi(URL, requestData, "POST");
    }
    function doUpdate()
    {
        var dataForm = $('form').serializeArray();
        var requestData = {};
        $.each(dataForm, function (i, v) {
            requestData[v.name] = v.value;
        });
        var URL = "<%=path%>/api/Utility/FormGroup/" + document.getElementById('id').value;
        callApi(URL, requestData, "PUT");
    }
    function recordDelete(id) {
        if (!confirm('Are you sure'))
            return;
        var URL = "<%=path%>/api/Utility/FormGroup/" + id;
        callApi(URL, "", "DELETE");
    }
    getRecord();

    function getFormNameMaster()
    {
        var URL = "<%=path%>/api/Utility/FormNameMaster";
        $.ajax({type: "GET", url: URL, headers: {'Authorization': '<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function (data) {
                $('.formId').empty().append("<option value=''>Select</option>");
                for (var i = 0; i < data.length; i++)
                {
                    $('.formId').append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                }
            }
        });
    }
    function getFormGroupMaster()
    {
        var URL = "<%=path%>/api/Utility/FormGroupMaster";
        $.ajax({type: "GET", url: URL, headers: {'Authorization': '<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function (data) {

                for (var i = 0; i < data.length; i++)
                {
                    $('#groupId').append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                }
            }
        });
    }
    function getUserTypeMaster()
    {
        var URL = "<%=path%>/api/Utility/UserTypeMaster";
        $.ajax({type: "GET", url: URL, headers: {'Authorization': '<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function (data) {
                $('#userType').empty();
                for (var i = 0; i < data.length; i++)
                {
                    $('#userType').append("<option value='" + data[i].id + "'>" + data[i].designationNepali + "</option>");
                }
            }
        });
    }
        getUserTypeMaster();
    getFormNameMaster();
    getFormGroupMaster();

</script>

<%@include file="../login/footer.jsp" %>

<%--
return "\n{\"formId\": \""+formId+"\",\"groupId\": \""+groupId+"\"}";
--%>