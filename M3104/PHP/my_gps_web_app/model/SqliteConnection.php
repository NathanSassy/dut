<?php


class SqliteConnection  {

    private static $_instance = null;

    private function __construct() {
    }

    public static function getInstance() {

      if(is_null(self::$_instance)) {
        self::$_instance = new SqliteConnection();
      }

      return self::$_instance;
    }

    public static function getConnection() {
        try {
            $con = new PDO("sqlite:resources/gps_web_app.db");
        }
        catch(PDOException $e) {
            print "Error : " . $e->getMessage() . "<br/>";
            die();
        }
        return $con;
    }
}

?>
