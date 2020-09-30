<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../login/header.jsp"%>
        <fieldset>  <legend>EmailSendingPanding</legend> 
<form method='POST' id='dataFrom'> 
<div class="row">
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Email</label>
<input type='text' name='email' id='email' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Date Time</label>
<input type='text' name='dateTime' id='dateTime' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Subject</label>
<input type='text' name='subject' id='subject' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Body</label>
<input type='text' name='body' id='body' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Status</label>
<input type='text' name='status' id='status' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
  
  <label id='ActionMSG'>&nbsp;</label><br>
<input type='button' onclick='doSave()' id='Save' value='Save' class='btn btn-success'/>
<input type='button' onclick='doUpdate()'  id='Update' value='Update' class='btn btn-success' style='display: none'/></div>
</div>
</form>
</fieldset>  <br> <fieldset>  <legend>EmailSendingPanding Data</legend> 
 <div class='row' id='table'></div>
</fieldset>
<script>
function edit(sn){
var id = ['email','dateTime','subject','body','status'];
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
var URL="<%=path%>/api/Utility/EmailSendingPanding";
callApi(URL,requestData,"POST");
}
 function doUpdate()
{
  var dataForm = $('form').serializeArray(); var requestData = {}; $.each(dataForm, function(i, v) {requestData[v.name] = v.value;});
 var URL="<%=path%>/api/Utility/EmailSendingPanding/"+document.getElementById('email').value;
callApi(URL,requestData,"PUT");
}
function getRecord()
    {
    var URL="<%=path%>/api/Utility/EmailSendingPanding";
    $.ajax({type: "GET",url: URL,headers: {'Authorization':'<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function(data){
  if(data.length==0){messages('Record Not Found');document.getElementById('table').innerHTML='';return false;}
document.getElementById('table').innerHTML=`<table class='table table-bordered table-hover table-striped' id='dataTable'><thead>
<tr><th>Email</th><th>Date Time</th><th>Subject</th><th>Body</th><th>Status</th><th>Action</th></tr></thead><tbody></tbody></table>`;
var tableData;
for(var i=0;i<data.length;i++)
{
tableData="<tr><td>"+data[i].email+"</td><td>"+data[i].dateTime+"</td><td>"+data[i].subject+"</td><td>"+data[i].body+"</td><td>"+data[i].status+"</td><td><a onclick='edit("+(i+1)+")' class='glyphicon glyphicon-edit'></a> | <a onclick='recordDelete("+data[i].email+")' class='glyphicon glyphicon-remove-circle'></a></td></tr>";
$('#dataTable').append(tableData);
}
}
});
}
function recordDelete(id){
if(!confirm('Are you sure'))return;
var URL="<%=path%>/api/Utility/EmailSendingPanding/"+id;
callApi(URL,"","DELETE");
}
 getRecord();
</script>

<%@include file="../login/footer.jsp" %>

<%--
return "\n{\"email\": \""+email+"\",\"dateTime\": \""+dateTime+"\",\"subject\": \""+subject+"\",\"body\": \""+body+"\",\"status\": \""+status+"\"}";
--%>