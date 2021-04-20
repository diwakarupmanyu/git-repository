<?php

   require 'connection.php';
   
   $id = $_POST['id'];
   
   $checkid = "select id from user";
   $deleteuser = mysqli_query($con,"delete from user where id ='$id'");
   
   
   
   if($deleteuser>0)
   {
	   $response['error'] = '200';
	   $response['message'] = 'account has been deleted';
   }
   else
   {
	   $response['error'] = '400';
	   $response['message'] = 'account does not exist';
   }
   
   echo json_encode ($response);
 
?>