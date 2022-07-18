<?php 
    include('connections.php');
    $roll_number= $_POST['roll_number'];


    $qry="SELECT faculty_table.f_name, subject_table.sname from faculty_table inner join subject_table 
    on faculty_table.f_id=subject_table.f_id inner join class_subject on
     class_subject.s_id=subject_table.s_id inner join studenttable on studenttable.c_id=class_subject.c_id 
     where studenttable.roll_number='$roll_number'";

     $result=mysqli_query($conn, $qry);
     
    $row=mysqli_fetch_all($result, MYSQLI_ASSOC);

    echo json_encode($row);
    ?>