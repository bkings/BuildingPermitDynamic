<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../login/header.jsp"%>
<fieldset>  <legend>UnitConversion</legend> 
    <form method='POST' id='dataFrom'> 
        <div class="row">
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
                <label>Id</label>
                <input type='text' name='id' id='id' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Qty</label>
                <input type='text' name='qty' id='qty' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Unit</label>
                <input type='text' name='unit' id='unit' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Conversion Unit</label>
                <input type='text' name='conversionUnit' id='conversionUnit' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Conversion Qty</label>
                <input type='text' name='conversionQty' id='conversionQty' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>

                <label id='ActionMSG'>&nbsp;</label><br>
                <input type='button' onclick='doSave()' id='Save' value='Save' class='btn btn-success'/>
                <input type='button' onclick='doUpdate()'  id='Update' value='Update' class='btn btn-success' style='display: none'/></div>
        </div>
    </form>
</fieldset>  <br> <fieldset>  <legend>UnitConversion Data</legend> 
    <div class='row' id='table'></div>
</fieldset>
<script>
    function edit(sn) {
        var id = ['id', 'qty', 'unit', 'conversionUnit', 'conversionQty'];
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
        var URL = "<%=path%>/api/Utility/UnitConversion";
        callApi(URL, requestData, "POST");
    }
    function doUpdate()
    {
        var dataForm = $('form').serializeArray();
        var requestData = {};
        $.each(dataForm, function (i, v) {
            requestData[v.name] = v.value;
        });
        var URL = "<%=path%>/api/Utility/UnitConversion/" + document.getElementById('id').value;
        callApi(URL, requestData, "PUT");
    }
    function getRecord()
    {
        var URL = "<%=path%>/api/Utility/UnitConversion";
        $.ajax({type: "GET", url: URL, headers: {'Authorization': '<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function (data) {
                if (data.length == 0) {
                    messages('Record Not Found');
                    document.getElementById('table').innerHTML = '';
                    return false;
                }
                document.getElementById('table').innerHTML = `<table class='table table-bordered table-hover table-striped' id='dataTable'><thead>
<tr><th hidden>Id</th><th>Qty</th><th>Unit</th><th>Conversion Unit</th><th>Conversion Qty</th><th>Action</th></tr></thead><tbody></tbody></table>`;
                var tableData;
                for (var i = 0; i < data.length; i++)
                {
                    tableData = "<tr><td hidden>" + data[i].id + "</td><td>" + data[i].qty + "</td><td>" + data[i].unit + "</td><td>" + data[i].conversionUnit + "</td><td>" + data[i].conversionQty + "</td><td><a onclick='edit(" + (i + 1) + ")' class='glyphicon glyphicon-edit'></a> | <a onclick='recordDelete(" + data[i].id + ")' class='glyphicon glyphicon-remove-circle'></a></td></tr>";
                    $('#dataTable').append(tableData);
                }
            }
        });
    }
    function recordDelete(id) {
        if (!confirm('Are you sure'))
            return;
        var URL = "<%=path%>/api/Utility/UnitConversion/" + id;
        callApi(URL, "", "DELETE");
    }
    getRecord();
</script>

<%@include file="../login/footer.jsp" %>

<%--
return "\n{\"id\": \""+id+"\",\"qty\": \""+qty+"\",\"unit\": \""+unit+"\",\"conversionUnit\": \""+conversionUnit+"\",\"conversionQty\": \""+conversionQty+"\"}";
--%>