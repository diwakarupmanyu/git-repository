<?php

   require 'connection.php';
   
   $id = $_REQUEST['id'];
   $username = $_POST['username'];
   $email = $_POST['email'];
   
   $updatequery = "udpate user set username = '$username', email = '$email' where id = '$id'";
   $result = mysqli_query($con,$updatequery);
   
   if($result)
   {
	   $response['error'] = '200';
	   $response['message'] = 'user update success';
   }
   else
   {
	  $response['error'] = '400';
	  $response['message']= ['user update failed'];
   }

   echo json_encode ($response);   

?>