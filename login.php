<?php

  require 'connection.php';
  
  $email = $_POST['email'];
  $password = md5($_POST['password']);
  
  $checkuser = "select * from user where email = '$email'";
  $result = mysqli_query($con,$checkuser);
  
  if(mysqli_num_rows($result)>0)
  {
	  $checkuserquery = "select id,username,email from user where email ='$email' and password = '$password'";
	  $resultant = mysqli_query($con,$checkuserquery);
	  
	  if(mysqli_num_rows($resultant)>0)
	  {
		  while($row = $resultant -> fetch_assoc())
		  {
			  $response['user']= $row;
		  }
		  
		  $response['error'] = '200';
		  $response['message'] = 'login success';
	  }
	  else
	  {
		  $response['user'] = (object)[];
		  $response['error'] = '200';
		  $response['message'] = 'wrong credentials';
	  }
	  
  }
  else
  {
	  $response['user'] = (object)[];
	  $response['error'] = '400';
	  $response['message'] = 'user does not exists';
  }
  
  echo json_encode ($response);

?>