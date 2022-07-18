<?php
    include("../connections.php");

    $name=$_POST['f_name'];
    $password=$_POST['pass'];

    $query="SELECT f_id FROM faculty_table where f_name='$name' AND password='$password'";

    $res = mysqli_query($conn, $query);

    $getid=mysqli_fetch_all($res, MYSQLI_ASSOC);
    $count= mysqli_num_rows($res);

    if($count==0){
        $response['message']= "Wrong id or password";
    }else{
       $response['message']=json_decode($getid[0]["f_id"]);
    }
    
    echo json_encode($response);

?>