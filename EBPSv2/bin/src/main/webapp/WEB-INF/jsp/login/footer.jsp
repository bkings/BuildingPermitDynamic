</div>
<script>  function messages(msg) {
        $.toast({
            heading: "Messages",
            text: msg,
            icon: 'info',
            loader: true, // Change it to false to disable loader
            loaderBg: '#FFFFFF', // To change the background,
            position: 'mid-center'
        });
    }
    function errorMessages(msg) {
         $.toast({
                    heading: "Error",
                    text: msg,
                    icon: 'error',
                    loader: true, // Change it to false to disable loader
                    loaderBg: '#FFFFFF', // To change the background,
                    position: 'mid-center'
                });
 }
   </script>  
<footer id="footer" >
<div class="container">
<div class="row">
<div class="col-xs-12 col-sm-12 col-md-12">
<p>&nbsp;</p>
<p>Copy Right &copy; 2014-2018 </p>
<p><a href="<%= request.getContextPath()%>/Logout" class="btn btn-success"> Logout</a></p>
</div>
</div>
</div>
</footer> 
</body>
</html>