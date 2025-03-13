package io.sample.playground.service;


import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class LinkedListNodesSample {
    static Node head;

    Node reverseListIterative(Node node) {
        Node next = null;
        Node prev = null;
        Node curr = node;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        node = prev;
        return node;
    }

    Node reverseListRecursive(Node curr, Node prev) {
        if (curr.next == null) {
            head = curr;

            curr.next = prev;
            return null;
        }

        Node next = curr.next;
        curr.next = prev;

        reverseListRecursive(next, curr);
        return head;
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    void printLinkedNodes(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    int[] finiobachi (int n) {
        int[] fib = new int[n];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i < n; i++) {
            fib[i] = fib[i-2] + fib[i-1];
        }
        return fib;
    }

    public static void main(String[] args) {
        LinkedListNodesSample list = new LinkedListNodesSample();
        list.head = new Node(1);
        list.head.next = new Node(2);
        list.head.next.next = new Node(3);
        list.head.next.next.next = new Node(4);
        list.head.next.next.next.next = new Node(5);

        list.printLinkedNodes(head);

        System.out.println(" ");
        head = list.reverseListIterative(head);
        System.out.println("Reverse using iterative method");
        list.printLinkedNodes(head);
        System.out.println(" ");
        System.out.println("Reverse using recursive method");

        head = list.reverseListRecursive(head, null);
        list.printLinkedNodes(head);
        System.out.println(" ");
        // print finobachi
        int[] serires = list.finiobachi(10);
        for (int serire : serires) {
            System.out.print(serire + ",");
        }

        System.out.println(" ");
        // java 11
        String lineString = "This is a new language.\n Please prepare for a test.\n\t it will be coming soon";
        List<String> lines = lineString.lines()
                .filter(Predicate.not(String::isBlank))
                .map(String::strip)
                .toList();

        for (String line : lines) {
            System.out.println(line);
        }


        //java 12 - teeing to
        Stream<Integer> stream = Stream.of(1,2,3,4,5);

        double mean = stream.collect(Collectors.teeing(Collectors.summingInt(i -> i),
                Collectors.counting(), (sum, count) -> (double) sum / count));

        System.out.println("Mean: " + mean);

        //java 11 (writeString) and 12 mismatch

        try {
            Path file1 = Files.createTempFile("test1","txt");
            Path file2 = Files.createTempFile("test2", "txt");
            file1 = Files.writeString(file1, "Testing java 11 new feature");
            file2 = Files.writeString(file2, "Testing java 11 new feature");

            long mismatch = Files.mismatch(file1, file2);

            System.out.println("Files are a match: " + (mismatch != -1));
            System.out.println("Mismatch at point: " + (mismatch == -1 ? "N/A" : mismatch));

        } catch (IOException e) {
            System.out.println("Error creating files" + e);
        }

        // java 12 + 13(yield) swithc
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        int numExample = switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> {
                //System.out.println("Weekday,  today is: " + day);
                yield day.getValue();
            }
            case SATURDAY, SUNDAY -> {
                yield -1;
            }
            default -> 0;
        };

        // java 17 pattern matching for switch
        System.out.println("Java 17 patthern matching produces: " + list.patternMatchingSwitch(new Person(12, "Isaac-switch")));

        System.out.println(" ");
        System.out.println("Today is: " + day + " and the value is: " + numExample);

        //java 13 textblocks
        String textBlock = """
                {
                    "name": "John",
                    "age": 30,
                    "city": "New York"
                }
                """;
        System.out.println(textBlock);

        //Java 14 - instanceOf pattern matching
        Object object = new Object();
        object = "This is a string";

        if (object instanceof String str) {
            int len = str.length();
            System.out.println("Length of string is: " + len);
        }

        //java 14 - record
        Person p = new Person(1, "ISaac-G");

        System.out.println("Person here has details: " + p.id() + " " + p.name());

        //java 14 - npe improved
        String str = null;
       // onlu use to see the helpful logging for NPE
        // System.out.println(str.toLowerCase());

        // java 16 invoke default interface mthd from proxy instance
        Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{DefaultEnhancement.class},
                (proxy1, method, args1) -> {
                    if (method.isDefault()) {
                        return InvocationHandler.invokeDefault(proxy1, method, args1);
                    }
                    return null;
                });

        try {
            Method methodToInvoke = proxy.getClass().getMethod("testMultiply", int.class, int.class);

            System.out.println("Interface default method for multiplying 3 and 5 will give  "
                    + methodToInvoke.invoke(proxy, 3, 5));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("FAiled to invoke default method in interface " + e);
        }
        System.out.println(" ");
        // java 16 - day period suppport - e.g. 2 in the afternoon.
        LocalDateTime dayTime = LocalDateTime.now();
        System.out.println("TIME NOW IS: " + dayTime.format(DateTimeFormatter.ofPattern("h B")));

        System.out.println(" ");
        List<String> listInts = List.of("1", "2", "3", "4", "5");
        // the old way of converting to list of ints
        List<Integer> listChange = listInts.stream().map(Integer::parseInt).collect(Collectors.toList());
        // now becomes
        List<Integer> ints = listInts.stream().map(Integer::parseInt).toList();

    }
    public record Person (int id, String name) {};

    private String patternMatchingSwitch(Object o) {
        return switch (o) {
            case Person p -> p.name();
            case String s -> "String";
            case Double d -> "Double";
            default -> "Unknown";
        };
    }
}

interface DefaultEnhancement {
    default int testMultiply(int a, int b) {
        return a * b;
    }
}

