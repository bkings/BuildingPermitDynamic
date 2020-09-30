<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../login/header.jsp"%>
<fieldset>  <legend>Form Name</legend> 
    <form method='POST' id='dataFrom'> 
        <div class="row">
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden="">
                <label>Id</label>
                <input type='text' name='id' id='id' class='form-control'/>
            </div>
            <div class='col-lg-3 col-sm-3 col-md-3 col-xs-6'>
                <label>Name</label>
                <input type='text' name='name' id='name' class='form-control'/>
            </div>
            <div class='col-lg-1 col-sm-1 col-md-1 col-xs-6' hidden>
                <label>Position</label>
                <input type='text' name='position' id='position' value="0" class='form-control'/>
            </div>

            <div class='col-lg-1 col-sm-1 col-md-1 col-xs-6'>
                <label>Code</label>
                <input type='text' name='pageCode' id='pageCode' class='form-control' style="width: 80px;"/>
            </div>
            <div class='col-lg-4 col-sm-4 col-md-4 col-xs-6'>
                <label>view Url</label>
                <input type='text' name='viewUrl' id='viewUrl' class='form-control'/>
            </div>
            <div class='col-lg-4 col-sm-4 col-md-4 col-xs-6'>
                <label>Table Name</label>
                <input type='text' name='tableName' id='tableName' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Enter By</label>
                <select name='enterBy' id='enterBy' class='form-control'>
                    <option value="A">Engineer</option>
                    <option value="B">Sub Engineer</option>
                    <option value="C">Chief</option>
                    <option value="D">Designer</option>
                    <option value="R">Rajasow</option>
                    <option value="AD">Amin</option>
                    <option value="E">Post E</option>
                    <option value="F">Post F</option>
                    <option value="G">Post G</option>
                </select>
            </div>


            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Designer Approval</label>
                <select name='designerApproval' id='designerApproval' class='form-control'>
                    <option value="Y">Yes</option>
                    <option value="N">No</option>
                </select>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Sub Eng.</label>
                <select name='subEngrApproval' id='subEngrApproval' class='form-control' >
                    <option value="Y">Yes</option>
                    <option value="N">No</option>
                </select>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Engineer</label>
                <select name='engrApproval' id='engrApproval' class='form-control'  >
                    <option value="Y">Yes</option>
                    <option value="N">No</option>
                </select>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Amin</label>
                <select name='aminApproval' id='aminApproval' class='form-control' >
                    <option value="Y">Yes</option>
                    <option value="N">No</option>
                </select>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Rajasow</label>
                <select name='rajasowApproval' id='rajasowApproval' class='form-control' >
                    <option value="Y">Yes</option>
                    <option value="N">No</option>
                </select>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Chief</label>
                <select name='chiefApproval' id='chiefApproval' class='form-control' >
                    <option value="Y">Yes</option>
                    <option value="N">No</option>
                </select>
            </div> 
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Post E Approval</label>
                <select name='posteApproval' id='posteApproval' class='form-control' >
                    <option value="Y">Yes</option>
                    <option value="N">No</option>
                </select>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Post F Approval</label>
                <select name='postfApproval' id='postfApproval' class='form-control' >
                    <option value="Y">Yes</option>
                    <option value="N">No</option>
                </select>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Post G Approval</label>
                <select name='postgApproval' id='postgApproval' class='form-control' >
                    <option value="Y">Yes</option>
                    <option value="N">No</option>
                </select>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>&nbsp;</label><br>
                <input type='button' onclick='doSave()' id='Save' value='Save' class='btn btn-success'/>
                <input type='button' onclick='doUpdate()'  id='Update' value='Update' class='btn btn-success' style='display: none'/></div>
        </div>
    </form>
</fieldset> 
<br>
<fieldset> 
    <legend>Form Name Master Data</legend> 
    <div class='row' id='table'></div>
</fieldset>
<script>
    function edit(sn) {
        var id = ['id', 'name', 'pageCode', 'viewUrl', 'enterBy', 'designerApproval', 'subEngrApproval', 'engrApproval', 'aminApproval', 'rajasowApproval', 'chiefApproval', 'tableName', 'posteApproval', 'postfApproval', 'postgApproval'];
        var data = document.getElementById(sn);
        for (var i = 0; i < id.length; i++)
            document.getElementById(id[i]).value = data.children[i].innerHTML;
        $('#Update').show();
        $('#Save').hide();
        document.getElementById('Update').focus();
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


    function doSave()
    {
        var dataForm = $('form').serializeArray();
        var requestData = {};
        $.each(dataForm, function (i, v) {
            requestData[v.name] = v.value;
        });
        var URL = "<%=path%>/api/Utility/FormNameMaster";
        callApi(URL, requestData, "POST");
    }
    function doUpdate()
    {
        var dataForm = $('form').serializeArray();
        var requestData = {};
        $.each(dataForm, function (i, v) {
            requestData[v.name] = v.value;
        });
        var URL = "<%=path%>/api/Utility/FormNameMaster/" + document.getElementById('id').value;
        callApi(URL, requestData, "PUT");
    }
    function getRecord()
    {
        var URL = "<%=path%>/api/Utility/FormNameMaster";
        $.ajax({type: "GET", url: URL, headers: {'Authorization': '<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function (data) {
                if (data.length == 0) {
                    messages('Record Not Found');
                    document.getElementById('table').innerHTML = '';
                    return false;
                }
                document.getElementById('table').innerHTML = `<table class='table table-bordered table-hover table-striped' id='dataTable'><thead>
<tr><th hidden></th><th hidden></th><th hidden></th><th hidden></th><th hidden></th><th>Id</th><th>Name</th><th>Page Code</th><th>View URL</th><th>Enter By</th><th>Table Name</th><th hidden></th><th hidden></th><th hidden></th><th hidden></th><th>Action</th></tr></thead><tbody></tbody></table>`;
                var tableData;
                for (var i = 0; i < data.length; i++)
                {
                    tableData = "<tr id='" + i + "'><td>" + data[i].id + "</td><td>" + data[i].name + "</td><td>" + data[i].pageCode + "</td>\n\
\n\ <td>" + data[i].viewUrl + "</td><td>" + data[i].enterBy + "</td>\n\
\n\ <td hidden>" + data[i].designerApproval + "</td>\n\
\n\ <td hidden>" + data[i].subEngrApproval + "</td>\n\
\n\ <td hidden>" + data[i].engrApproval + "</td>\n\
\n\ <td hidden>" + data[i].aminApproval + "</td>\n\
\n\ <td hidden>" + data[i].rajasowApproval + "</td>\n\
\n\\n\ <td hidden>" + data[i].chiefApproval + "</td>\n\
\n\\n\\n\ <td>" + data[i].tableName + "</td>\n\
\n\\n\\n\ <td hidden>" + data[i].posteApproval + "</td>\n\
\n\\n\\n\ <td hidden>" + data[i].postfApproval + "</td>\n\
\n\\n\\n\ <td hidden>" + data[i].postgApproval + "</td>\n\
<td><a onclick='edit(" + (i) + ")' class='glyphicon glyphicon-edit'></a> | <a onclick='recordDelete(" + data[i].id + ")' class='glyphicon glyphicon-remove-circle'></a></td></tr>";
                    $('#dataTable').append(tableData);
                }
                $('#dataTable').DataTable();
            }
        });
    }
    function recordDelete(id) {
        if (!confirm('Are you sure'))
            return;
        var URL = "<%=path%>/api/Utility/FormNameMaster/" + id;
        callApi(URL, "", "DELETE");
    }
    getRecord();
</script>

<%@include file="../login/footer.jsp" %>

<%--
return "\n{\"id\": \""+id+"\",\"name\": \""+name+"\",\"position\": \""+position+"\"}";
--%>