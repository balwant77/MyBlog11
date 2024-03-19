package com.myblog.myblog11.Util;

import java.util.Random;
import java.util.function.Supplier;

public class Test {
    public static void main(String[] args) {
   //PREDICATE FUNCTION INTERFACE
//        Predicate<Integer> val= n->n%2==0;
//        boolean test = val.test(11);
//        System.out.println(test);
//
//        Predicate<Integer> val1=n->n%2!=0;
//        boolean test1 = val1.test(13);
//        System.out.println(test1);
//
//       Predicate<String>val2=n->n.equals("Regnar");
//        boolean test2 = val2.test("Regnar");
//        System.out.println(test2);

//        List<Integer> list = Arrays.asList(11, 23, 32, 43, 23, 22, 10);
//        List<Integer> col = list.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
//        System.out.println(col);
//
//        List<Integer> list1 = Arrays.asList(11, 12, 23, 32, 33);
//        List<Integer> col1 = list1.stream().filter(n -> n % 2 != 0).collect(Collectors.toList());
//        System.out.println(col1);
//
//        List<String> list2 = Arrays.asList("Vjorn", "Regnar", "Ivar", "Vitsurk");
//        List<String> col2 = list2.stream().filter(n -> n.equals("Vjorn")).collect(Collectors.toList());
//        System.out.println(col2);
//        List<String> col3 = list2.stream().filter(n -> n.endsWith("r")).collect(Collectors.toList());
//        List<String> col4 = list2.stream().filter(n -> n.startsWith("I")).collect(Collectors.toList());
//        List<String> col5 = list2.stream().filter(n -> n.contains("i")).collect(Collectors.toList());
//        System.out.println(col3);
//        System.out.println(col4);
//        System.out.println(col5);

   //......................................................................................
   //FUNCTION FUNCTIONAL INTERFACE

//     Function<String,Integer> val=str->str.length();
//     Integer res = val.apply("Regnar");
//     System.out.println(res);
//
//     Function<Integer,Integer>val1=i->i+10;
//     Integer res1 = val1.apply(11);
//     System.out.println(res1);
//     List<Integer> list = Arrays.asList(10, 12, 22, 23, 43);
//     List<Integer> collect = list.stream().map(i -> i + 10).collect(Collectors.toList());
//     System.out.println(collect);

//     List<String> list1 = Arrays.asList("adam", "ada", "smith", "ivar");
//     List<String> collect1 = list1.stream().map(i -> i.toUpperCase()).collect(Collectors.toList());
//     System.out.println(collect1);

//        List<String> list2 = Arrays.asList("smith", "ivar", "ada", "roman");
//        List<String> collect = list2.stream().sorted().collect(Collectors.toList());
//        System.out.println(collect);
////
//        List<Integer>list3=Arrays.asList(88,11,8,23,33,11,22,7,45,55);
//        List<Integer>collect2=list3.stream().sorted().distinct().collect(Collectors.toList());
//        System.out.println(collect2);
//-----------------------------------------------------------------------------------------
  //CONSUMER INTERFACE

//   Consumer<Integer> result= number -> System.out.println(number);
//   result.accept(100);
//        List<String> list = Arrays.asList("mike", "adam", "sam");
//        Consumer<String> val=name-> System.out.println(name);
//        list.forEach(val);

    //.........................................................................................
    //SUPPLIER INTERFACE
        Supplier<Integer>random = ()->new Random().nextInt(500);
        Integer i = random.get();
        System.out.println(i);

    }

}
