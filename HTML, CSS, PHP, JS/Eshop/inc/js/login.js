$(document).ready(function() {

    /*---Execute on Page Ready---*/

    $.ajax({
        type: 'POST',
        url: 'inc/functions.php',
        data: {'action': 'checkLoggedIn'},
        success: function(response) {
            if(response=="true"){
                window.location.replace("home.html");
            }
        }
    });

    $('.cancelButton').on("click", function(event){  
        event.preventDefault();
        window.location.replace("home.html");
     });
});