public
abstract
class
Employee
 {
private
 String firstName;
private
 String lastName;
//constructor
public
 Employee (String first, String last) {
firstName = first;
lastName = last;
}
public
 String getFirstName() {
return
 firstName;
}
public
 String getLastName(){
return
 lastName;
    }
public
 String toString(){
return
 firstName +
' '
+ lastName;
}
public
abstract
double
 earnings();
}
