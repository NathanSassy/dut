<?php
require_once('SqliteConnection.php');
require_once("Journey.php");

class JourneyDAO {
    private static $dao;

    private function __construct() {
    }

    public final static function getInstance() {
        if(!isset(self::$dao)) {
            self::$dao= new JourneyDAO();
        }
        return self::$dao;
    }

    public final function find($id) {
        $dbc = SqliteConnection::getInstance()->getConnection();

        $stmt = $dbc->prepare("SELECT * FROM Journey WHERE id = :journey_id");
        $stmt->bindValue(':journey_id', (int) $id, PDO::PARAM_INT);
        $stmt->execute();
        $result = $stmt->fetch(PDO::FETCH_ASSOC);

        $journey = new Journey();
        $journey->setId($result["id"]);
        $journey->setDescription($result["description"]);
        $journey->setDateCreation($result["date_creation"]);
        $journey->setDistance($result["distance"]);
        return $journey;
    }

    public final function findAll(){
        $dbc = SqliteConnection::getInstance()->getConnection();
        $query = "SELECT * FROM Journey;";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Journey');
        return $results;
    }

    public final function insert(Journey $journey){
        if($journey instanceof Journey){
            $dbc = SqliteConnection::getInstance()->getConnection();
            $stmt = $dbc->prepare("INSERT INTO Journey(description) values (:d)");
            $stmt->bindValue(':d', $journey->getDescription(), PDO::PARAM_STR);
            $stmt->execute();
            return true;
        }
    }

    public function delete(Journey $journey) {
        if($journey instanceof Journey) {
            $dbc = SqliteConnection::getInstance()->getConnection();
            $stmt = $dbc->prepare("DELETE FROM Journey WHERE id = :journey_id");
            $stmt->bindValue(':journey_id', $journey->getId(), PDO::PARAM_INT);
            $stmt->execute();
            return true;
        }
    }

    public function update(Journey $journey) {
        if($journey instanceof Journey) {
            $dbc = SqliteConnection::getInstance()->getConnection();
            $stmt = $dbc->prepare("UPDATE Journey SET description = :description, distance = :distance WHERE id = :id");
            $stmt->bindValue(':description', $journey->getDescription(), PDO::PARAM_STR);
            $stmt->bindValue(':distance', $journey->getDistance(), PDO::PARAM_STR);
            $stmt->bindValue(':id', $journey->getId(), PDO::PARAM_INT);
            $stmt->execute();
            return true;
        }
    }
}
?>
