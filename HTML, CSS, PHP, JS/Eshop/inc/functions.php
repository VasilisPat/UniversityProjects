<?php

session_start();

if(isset($_POST['action'])){
    switch ($_POST['action']){
        case 'checkLoggedIn': echo json_encode(checkLoggedIn()); break;
        case 'getSessionUserDetails': echo json_encode(getSessionUserDetails()); break;
        case 'logout': logout(); break;
        case 'guestLogin': userSession(array('UserID' => session_id(), 'UserFullName' => 'Guest', 'UserEmail' => '-', 'UserType' => '-'));
    }
}

function checkLoggedIn():bool {
    if(isset($_SESSION['userID']) && isset($_SESSION['userFullName']) && isset($_SESSION['userEmail']) && isset($_SESSION['userType'])){
        if($_SESSION['userFullName']=='Guest' && $_SESSION['userEmail']=='-' && $_SESSION['userType']=='-'){
            return  false;
        }else{
            return true;
        }
    }else{
        return false;
    }
}

function userSession(array $uSess) {
    $_SESSION['userID'] = $uSess['UserID'];
    $_SESSION['userFullName'] = $uSess['UserFullName'];
    $_SESSION['userEmail'] = $uSess['UserEmail'];
    $_SESSION['userType'] = $uSess['UserType'];
}

function getSessionUserDetails():array{
    return array($_SESSION['userFullName'], $_SESSION['userEmail'], $_SESSION['userType']);
}

function logout(){
    unset($_SESSION['userID']);
    unset($_SESSION['userFullName']);
    unset($_SESSION['userEmail']);
    unset($_SESSION['userType']);
}