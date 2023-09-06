<?php

require_once ('functions.php');
require_once ('dbHandler.php');

$db = connectDB();

if(isset($_POST['userEmail']) && isset($_POST['userPassword'])){
    $uMail = $_POST['userEmail'];
    $uPass = $_POST['userPassword'];
    
    $stm = $db->prepare("SELECT UserID, UserFullName, UserEmail, UserType FROM users WHERE UserEmail = :uMail AND ( UserPassword = md5 ( :uPass ) OR UserPassword = :uPass)");
    $params = ['uMail' => $uMail, 'uPass' => $uPass];
    
    try{
        $stm->execute($params);
        $results = $stm->fetchAll(\PDO::FETCH_ASSOC);

        if(empty($results)){
            header("Location: ../login.php?authenticated=false");
        }else{
            $uSess = $results[0];
            userSession($uSess);
            header('Location: ../home.html');
        }
        
    }catch(PDOException $ex){
        echo $ex->getMessage();
    }

    die();
}