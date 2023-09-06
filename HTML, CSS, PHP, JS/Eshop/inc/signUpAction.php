<?php

require_once ('functions.php');
require_once ('dbHandler.php');

$db = connectDB();

if(isset($_POST['userFullName']) && isset($_POST['userEmail']) && isset($_POST['userPassword']) && isset($_POST['userType'])){
    $uName = $_POST['userFullName'];
    $uMail = $_POST['userEmail'];
    $uPass = $_POST['userPassword'];
    $uType = $_POST['userType'];

    $stm = $db->prepare("INSERT INTO users (UserFullName, UserEmail, UserPassword, UserType) VALUES (:uName, :uMail, md5( :uPass ), :uType)");
    $params = ['uName' => $uName,'uMail' => $uMail, 'uPass' => $uPass, 'uType' => $uType];
    
    try{
        $stm->execute($params);
        header("Location: ../login.php?regSuccess=true");
    }catch(PDOException $ex){
        echo $ex->getMessage();
    }

    die();
}