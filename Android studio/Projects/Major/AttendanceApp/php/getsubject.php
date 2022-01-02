<?php
    include('connections.php');

      $rollno=trim($_POST['roll_number']);

      $qry="select subject_table.sname, subject_table.s_id from subject_table inner join class_subject on 
      class_subject.s_id=subject_table.s_id inner join studenttable on studenttable.c_id=class_subject.c_id
       where studenttable.roll_number='$rollno'";
      
      $row1=mysqli_query($conn, $qry);
      $res=mysqli_fetch_all($row1, MYSQLI_ASSOC);
    echo json_encode($res);
    
     
?> 
