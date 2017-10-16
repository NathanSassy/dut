<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Application de depot de TP</title>
        <link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
    </head>

    <body>
        <?php
        require_once('controllers/FrontController.php');
        echo rooting($_REQUEST);
        ?>
    </body>
</html>
