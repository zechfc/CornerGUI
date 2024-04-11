package model;
public class User {
  protected String userID;
  protected String firstName;
  protected String lastName;
  protected String middleName;
  protected String age;
  protected String email;
  protected String password;

  // new account
  public User(String userID, String firstName, String middleName, String lastName, String age, String email,
      String password) {
    this.userID = userID;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.age = age;
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getUserAge() {
    return age;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFullName(){
    return this.firstName + " " + this.middleName + " " + this.lastName;
  }

  public String getPassword() {
    return password;
  }

  public String getUserID() {
    return userID;
  }
}