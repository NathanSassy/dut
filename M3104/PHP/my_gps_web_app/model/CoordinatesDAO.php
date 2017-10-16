<?php
require_once('SqliteConnection.php');
require_once("Coordinates.php");

class CoordinatesDAO {
    private static $dao;

    private function __construct() {
    }

    public final static function getInstance() {
        if(!isset(self::$dao)) {
            self::$dao= new CoordinatesDAO();
        }
        return self::$dao;
    }

    public final function find($id) {
        $dbc = SqliteConnection::getInstance()->getConnection();
        $stmt = $dbc->prepare("SELECT * FROM Coordinates WHERE journey_pos = :journey_pos");
        $stmt->bindValue(':journey_pos', (int) $id, PDO::PARAM_INT);
        $stmt->execute();
        $result = $stmt->fetch(PDO::FETCH_ASSOC);

        $coordinates = new Coordinates();
        $coordinates->setJourneyId($result["journey_id"]);
        $coordinates->setJourneyPos($result["journey_pos"]);
        $coordinates->setLatitude($result["latitude"]);
        $coordinates->setLongitude($result["longitude"]);
        return $coordinates;
    }

    public final function findWithJourneyId($id) {
        $dbc = SqliteConnection::getInstance()->getConnection();
        $stmt = $dbc->prepare("SELECT * FROM Coordinates WHERE journey_id = :id");
        $stmt->bindValue(':id', (int) $id, PDO::PARAM_INT);
        $stmt->execute();
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Coordinates');
        return $results;
    }

    public final function findAll(){
        $dbc = SqliteConnection::getInstance()->getConnection();
        $query = "SELECT * FROM Coordinates";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Coordinates');
        return $results;
    }

    public final function insert(Coordinates $coordinates){
        if($coordinates instanceof Coordinates){
            $dbc = SqliteConnection::getInstance()->getConnection();
            $stmt = $dbc->prepare("INSERT INTO Coordinates VALUES (:journey_id, ;journey_pos, :latitude, :longitude)");
            $stmt->bindValue(':journey_id', $coordinates->getJourneyId(), PDO::PARAM_INT);
            $stmt->bindValue(':journey_pos', $coordinates->getJourneyPos(), PDO::PARAM_INT);
            $stmt->bindValue(':latitude', $coordinates->getLatitude(), PDO::PARAM_STR);
            $stmt->bindValue(':longitude', $coordinates->getLongitude(), PDO::PARAM_STR);
            $stmt->execute();
            return true;
        }
        else {
            return false;
        }
    }

    public final function getLastId() {
        $dbc = SqliteConnection::getInstance()->getConnection();
        $query = "SELECT Count(*) FROM Coordinates";
        $stmt = $dbc->query($query);
        $result = $stmt->fetch();
        $result[0];
    }

    public function delete(Coordinates $coordinates) {
        if($coordinates instanceof Coordinates) {
            $dbc = SqliteConnection::getInstance()->getConnection();
            $stmt = $dbc->prepare("DELETE FROM Coordinates WHERE journey_pos = :journey_pos");
            $stmt->bindValue(':journey_pos', $coordinates->getJourneyPos(), PDO::PARAM_INT);
            $stmt->execute();
            return true;
        }
        else {
            return false;
        }
    }

    public function update(Coordinates $coordinates) {
        if($coordinates instanceof Coordinates) {
            $dbc = SqliteConnection::getInstance()->getConnection();
            $stmt = $dbc->prepare("UPDATE Coordinates SET journey_pos = :journey_pos, latitude = :latitude, longitude = :longitude WHERE journey_id = :journey_id");
            $stmt->bindValue(':journey_pos', $coordinates->getJourneyPos(), PDO::PARAM_INT);
            $stmt->bindValue(':latitude', $coordinates->getLatitude(), PDO::PARAM_STR);
            $stmt->bindValue(':longitude', $coordinates->getLongitude(), PDO::PARAM_STR);
            $stmt->bindValue(':journey_id', $coordinates->getJourneyId(), PDO::PARAM_INT);
            $stmt->execute();
            return true;
        }
        else {
            return false;
        }
    }
}
?>
