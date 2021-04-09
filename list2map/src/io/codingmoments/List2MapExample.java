package io.codingmoments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Demo application class.
 */
public class List2MapExample {

  private List<Employee> employeeList;

  public static void main(String[] args) {
    List2MapExample example = new List2MapExample();
    example.runDemo();
  }

  public void runDemo() {
    employeeList = buildEmployeeList();

    Map<Long, Employee> employeeIdToEmployeeMap = getMapOfEmployeeIdToEmployee(employeeList);
    System.out.println("Map of EmployeeId to Employee: \n" + employeeIdToEmployeeMap);

    Map<Long, List<Employee>> managerIdToEmployeeListMap = getMapOfManagerIdToEmployeeListIncludingNullManagers(employeeList);
    System.out.println("Map of ManagerId to Employees (including null managerIds): \n" + managerIdToEmployeeListMap);

    managerIdToEmployeeListMap = getMapOfManagerIdToEmployeeList(employeeList);
    System.out.println("Map of ManagerId to Employees: \n" + managerIdToEmployeeListMap);

    Map<Long, List<Employee>> sortedManagerIdToEmployeeListMap = getMapOfSortedManagerIdToEmployeeList(employeeList);
    System.out.println("Map of Sorted ManagerId to Employees: \n" + sortedManagerIdToEmployeeListMap);

    Map<Long, List<String>> sortedManagerIdToEmployeeLastNameListMap = getMapOfSortedManagerIdToEmployeeLastNameList(employeeList);
    System.out.println("Map of Sorted ManagerId to Employees Last Name: \n" + sortedManagerIdToEmployeeLastNameListMap);

    Map<Long, List<Long>> managerIdToEmployeeIdListMap = getMapOfManagerIdToEmployeeIdList(employeeList);
    System.out.println("Map of ManagerId to EmployeeIds: \n" + managerIdToEmployeeIdListMap);

    Map<Long, Task> employeeIdToNewTaskMap = getMapOfEmployeeIdToNewTask(employeeList);
    System.out.println("EmployeeId to new Task: \n" + employeeIdToNewTaskMap);

    Map<Long, List<Task>> employeeIdToEmptyTaskListMap = getMapOfEmployeeIdToEmptyTaskList(employeeList);
    System.out.println("EmployeeId to Emtpy Task List: \n" + employeeIdToEmptyTaskListMap);

    Map<Long, Employee> managerIdToEmployeeWithLeastSalaryMap = getMapOfManagerIdToEmployeeWithLeastSalary(employeeList);
    System.out.println("ManagerId to Employee having least salary: \n" + managerIdToEmployeeWithLeastSalaryMap);

    Map<Employee, Employee> employeeToManagerMap = getMapOfEmployeeToManager(employeeList);
    System.out.println("Employee to Manager Map: \n" + employeeToManagerMap);
  }

  private Map<Long, Employee> getMapOfEmployeeIdToEmployee(List<Employee> employeeList) {
    return employeeList.stream().collect(Collectors.toMap(Employee::getEmployeeId, Function.identity()));
  }

  private Map<Long, List<Employee>> getMapOfManagerIdToEmployeeList(List<Employee> employeeList) {
    return employeeList.stream().filter(e -> e.getManagerId() != null).collect(Collectors.groupingBy(Employee::getManagerId));
  }

  private Map<Long, List<Employee>> getMapOfManagerIdToEmployeeListIncludingNullManagers(List<Employee> employeeList) {
    return employeeList.stream().collect(Collectors.groupingBy(e -> e.getManagerId() == null ? -1L : e.getManagerId()));
  }

  private Map<Long, List<Employee>> getMapOfSortedManagerIdToEmployeeList(List<Employee> employeeList) {
    return employeeList.stream().filter(e -> e.getManagerId() != null).collect(Collectors.groupingBy(Employee::getManagerId, TreeMap::new, Collectors.toList()));
  }

  private Map<Long, List<String>> getMapOfSortedManagerIdToEmployeeLastNameList(List<Employee> employeeList) {
    return employeeList.stream().filter(e -> e.getManagerId() != null)
      .collect(Collectors.groupingBy(Employee::getManagerId, TreeMap::new, Collectors.mapping(Employee::getLastName, Collectors.toList())));
  }

  private Map<Long, List<Long>> getMapOfManagerIdToEmployeeIdList(List<Employee> employeeList) {
    return employeeList.stream().filter(e -> e.getManagerId() != null)
      .collect(Collectors.groupingBy(Employee::getManagerId, HashMap::new, Collectors.mapping(Employee::getEmployeeId, Collectors.toList())));
  }

  private Map<Long, Task> getMapOfEmployeeIdToNewTask(List<Employee> employeeList) {
    return employeeList.stream().collect(Collectors.toMap(Employee::getEmployeeId, e -> new Task()));
  }

  private Map<Long, List<Task>> getMapOfEmployeeIdToEmptyTaskList(List<Employee> employeeList) {
    return employeeList.stream().collect(Collectors.toMap(Employee::getEmployeeId, e -> new ArrayList<>()));
  }

  private Map<Long, Employee> getMapOfManagerIdToEmployeeWithLeastSalary(List<Employee> employeeList) {
    return employeeList.stream().filter(e -> e.getManagerId() != null).collect(Collectors.toMap(Employee::getManagerId, e -> e, (e1, e2) -> e1.getSalary() < e2.getSalary() ? e1 : e2));
  }

  private Map<Employee, Employee> getMapOfEmployeeToManager(List<Employee> employeeList) {
    return employeeList.stream().filter(e -> e.getManagerId() != null).collect(Collectors.toMap(e -> e, e -> {
      Optional<Employee> manager = employeeList.stream().filter(m -> m.getEmployeeId().equals(e.getManagerId())).findFirst();
      return manager.isPresent() ? manager.get() : null;
    }));
  }

  private List<Employee> buildEmployeeList() {
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(new Employee(10L, "Angela", "Williams", null, 10000));
    employeeList.add(new Employee(20L, "Bonnie", "Vargas", 10L, 8000));
    employeeList.add(new Employee(30L, "Frank", "Russell", 10L, 7600));
    employeeList.add(new Employee(40L, "Joe", "Smith", null, 11000));
    employeeList.add(new Employee(50L, "Louise", "Doran", 40L, 9000));
    employeeList.add(new Employee(60L, "Lisa", "Lorentz", 40L, 8000));
    employeeList.add(new Employee(70L, "Michael", "Raphaely", 40L, 9500));
    employeeList.add(new Employee(80L, "Patrick", "Tobias", null, 9000));
    employeeList.add(new Employee(90L, "Rose", "Weiss", 80L, 9200));
    employeeList.add(new Employee(100L, "Todd", "Vollman", 80L, 9400));
    return employeeList;
  }
}
