<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Log In | E-Shop</title>
        <link rel="stylesheet" href="style.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="assets/jquery-3.7.0.min.js"></script>
        <script src="inc/js/login.js"></script>
    </head>
    <body>
        <h1>Log In</h1>
        <hr>
        <p>Welcome to the E-shop. Please Log In or <a href="sign_up.html">Sign Up</a>.</p>

        <?php
            if (isset($_REQUEST['authenticated']) && $_REQUEST['authenticated']=="false") {
                echo "<h3 id='wrong-credentials'>Wrong user credentials!</h3>";
            }

            if (isset($_REQUEST['regSuccess']) && $_REQUEST['regSuccess']=="true") {
                echo "<h3 id='registration-success'>Registration was successful!</h3>";
            }
        ?>

        <div class="form-container">
            <form method="post" action="inc/logInAction.php" >
                <label for="userEmail">E-Mail:</label>
                <input type="email" id="userEmail" name="userEmail" placeholder="Enter your E-mail" required><br>
                <br>
                <label for="userPassword">Password:</label>
                <input type="password" id="userPassword" name="userPassword" placeholder="Enter your Password" required><br>
                <br>
                <input type="button" class="cancelButton" value="Cancel">
                <input type="submit" class="loginButton" value="Log In">
            </form>
        </div>
    </body>
    <footer>
        <br> <hr>
        <div class="footer-container">
            <p>E-Shop © 2023</p>  
        </div>  
    </footer>
</html>