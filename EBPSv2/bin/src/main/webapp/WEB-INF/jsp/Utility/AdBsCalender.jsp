<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../login/header.jsp"%>
        <fieldset>  <legend>AdBsCalender</legend> 
<form method='POST' id='dataFrom'> 
<div class="row">
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Ad Date</label>
<input type='text' name='adDate' id='adDate' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Bs Date</label>
<input type='text' name='bsDate' id='bsDate' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Day</label>
<input type='text' name='day' id='day' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Default Holiday</label>
<input type='text' name='defaultHoliday' id='defaultHoliday' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Festival Holiday</label>
<input type='text' name='festivalHoliday' id='festivalHoliday' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Remark</label>
<input type='text' name='remark' id='remark' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
  
  <label id='ActionMSG'>&nbsp;</label><br>
<input type='button' onclick='doSave()' id='Save' value='Save' class='btn btn-success'/>
<input type='button' onclick='doUpdate()'  id='Update' value='Update' class='btn btn-success' style='display: none'/></div>
</div>
</form>
</fieldset>  <br> <fieldset>  <legend>AdBsCalender Data</legend> 
 <div class='row' id='table'></div>
</fieldset>
<script>
function edit(sn){
var id = ['adDate','bsDate','day','defaultHoliday','festivalHoliday','remark'];
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
var URL="<%=path%>/api/Utility/AdBsCalender";
callApi(URL,requestData,"POST");
}
 function doUpdate()
{
  var dataForm = $('form').serializeArray(); var requestData = {}; $.each(dataForm, function(i, v) {requestData[v.name] = v.value;});
 var URL="<%=path%>/api/Utility/AdBsCalender/"+document.getElementById('adDate').value;
callApi(URL,requestData,"PUT");
}
function getRecord()
    {
    var URL="<%=path%>/api/Utility/AdBsCalender";
    $.ajax({type: "GET",url: URL,headers: {'Authorization':'<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function(data){
  if(data.length==0){messages('Record Not Found');document.getElementById('table').innerHTML='';return false;}
document.getElementById('table').innerHTML=`<table class='table table-bordered table-hover table-striped' id='dataTable'><thead>
<tr><th>Ad Date</th><th>Bs Date</th><th>Day</th><th>Default Holiday</th><th>Festival Holiday</th><th>Remark</th><th>Action</th></tr></thead><tbody></tbody></table>`;
var tableData;
for(var i=0;i<data.length;i++)
{
tableData="<tr><td>"+data[i].adDate+"</td><td>"+data[i].bsDate+"</td><td>"+data[i].day+"</td><td>"+data[i].defaultHoliday+"</td><td>"+data[i].festivalHoliday+"</td><td>"+data[i].remark+"</td><td><a onclick='edit("+(i+1)+")' class='glyphicon glyphicon-edit'></a> | <a onclick='recordDelete("+data[i].adDate+")' class='glyphicon glyphicon-remove-circle'></a></td></tr>";
$('#dataTable').append(tableData);
}
}
});
}
function recordDelete(id){
if(!confirm('Are you sure'))return;
var URL="<%=path%>/api/Utility/AdBsCalender/"+id;
callApi(URL,"","DELETE");
}
 getRecord();
</script>

<%@include file="../login/footer.jsp" %>

<%--
return "\n{\"adDate\": \""+adDate+"\",\"bsDate\": \""+bsDate+"\",\"day\": \""+day+"\",\"defaultHoliday\": \""+defaultHoliday+"\",\"festivalHoliday\": \""+festivalHoliday+"\",\"remark\": \""+remark+"\"}";
--%>