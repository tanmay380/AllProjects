<?php
    include("../connections.php");

    $f_id= $_GET['f_id'];

    $query= "SELECT  s_id, sname from subject_table where f_id=$f_id";

    $res= mysqli_query($conn, $query);

    $result  =  mysqli_fetch_all($res,MYSQLI_ASSOC);

    echo json_encode($result);
    ?>