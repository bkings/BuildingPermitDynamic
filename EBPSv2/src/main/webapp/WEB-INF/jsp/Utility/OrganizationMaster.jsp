<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../login/header.jsp"%>
<fieldset>  <legend>Organization Master</legend> 
    <form method='POST' id='dataFrom'> 
        <div class="row">
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden="">
                <label>Id</label>
                <input type='text' name='id' id='id' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Address</label>
                <input type='text' name='address' id='address' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Province</label>
                <input type='text' name='province' id='province' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Email</label>
                <input type='text' name='email' id='email' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Mayor Contat No</label>
                <input type='text' name='mayorContatNo' id='mayorContatNo' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Mayor Name</label>
                <input type='text' name='mayorName' id='mayorName' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Name</label>
                <input type='text' name='name' id='name' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Office Contact No</label>
                <input type='text' name='officeContactNo' id='officeContactNo' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Office Name</label>
                <input type='text' name='officeName' id='officeName' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>File Location</label>
                <input type='text' name='fileLocation' id='fileLocation' class='form-control'/>
            </div>

            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Phase1a</label>
                <input type='text' name='phase1a' id='phase1a' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Phase1b</label>
                <input type='text' name='phase1b' id='phase1b' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Phase1c</label>
                <input type='text' name='phase1c' id='phase1c' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Phase1d</label>
                <input type='text' name='phase1d' id='phase1d' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Phase2a</label>
                <input type='text' name='phase2a' id='phase2a' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Phase2b</label>
                <input type='text' name='phase2b' id='phase2b' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Phase2c</label>
                <input type='text' name='phase2c' id='phase2c' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Phase2d</label>
                <input type='text' name='phase2d' id='phase2d' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Phase3a</label>
                <input type='text' name='phase3a' id='phase3a' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Phase3b</label>
                <input type='text' name='phase3b' id='phase3b' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Phase3c</label>
                <input type='text' name='phase3c' id='phase3c' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Phase3d</label>
                <input type='text' name='phase3d' id='phase3d' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Secretary Contat No</label>
                <input type='text' name='secretaryContatNo' id='secretaryContatNo' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Secretary Name</label>
                <input type='text' name='secretaryName' id='secretaryName' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Sub Mayor Contat No</label>
                <input type='text' name='subMayorContatNo' id='subMayorContatNo' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Sub Mayor Name</label>
                <input type='text' name='subMayorName' id='subMayorName' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Url</label>
                <input type='text' name='url' id='url' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>

                <label id='ActionMSG'>&nbsp;</label><br>
                <input type='button' onclick='doSave()' id='Save' value='Save' class='btn btn-success'/>
                <input type='button' onclick='doUpdate()'  id='Update' value='Update' class='btn btn-success' style='display: none'/></div>
        </div>
    </form>
</fieldset>  <br> <fieldset>  <legend>Organization Master Data</legend> 
    <div class='row' id='table'></div>
</fieldset>
<script>
    function edit(sn) {
        var id = ['id','fileLocation', 'address', 'province', 'email', 'mayorContatNo', 'mayorName', 'name', 'officeContactNo', 'officeName', 'phase1a', 'phase1b', 'phase1c', 'phase1d', 'phase2a', 'phase2b', 'phase2c', 'phase2d', 'phase3a', 'phase3b', 'phase3c', 'phase3d', 'secretaryContatNo', 'secretaryName', 'subMayorContatNo', 'subMayorName', 'url'];
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
        var URL = "<%=path%>/api/Utility/OrganizationMaster";
        callApi(URL, requestData, "POST");
    }
    function doUpdate()
    {
        var dataForm = $('form').serializeArray();
        var requestData = {};
        $.each(dataForm, function (i, v) {
            requestData[v.name] = v.value;
        });
        var URL = "<%=path%>/api/Utility/OrganizationMaster";
        callApi(URL, requestData, "PUT");
    }
    function getRecord()
    {
        var URL = "<%=path%>/api/Utility/OrganizationMaster";
        $.ajax({type: "GET", url: URL, headers: {'Authorization': '<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function (data) {
                if (data.length === 0) {
                    messages('Record Not Found');
                    document.getElementById('table').innerHTML = '';
                    return false;
                }
                document.getElementById('table').innerHTML = `<table class='table table-bordered table-hover table-striped' id='dataTable'><thead>
<tr><th hidden>Id</th><th>Address</th><th>province</th><th hidden>Email</th><th hidden>Mayor Contat No</th><th hidden>Mayor Name</th><th>Name</th><thhidden>Office Contact No</th><th>Office Name</th><th>Action</th></tr></thead><tbody></tbody></table>`;
                var tableData;
                for (var i = 0; i < data.length; i++)
                {
                    tableData = "<tr><td hidden>" + data[i].id + "</td><td hidden>" + data[i].fileLocation + "</td><td>" + data[i].address + "</td><td>" + data[i].province + "</td><td hidden>" + data[i].email + "</td><td hidden>" + data[i].mayorContatNo + "</td><td hidden>" + data[i].mayorName + "</td><td>" + data[i].name + "</td><td hidden>" + data[i].officeContactNo + "</td><td >" + data[i].officeName + "</td><td hidden>" + data[i].phase1a + "</td><td hidden>" + data[i].phase1b + "</td><td hidden>" + data[i].phase1c + "</td><td hidden>" + data[i].phase1d + "</td><td hidden>" + data[i].phase2a + "</td><td hidden>" + data[i].phase2b + "</td><td hidden>" + data[i].phase2c + "</td><td hidden>" + data[i].phase2d + "</td><td hidden>" + data[i].phase3a + "</td><td hidden>" + data[i].phase3b + "</td><td hidden>" + data[i].phase3c + "</td><td hidden>" + data[i].phase3d + "</td><td hidden>" + data[i].secretaryContatNo + "</td><td hidden>" + data[i].secretaryName + "</td><td hidden>" + data[i].subMayorContatNo + "</td><td hidden>" + data[i].subMayorName + "</td><td hidden>" + data[i].url + "</td><td><a onclick='edit(" + (i + 1) + ")' class='glyphicon glyphicon-edit'></a> </td></tr>";
//                    | <a onclick='recordDelete(" + data[i].id + ")' class='glyphicon glyphicon-remove-circle'></a>
                    $('#dataTable').append(tableData);
                }
            }
        });
    }
//    function recordDelete(id) {
//        if (!confirm('Are you sure'))
//            return;
//        var URL = "
//        <//%=path%>/api/Utility/OrganizationMaster/" + id;
//        callApi(URL, "", "DELETE");
//    }
    getRecord();
</script>

<%@include file="../login/footer.jsp" %>

<%--
return "\n{\"id\": \""+id+"\",\"address\": \""+address+"\",\"email\": \""+email+"\",\"mayorContatNo\": \""+mayorContatNo+"\",\"mayorName\": \""+mayorName+"\",\"name\": \""+name+"\",\"officeContactNo\": \""+officeContactNo+"\",\"officeName\": \""+officeName+"\",\"phase1a\": \""+phase1a+"\",\"phase1b\": \""+phase1b+"\",\"phase1c\": \""+phase1c+"\",\"phase1d\": \""+phase1d+"\",\"phase2a\": \""+phase2a+"\",\"phase2b\": \""+phase2b+"\",\"phase2c\": \""+phase2c+"\",\"phase2d\": \""+phase2d+"\",\"phase3a\": \""+phase3a+"\",\"phase3b\": \""+phase3b+"\",\"phase3c\": \""+phase3c+"\",\"phase3d\": \""+phase3d+"\",\"secretaryContatNo\": \""+secretaryContatNo+"\",\"secretaryName\": \""+secretaryName+"\",\"subMayorContatNo\": \""+subMayorContatNo+"\",\"subMayorName\": \""+subMayorName+"\",\"url\": \""+url+"\"}";
--%>