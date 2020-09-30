<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../login/header.jsp"%>
        <fieldset>  <legend>Organization User</legend> 
<form method='POST' id='dataFrom'> 
<div class="row">
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Login Id</label>
<input type='text' name='loginId' id='loginId' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Address</label>
<input type='text' name='address' id='address' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Db Password</label>
<input type='text' name='dbPassword' id='dbPassword' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Education Qualification</label>
<input type='text' name='educationQualification' id='educationQualification' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Email</label>
<input type='text' name='email' id='email' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Id</label>
<input type='text' name='id' id='id' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Join Date</label>
<input type='text' name='joinDate' id='joinDate' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>License No</label>
<input type='text' name='licenseNo' id='licenseNo' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Mobile</label>
<input type='text' name='mobile' id='mobile' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Municipal Reg No</label>
<input type='text' name='municipalRegNo' id='municipalRegNo' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Photo</label>
<input type='text' name='photo' id='photo' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Signature</label>
<input type='text' name='signature' id='signature' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Stamp</label>
<input type='text' name='stamp' id='stamp' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>Status</label>
<select name='status' id='status' class='form-control'>
    <option value="Y">Yes</option>
    <option value="N">No</option>
   
</select>

</div>
    <div class='col-lg-2 col-sm-2 col-md-2 col-xs-6' hidden>
<label>Token</label>
<input type='text' name='token' id='token' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>User Name</label>
<input type='text' name='userName' id='userName' class='form-control'/>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
<label>User Type</label>
<select name='userType' id='userType' class='form-control'>
    <option value="C">Chief</option>
    <option value="A">Engineer</option>
    <option value="B">Sub Engineer</option>
    <option value="D">Designer</option>
    <option value="AD">Amin</option>
    <option value="R">Rajasow</option>
</select>
</div>
<div class='col-lg-2 col-sm-2 col-md-2 col-xs-6'>
  
  <label id='ActionMSG'>&nbsp;</label><br>
<input type='button' onclick='doSave()' id='Save' value='Save' class='btn btn-success'/>
<input type='button' onclick='doUpdate()'  id='Update' value='Update' class='btn btn-success' style='display: none'/></div>
</div>
</form>
</fieldset>  <br> <fieldset>  <legend>Organization User Data</legend> 
 <div class='row' id='table'></div>
</fieldset>
<script>
function edit(sn){
var id = ['id','loginId','address','dbPassword','educationQualification','email','joinDate','licenseNo','mobile','municipalRegNo','photo','signature','stamp','status','token','userName','userType'];
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
var URL="<%=path%>/api/Utility/OrganizationUser";
callApi(URL,requestData,"POST");
}
 function doUpdate()
{
  var dataForm = $('form').serializeArray(); var requestData = {}; $.each(dataForm, function(i, v) {requestData[v.name] = v.value;});
 var URL="<%=path%>/api/Utility/OrganizationUser/"+document.getElementById('id').value;
callApi(URL,requestData,"PUT");
}
function getRecord()
    {
    var URL="<%=path%>/api/Utility/OrganizationUser";
    $.ajax({type: "GET",url: URL,headers: {'Authorization':'<%=token%>'}, contentType: "application/json; charset=utf-8", dataType: "json", success: function(data){
  if(data.length==0){messages('Record Not Found');document.getElementById('table').innerHTML='';return false;}
document.getElementById('table').innerHTML=`<table class='table table-bordered table-hover table-striped' id='dataTable'><thead>
<tr><th>Login Id</th><th hidden>Address</th><th hidden>Db Password</th><th hidden>Education Qualification</th><th hidden>Email</th><th hidden>Id</th><th hidden>Join Date</th><th hidden>License No</th><th hidden>Mobile</th><th hidden>Municipal Reg No</th><th hidden>Photo</th><th hidden>Signature</th><th hidden>Stamp</th><th>Status</th><th hidden>Token</th><th>User Name</th><th>User Type</th><th>Action</th></tr></thead><tbody></tbody></table>`;
var tableData;
for(var i=0;i<data.length;i++)
{
tableData="<tr><td hidden>"+data[i].id+"</td><td>"+data[i].loginId+"</td><td hidden>"+data[i].address+"</td><td hidden>"+data[i].dbPassword+"</td><td hidden>"+data[i].educationQualification+"</td><td hidden>"+data[i].email+"</td><td hidden>"+data[i].joinDate+"</td><td hidden>"+data[i].licenseNo+"</td><td hidden>"+data[i].mobile+"</td><td hidden>"+data[i].municipalRegNo+"</td><td hidden>"+data[i].photo+"</td><td hidden>"+data[i].signature+"</td><td hidden>"+data[i].stamp+"</td><td>"+data[i].status+"</td><td hidden>"+data[i].token+"</td><td>"+data[i].userName+"</td><td>"+data[i].userType.id+"</td><td><a onclick='edit("+(i+1)+")' class='glyphicon glyphicon-edit'></a> | <a onclick='recordDelete(\""+data[i].loginId+"\")' class='glyphicon glyphicon-remove-circle'></a></td></tr>";
$('#dataTable').append(tableData);
}
}
});
}
function recordDelete(id){
if(!confirm('Are you sure'))return;
var URL="<%=path%>/api/Utility/OrganizationUser/"+id;
callApi(URL,"","DELETE");
}
 getRecord();
</script>

<%@include file="../login/footer.jsp" %>

<%--
return "\n{\"loginId\": \""+loginId+"\",\"address\": \""+address+"\",\"dbPassword\": \""+dbPassword+"\",\"educationQualification\": \""+educationQualification+"\",\"email\": \""+email+"\",\"id\": \""+id+"\",\"joinDate\": \""+joinDate+"\",\"licenseNo\": \""+licenseNo+"\",\"mobile\": \""+mobile+"\",\"municipalRegNo\": \""+municipalRegNo+"\",\"photo\": \""+photo+"\",\"signature\": \""+signature+"\",\"stamp\": \""+stamp+"\",\"status\": \""+status+"\",\"token\": \""+token+"\",\"userName\": \""+userName+"\",\"userType\": \""+userType+"\"}";
--%>