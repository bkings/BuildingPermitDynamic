<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../login/header.jsp"%>
        <fieldset>  <legend>RajashowMaster</legend> 
<form method='POST' id='dataFrom'> 
<div class="row">
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Ward No</label>
<input type='text' name='wardNo' id='wardNo' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Floor</label>
<input type='text' name='floor' id='floor' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Floor Type</label>
<input type='text' name='floorType' id='floorType' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Area</label>
<input type='text' name='area' id='area' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Dharouti Rate</label>
<input type='text' name='dharoutiRate' id='dharoutiRate' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Dastur Rate</label>
<input type='text' name='dasturRate' id='dasturRate' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
  
  <label id='ActionMSG'>&nbsp;</label><br>
<input type='button' onclick='doSave()' id='Save' value='Save' class='btn btn-success'/>
<input type='button' onclick='doUpdate()'  id='Update' value='Update' class='btn btn-success' style='display: none'/></div>
</div>
</form>
</fieldset>  <br> <fieldset>  <legend>RajashowMaster Data</legend> 
 <div class='row' id='table'></div>
</fieldset>
<script>
function edit(sn){
var id = ['wardNo','floor','floorType','area','dharoutiRate','dasturRate'];
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
var URL="<%=path%>/api/Utility/RajashowMaster";
callApi(URL,requestData,"POST");
}
 function doUpdate()
{
  var dataForm = $('form').serializeArray(); var requestData = {}; $.each(dataForm, function(i, v) {requestData[v.name] = v.value;});
 var URL="<%=path%>/api/Utility/RajashowMaster/"+document.getElementById('wardNo').value;
callApi(URL,requestData,"PUT");
}
function getRecord()
    {
    var URL="<%=path%>/api/Utility/RajashowMaster";
    $.ajax({type: "GET",url: URL,headers: {'Authorization':'<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function(data){
  if(data.length==0){messages('Record Not Found');document.getElementById('table').innerHTML='';return false;}
document.getElementById('table').innerHTML=`<table class='table table-bordered table-hover table-striped' id='dataTable'><thead>
<tr><th>Ward No</th><th>Floor</th><th>Floor Type</th><th>Area</th><th>Dharouti Rate</th><th>Dastur Rate</th><th>Action</th></tr></thead><tbody></tbody></table>`;
var tableData;
for(var i=0;i<data.length;i++)
{
tableData="<tr><td>"+data[i].wardNo+"</td><td>"+data[i].floor+"</td><td>"+data[i].floorType+"</td><td>"+data[i].area+"</td><td>"+data[i].dharoutiRate+"</td><td>"+data[i].dasturRate+"</td><td><a onclick='edit("+(i+1)+")' class='glyphicon glyphicon-edit'></a> | <a onclick='recordDelete("+data[i].wardNo+")' class='glyphicon glyphicon-remove-circle'></a></td></tr>";
$('#dataTable').append(tableData);
}
}
});
}
function recordDelete(id){
if(!confirm('Are you sure'))return;
var URL="<%=path%>/api/Utility/RajashowMaster/"+id;
callApi(URL,"","DELETE");
}
 getRecord();
</script>

<%@include file="../login/footer.jsp" %>

<%--
return "\n{\"wardNo\": \""+wardNo+"\",\"floor\": \""+floor+"\",\"floorType\": \""+floorType+"\",\"area\": \""+area+"\",\"dharoutiRate\": \""+dharoutiRate+"\",\"dasturRate\": \""+dasturRate+"\"}";
--%>