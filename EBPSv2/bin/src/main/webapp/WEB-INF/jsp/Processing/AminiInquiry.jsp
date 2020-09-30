<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../login/header.jsp"%>
        <fieldset>  <legend>AminiInquiry</legend> 
<form method='POST' id='dataFrom'> 
<div class="row">
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Application No</label>
<input type='text' name='applicationNo' id='applicationNo' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Json Data</label>
<input type='text' name='jsonData' id='jsonData' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Enter By</label>
<input type='text' name='enterBy' id='enterBy' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Enter Date</label>
<input type='text' name='enterDate' id='enterDate' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Amini Name</label>
<input type='text' name='aminiName' id='aminiName' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Amini Data</label>
<input type='text' name='aminiData' id='aminiData' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Ser Date</label>
<input type='text' name='serDate' id='serDate' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Ser Name</label>
<input type='text' name='serName' id='serName' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Er Name</label>
<input type='text' name='erName' id='erName' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Er Date</label>
<input type='text' name='erDate' id='erDate' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
  
  <label id='ActionMSG'>&nbsp;</label><br>
<input type='button' onclick='doSave()' id='Save' value='Save' class='btn btn-success'/>
<input type='button' onclick='doUpdate()'  id='Update' value='Update' class='btn btn-success' style='display: none'/></div>
</div>
</form>
</fieldset>  <br> <fieldset>  <legend>AminiInquiry Data</legend> 
 <div class='row' id='table'></div>
</fieldset>
<script>
function edit(sn){
var id = ['applicationNo','jsonData','enterBy','enterDate','aminiName','aminiData','serDate','serName','erName','erDate'];
for( var i=0;i<id.length;i++)
document.getElementById(id[i]).value=document.getElementById("dataTable").rows[sn].cells.item(i).innerHTML;
  $('#Update').show();$('#Save').hide();
document.getElementById('Update').focus();
}</script>
<script>
 function callApi(URL,requestData,apiMethod)
 {
$('.btn').button('loading');
var msg=''; $.ajax({ type: apiMethod, url: URL, headers: {'Authorization':'<%=token%>'}, data: JSON.stringify(requestData), contentType: "application/json; charset=utf-8",dataType: "json",
 success: function(data) {
$('.btn').button('reset');
 if(!data['error']){ $('#Update').hide();$('#Save').show();msg=data['message'];
document.getElementById('dataFrom').reset();
getRecord();
}else{
msg=data['error'].message;
}messages(msg);},
    error: function(XMLHttpRequest, textStatus, errorThrown) {
$('.btn').button('reset'); 
    messages(XMLHttpRequest+" Status: " + textStatus+" Error: " + errorThrown);
    }    });
   }
</script>
<script>
 function doSave()
{
 var dataForm = $('form').serializeArray(); var requestData = {}; $.each(dataForm, function(i, v) {requestData[v.name] = v.value;});
var URL="<%=path%>/api/Processing/AminiInquiry";
callApi(URL,requestData,"POST");
}
 function doUpdate()
{
  var dataForm = $('form').serializeArray(); var requestData = {}; $.each(dataForm, function(i, v) {requestData[v.name] = v.value;});
 var URL="<%=path%>/api/Processing/AminiInquiry/"+document.getElementById('applicationNo').value;
callApi(URL,requestData,"PUT");
}
function getRecord()
    {
    var URL="<%=path%>/api/Processing/AminiInquiry";
    $.ajax({type: "GET",url: URL,headers: {'Authorization':'<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function(data){
  if(data.length==0){messages('Record Not Found');document.getElementById('table').innerHTML='';return false;}
document.getElementById('table').innerHTML=`<table class='table table-bordered table-hover table-striped' id='dataTable'><thead>
<tr><th>Application No</th><th>Json Data</th><th>Enter By</th><th>Enter Date</th><th>Amini Name</th><th>Amini Data</th><th>Ser Date</th><th>Ser Name</th><th>Er Name</th><th>Er Date</th><th>Action</th></tr></thead><tbody></tbody></table>`;
var tableData;
for(var i=0;i<data.length;i++)
{
tableData="<tr><td>"+data[i].applicationNo+"</td><td>"+data[i].jsonData+"</td><td>"+data[i].enterBy+"</td><td>"+data[i].enterDate+"</td><td>"+data[i].aminiName+"</td><td>"+data[i].aminiData+"</td><td>"+data[i].serDate+"</td><td>"+data[i].serName+"</td><td>"+data[i].erName+"</td><td>"+data[i].erDate+"</td><td><a onclick='edit("+(i+1)+")' class='glyphicon glyphicon-edit'></a> | <a onclick='recordDelete("+data[i].applicationNo+")' class='glyphicon glyphicon-remove-circle'></a></td></tr>";
$('#dataTable').append(tableData);
}
}
});
}
function recordDelete(id){
if(!confirm('Are you sure'))return;
var URL="<%=path%>/api/Processing/AminiInquiry/"+id;
callApi(URL,"","DELETE");
}
 getRecord();
</script>

<%@include file="../login/footer.jsp" %>

<%--
return "\n{\"applicationNo\": \""+applicationNo+"\",\"jsonData\": \""+jsonData+"\",\"enterBy\": \""+enterBy+"\",\"enterDate\": \""+enterDate+"\",\"aminiName\": \""+aminiName+"\",\"aminiData\": \""+aminiData+"\",\"serDate\": \""+serDate+"\",\"serName\": \""+serName+"\",\"erName\": \""+erName+"\",\"erDate\": \""+erDate+"\"}";
--%>