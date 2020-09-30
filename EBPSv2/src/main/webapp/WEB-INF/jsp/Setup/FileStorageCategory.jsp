<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../login/header.jsp"%>
<fieldset>  <legend>FileStorageCategory</legend> 
    <form method='POST' id='dataFrom'> 
        <div class="row">
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Id</label>
                <input type='text' name='id' id='id' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Name</label>
                <input type='text' name='name' id='name' class='form-control'/>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Is Multiple</label>
                <select name='isMultiple' id='isMultiple' class='form-control'>
                    <option value="Y">Yes</option>
                    <option value="N">No</option>
                </select>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
                <label>Form Name</label>
                <select name='formName' id='formName' class='form-control'>
                    <option value='1'>निवेदन</option>"
                    <option value='2'>अनुसुची क</option>"
                    <option value='3'>B - अनुसुची ख अर्किटेक्चरल</option>"
                    <option value='4'>B - अनुसुची ख स्ट्रक्चरल</option>"
                    <option value='5'>C - अनुसुची ख  अर्किटेक्चरल</option>"
                    <option value='6'>C - अनुसुची ख स्ट्रक्चरल</option>"
                    <option value='8'>इलेक्ट्रिकल डिजाइन</option>"
                    <option value='7'>स्यानिटरी डिजाइन</option>"
                    <option value='9'>अनुसुची ग</option>"
                    <option value='10'>अनुसुची घ</option>"
                    <option value='39'>नक्सा बनाउने प्रविधिकले गर्नु पर्ने विवरण</option>"
                    <option value='42'>माथिको तह मा स्वीकृत को लागि पठाउनु </option>"
                    <option value='43'>अमिनी सजमिना </option>"
                    <option value='44'>ghar compound wall</option>"
                    <option value='45'>AminiKabuliyatnama</option>"
                    <option value='11'>नक्सा जाँच गरी प्रतिवेदन</option>"
                    <option value='12'>राजस्व विवरण</option>"
                    <option value='13'>संधियारको नाममा जारी भएको सुचना</option>"
                    <option value='14'>सुचना बुझाएको भर्पाई टास तथा मुचुलका</option>"
                    <option value='15'>सर्जमिन गरी पेश गर्ने सम्बन्धमा</option>"
                    <option value='16'>घर नक्सा सरजमिन मुचुल्का</option>"
                    <option value='17'>प्रविधिक प्रतिवेदन</option>"
                    <option value='18'>संसोधन गरी निवेदन</option>"
                    <option value='19'>संसोधन टिप्पणी र आदेश</option>"
                    <option value='20'>प्लिन्थ लेभलसम्म निर्माण निमित्त इजाजत प्रधान गर्ने</option>"
                    <option value='21'>प्रथम चरणको अस्थायी निर्माण इजाजत पत्र</option>"
                    <option value='22'>सुपरस्ट्रक्चरको निर्माण कार्यको लागि इजाजतबारे</option>"
                    <option value='23'>प्लिन्थ लेभलसम्मको निर्माण कार्य सम्पन्नबारे घरधनीको तर्फबाट राखिएको सुपरिवेक्षकको प्रतिवेदन</option>"
                    <option value='24'>संसोधन विवरण (पहिलो चरण)</option>"
                    <option value='40'>प्लिन्थ लेभल सम्म निर्माण सम्पन्‍न बारे न.पा. प्राविधिकको राय</option>"
                    <option value='25'>संसोधनको टिप्पणी आदेश</option>"
                    <option value='26'>सुपरस्ट्रक्चर निर्माणका निमित्त इजाजत प्रदान गर्ने टिप्पणी आदेश</option>"
                    <option value='27'>दोस्रो चरणको इजाजत पत्र</option>"
                    <option value='28'>निर्माण कार्य सम्पन्‍न प्रमाण पत्र बारे</option>"
                    <option value='29'>सुपरस्ट्रक्चरको निर्माण कार्य सम्पन्‍नबारे घरधनीको तर्फबाट राखिएको सुपरिवेक्षकका प्रतिवेदन</option>"
                    <option value='30'>सुपरस्ट्रक्चरको संसोधन विवरण</option>"
                    <option value='31'>संसोधन/सुपरस्ट्रक्चर इजाजत प्रतिवेदन पेश गरेको बारे</option>"
                    <option value='33'>संसोधन सुपरस्ट्रक्चर टिप्पणी आदेश</option>"
                    <option value='32'>संसोधन सुपरस्ट्रक्चर इजाजत सम्बन्धमा</option>"
                    <option value='41'>भवन निर्माण सम्पन्‍न प्रतिवेदन सम्बन्धमा</option>"
                    <option value='34'>निर्माण कार्य सम्पन्न प्रमाण-पत्र दिने टिप्पणी आदेश</option>"
                    <option value='35'>भवन निर्माण सम्पन्‍न प्रमाण पत्र</option>"
                    <option value='36'>घर नक्सा नामसारी सम्बन्धमा</option>"
                    <option value='37'>नामसारी टिप्पणी आदेश</option>"
                    <option value='38'>नामसारी प्रमाण-पत्र</option>"

                </select>
            </div>
            <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>

                <label id='ActionMSG'>&nbsp;</label><br>
                <input type='button' onclick='doSave()' id='Save' value='Save' class='btn btn-success'/>
                <input type='button' onclick='doUpdate()'  id='Update' value='Update' class='btn btn-success' style='display: none'/></div>
        </div>
    </form>
</fieldset>  <br> <fieldset>  <legend>FileStorageCategory Data</legend> 
    <div class='row' id='table'></div>
</fieldset>
<script>
    function edit(sn) {
        var id = ['id', 'name', 'isMultiple', 'formName'];
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
        var URL = "<%=path%>/api/Utility/FileStorageCategory";
        callApi(URL, requestData, "POST");
    }
    function doUpdate()
    {
        var dataForm = $('form').serializeArray();
        var requestData = {};
        $.each(dataForm, function (i, v) {
            requestData[v.name] = v.value;
        });
        var URL = "<%=path%>/api/Utility/FileStorageCategory/" + document.getElementById('id').value;
        callApi(URL, requestData, "PUT");
    }
    function getRecord()
    {
        var URL = "<%=path%>/api/Utility/FileStorageCategory";
        $.ajax({type: "GET", url: URL, headers: {'Authorization': '<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function (data) {
                if (data.length == 0) {
                    messages('Record Not Found');
                    document.getElementById('table').innerHTML = '';
                    return false;
                }
                document.getElementById('table').innerHTML = `<table class='table table-bordered table-hover table-striped' id='dataTable'><thead>
<tr><th>Id</th><th>Name</th><th>Is Multiple</th><th>Form Name</th><th>Action</th></tr></thead><tbody></tbody></table>`;
                var tableData;
                for (var i = 0; i < data.length; i++)
                {
                    tableData = "<tr><td>" + data[i].id + "</td><td>" + data[i].name + "</td><td>" + data[i].isMultiple + "</td><td hidden>" + data[i].formName.id + "</td><td>" + data[i].formName.name + "</td><td><a onclick='edit(" + (i + 1) + ")' class='glyphicon glyphicon-edit'></a> | <a onclick='recordDelete(" + data[i].id + ")' class='glyphicon glyphicon-remove-circle'></a></td></tr>";
                    $('#dataTable').append(tableData);
                }
            }
        });
    }
    function recordDelete(id) {
        if (!confirm('Are you sure'))
            return;
        var URL = "<%=path%>/api/Utility/FileStorageCategory/" + id;
        callApi(URL, "", "DELETE");
    }
    getRecord();
</script>

<%@include file="../login/footer.jsp" %>

<%--
return "\n{\"id\": \""+id+"\",\"name\": \""+name+"\",\"isMultiple\": \""+isMultiple+"\"}";
--%>