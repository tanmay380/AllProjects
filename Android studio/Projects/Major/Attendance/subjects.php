<?php 
    include('connections.php');
    $classid=$_GET['c_id'];

    $query="SELECT s_id,sname from subject_table where subject_table.s_id 
        in( SELECT class_subject.s_id from class_subject where class_subject.c_id=$classid)";

    $res=mysqli_query($conn,$query);
    $res = mysqli_fetch_all($res,MYSQLI_ASSOC);

    echo json_encode($res);



?>