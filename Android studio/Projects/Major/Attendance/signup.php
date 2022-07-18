<?php
    include('connections.php');

    $rollno=trim($_POST['roll_number']);
    $name=trim($_POST['name']);
    $class_id=trim($_POST['c_id']);

    $qry="select * from studenttable where roll_number='$rollno'";
    $raw=mysqli_query($conn,$qry);

    $count=mysqli_num_rows($raw);

    if($count>0){
        $response['message']='exits';
    }else{
        $qry2="INSERT INTO `studenttable` (`roll_number`, `name`, `c_id`) 
            VALUES ('$rollno', '$name', '$class_id')";
        $res=mysqli_query($conn,$qry2);
        if($res==true)
        $response['message']='inserted';
    
        else
        $response['message']='failed';
    }
        echo json_encode($response);
        mysqli_close($conn);

?>