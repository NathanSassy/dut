<?php
require_once('SqliteConnection.php');
class StudentDAO {
    private static $dao;

    private function __construct() {
    }

    public final static function getInstance() {
        if(!isset(self::$dao)) {
            self::$dao= new StudentDAO();
        }
        return self::$dao;
    }

    public final function findAll(){
        $dbc = SqliteConnection::getInstance()->getConnection();
        $query = "select * from students order by nom,prenom";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Student');
        return $results;
    }

    public final function insert(DataObject $st){
        if($st instanceof Student){
            $dbc = SqliteConnection::getInstance()->getConnection();
            // prepare the SQL statement
            $query = "insert into students(login, nom, prenom) values (:l,:n,:p)";
            $stmt = $dbc->prepare($query);

            // bind the paramaters
            $stmt->bindValue(':l',$st->getLogin(),PDO::PARAM_STR);
            $stmt->bindValue(':n',$st->getLastname(),PDO::PARAM_STR);
            $stmt->bindValue(':p',$st->getFirstname(),PDO::PARAM_STR);

            // execute the prepared statement
            $stmt->execute();
        }
    }

    //public function delete(DataObject $obj){ ... }

    //public function update(DataObject $obj){...}
}
?>
