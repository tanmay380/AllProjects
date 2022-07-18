<?php 
    include("connections.php");

    date_default_timezone_set("Asia/Kolkata");
    $subid= $_GET['s_id'];
    $rollnumber=$_GET['roll_number'];
    $date= date("Y-m-d H:i:59");
    $min= date("i");
    $date1hr = date('Y-m-d H:i:w',strtotime('-'.$min.' minute ',strtotime($date)));


    $select1= "Select rol_number, sub_id, class_date from status_table 
        where (class_date BETWEEN '$date1hr' and '$date') and rol_number='$rollnumber'";

    $exist= mysqli_query($conn, $select1);
    $count=mysqli_num_rows($exist);

    

    if($count==0)
{
    $query="INSERT INTO `status_table` (`id`, `rol_number`, `sub_id`, `class_date`, `status`) 
            VALUES (NULL, $rollnumber,$subid , now(), '1')";
    
    $res=mysqli_query($conn, $query);

    if($res==true){
        $response['messge']="marked";
    }else
        $response['messge']="failed";
}else
    $response['messge']="exists";

    echo json_encode($response);


    ?>

    