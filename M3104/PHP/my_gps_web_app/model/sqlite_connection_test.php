<?php

require_once("StudentDAO.php");
require_once("Student.php");

function testStudentDAO() {
    $dao = StudentDAO::getInstance();
    $s = new Student();
    $s->init("toto", "titi");
    $dao->insert($s);

}

testStudentDAO();

?>
