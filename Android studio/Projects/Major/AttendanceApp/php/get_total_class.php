<?php
    include('connections.php');

    $rollno= $_GET['roll_no'];
    $sub_id=$_GET["sub_id"];

    $qry="Select count(sub_id) as count FROM status_table where sub_id='$sub_id' and rol_number='$rollno'";
    $res=mysqli_query($conn, $qry);

    // $count=mysqli_fetch_assoc($res);
    $count=mysqli_fetch_all($res,MYSQLI_ASSOC);
    // $count=mysqli_num_rows($res);

    $qry1="Select count(sub_id) as count1 FROM status_table where sub_id='$sub_id' and rol_number='$rollno' and status
='1'    ";
    $res1=mysqli_query($conn, $qry1);
    // $count1=mysqli_num_rows($res1);
    $count1=mysqli_fetch_all($res1,MYSQLI_ASSOC);
        // $count1=mysqli_fetch_assoc($res1);


    // $response['count']=$count[0];
    // $response['count1']=$count1[0];

    echo json_encode(array_merge($count,$count1));
?>