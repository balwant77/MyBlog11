package com.myblog.myblog11.Util;

public class Test1 {
    public static void main(String[] args) {
//        List<Login> logins = Arrays.asList(new Login("mike", "testing"),
//                new Login("adam", "testing"),
//                new Login("stallin", "testing"));
//        List<LoginDto> dtos = logins.stream().map(login -> mapToDto(login)).collect(Collectors.toList());
//    }
//    static LoginDto mapToDto(Login login){
//        LoginDto dto = new LoginDto();
//        dto.setUserName(login.getUserName());
//        dto.setPassword(login.getPassword());
//        return dto;
//------------------------------------------------------------------------------------------------------------
// Filter out the employee who's age is greator than 30.
//        List<Employee> employees = Arrays.asList(new Employee("mike", 30, "chennai"),
//                new Employee("stallin",34,"bangalore"),
//                new Employee("adam",30,"hydrabad"),
//                new Employee("sam",25,"bangalore")
//        );
//        List<Employee> collect = employees.stream().filter(emp -> emp.getAge() > 30).collect(Collectors.toList());
//        for(Employee e:collect){
//            System.out.println(e.getName());
//            System.out.println(e.getCity());
///            System.out.println(e.getAge());
//-------------------------------------------------------------------------------------------------------------------------------
//Filter out the name that starts which letter a
//        List<Employee> employees = Arrays.asList(new Employee("mike", 30, "chennai"),
//                new Employee("stallin",34,"bangalore"),
//                new Employee("adam",30,"hydrabad"),
//                new Employee("sam",25,"bangalore"),
//                new Employee("aanchal",18,"Shimla")
//        );
//        List<Employee> collect = employees.stream().filter(emp -> emp.getName().startsWith("a")).collect(Collectors.toList());
//    for(Employee e:collect){
//        System.out.println(e.getName());
//    }
        //-------------------------------------------------------------------------------------------------
        //You have a list of integers. your task is to filter out the even numbers, square each of them and find sum of the squared value
        //use filter, map and reduce operation.
//        List<Integer> list = Arrays.asList(11, 12, 4, 7, 6, 20);
//        Integer reduce = list.stream().filter(num -> num % 2 == 0).map(i -> i * i).collect(Collectors.toList()).stream().reduce(0, (a, b) -> a + b);
//        System.out.println(reduce);
//
        //------------------------------------------------------------------------------------------------------
        //Using Group by and printing the values based on key. Mapping
//        List<Employee> employees = Arrays.asList(new Employee("mike", 30, "chennai"),
//                new Employee("stallin", 34, "bangalore"),
//                new Employee("adam", 30, "hydrabad"),
//                new Employee("sam", 25, "bangalore"),
//                new Employee("aanchal", 18, "Shimla"));
//        Map<Integer, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(i -> i.getAge()));
//
//        for (Map.Entry<Integer, List<Employee>> entry : collect.entrySet()) {
//            Integer key = entry.getKey();
//            List<Employee> value = entry.getValue();
//            System.out.println("Age" + key);
//            for (Employee e : value) {
//
//                System.out.println(e.getName());
//                System.out.println(e.getCity());
//            }
//
//        }
    }

    }







