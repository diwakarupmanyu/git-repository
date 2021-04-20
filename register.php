<?php

  require 'connection.php';
  $username=$_POST['username'];
  $email=$_POST['email'];
  $password=md5($_POST['password']);
  
  $checkuser = "select * from user where email = '$email'";
  $checkquery = mysqli_query($con,$checkuser);
  
  if(mysqli_num_rows($checkquery)>0)
  {
	  $response['error'] = '000';
	  $response['message']= ['user already exists'];;
  }
  else
  {
  $insertquery = "INSERT into user(username,email,password) VALUES('$username','$email','$password')";
  $result = mysqli_query($con,$insertquery);
  
  if($result)
  {
	  $response['error'] = '200';
	  $response['message']= ['register success'];
  }
  else
  {
	  $response['error'] = '400';
	  $response['message']= ['register failed'];
  }
  
  }
  
  echo json_encode ($response);

?>