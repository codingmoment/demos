package io.codingmoments;

/**
 * Domain class for Employee
 */
public class Employee {

  private Long employeeId;
  private String firstName;
  private String lastName;
  private Long managerId;
  private Integer salary;

  public Employee(Long employeeId, String firstName, String lastName, Long managerId, Integer salary) {
    super();
    this.employeeId = employeeId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.managerId = managerId;
    this.salary = salary;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Long getManagerId() {
    return managerId;
  }

  public void setManagerId(Long managerId) {
    this.managerId = managerId;
  }

  public Integer getSalary() {
    return salary;
  }

  public void setSalary(Integer salary) {
    this.salary = salary;
  }

  @Override
  public String toString() {
    return "{employeeId:" + employeeId + ", name:" + firstName + " " + lastName + ", managerId:" + managerId + ", salary:" + salary + "}";
  }

}
