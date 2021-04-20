<?php

    require 'connection.php';
	
	$current = md5($_POST['current'];
	$new = md5($_POST['new'];
	$email = $_POST['email'];
	
	$checkuser = "select * from user where email = '$email' and password = '$current'";
	$result = mysqli_query($con,$checkuser);
	
	if($result>0)
	{
		$updatepass = mysqli_query($con,"update user set password = '$new' where email ='$email'");
		
		if($updatepass>0)
		{
			$response['error'] = '200';
			$response['message'] = 'password update success';
		}
		else
		{
			$response['error'] = '400';
			$response['message'] = 'password not updated';
		}
		
	}
	else
	{
		$response['error'] = '400';
		$response['message'] = 'user does not exist';
	}
	
	echo json_encode ($response);
	
	
?>