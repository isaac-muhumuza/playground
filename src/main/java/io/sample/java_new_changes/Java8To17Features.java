package io.sample.java_new_changes;

import io.sample.playground.client.JavaHttpClient;
import io.sample.java_new_changes.features.sealed_classes.Circle;
import io.sample.java_new_changes.features.sealed_classes.Rectangle;
import io.sample.java_new_changes.features.sealed_classes.Shape;
import io.sample.java_new_changes.features.sealed_classes.Square;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java 8-17 changes:
 *  ------------------------------------------------ Java 9 ----------------------------------------------------------
 *  - Modular system (Project Jigsaw)
 *      - Are added via module-info.java file with the following structure:
 *          ```module <module_name> {
 *              requires spring.data.jpa;
 *              requires spring.boot;
 *              requires spring.boot.autoconfigure;
 *              requires ...;
 *          }```
 *      - Benefits of the modular system:
 *          - Better encapsulation
 *          - dependencies available only via export
 *          - Enable running on devices with limited resources/memory
 *  - G1 as the default garbage collector (instead of Parrallel GC) (Starting in Java 9)
 *      - Reason: Preference in limiting the maximum pause times instead of the maximising thoughput.
 *  - JShell: REPL (Read-Eval-Print-Loop) tool
 *  - Try with Resources does not require a variable declaration
 *  - Optional to stream
 * <p>
 *  ------------------------------------------------ Java 10 ----------------------------------------------------------
 *  - Unmodifiable collections enhancements: copy.of and toUnmodifiableList/Set/Map
 *  - Var keyword for local variables (no need to specify type)
 *  - G1 Collector Interface for better modularity
 *  - G1 made fully parallel
 *  - Container Awareness:
 *      - JVM can now detect if it is running in a container and adjust memory/cpu/Thread count limits accordingly
 *  - Optional*.orElseThrow() metthod that throws NoSuchElementException if the value is not present
 * <p>
 *  ------------------------------------------------ Java 11 ----------------------------------------------------------
 * - First LTS since Java 8
 * - New String methods:
 *      - isBlank(),
 *      - lines(),
 *      - strip()(also stripLeading, stripTailing)
 *      - repeat(),
 *      - transform(),
 *      - indent()
 * - File API new methods:
 *      - readString()
 *      - writeString()
 * - Collection API enhancements:
 *     - Collectors.toArray()
 * - Predicate.not() method
 * - HTTPClient API became a standard feature
 * - Performance enhancements such as:
 *     - Dynamic class-file constants (creation is delegeated to a bootstrap method)
 *     - Improved Aarch64 base intrinsics:
 *       - optimises strings and arrays intrinsics on processors leveraging CPU assembly code to boost performance
 * - Compiling java source files with just the "java" command
 * - Flight recorder is now open source
 * <p>
 *  ------------------------------------------------ Java 12 ----------------------------------------------------------
 * - Switch expression improvement
 * - Collectors.teeing() method
 * - File API enhancements with mismatch() method (compares 2 files and returns the position of the first mismatch or -1)
 * - Compact Number Formatting to represent numbers in shorter form: 2.59K for 2592 with 2 digit spacing
 *  ------------------------------------------------ Java 13 ----------------------------------------------------------
 * - Switch expressions have a return feature: yield statement
 * - Text blocks
 * - Class data sharing to allow sharing metadata and reduce start-up time and memory footprint
 * - Reimplement the legacy Socket API
 * - Z Garbage Collector equipped to return unused memory
 *  ------------------------------------------------ Java 14 ----------------------------------------------------------
 * - switch becomes a standard feature
 * - Records: for POJO to reduce boilerplate code.
 *      - Adds constructor, getters, setters, equals, hashcode & toString
 *      - override via canonical constructor that does some validation
 * - Improved NPE with more detailed messages
 * - Pattern matching for 'instanceOf'
 * - Incubator features:
 *     - Foreign memory access API (allow safe and efficient access to memory outside the Java heap)
 *     - Package tool - Jlink to condense and create images that can be run on any OS instead of JAR files
 * - JVM features:
 *     - JFR Flight reocroder Streaming
 *     - G1 Garbage Collector was made NUMA-aware (Non Uniform Memory Access)
 *     - Z Garbage Collector for linux now also available for mac and windows
 * <p>
 *  ------------------------------------------------ Java 15----------------------------------------------------------
 * - records became a standard feature
 * - Sealed classes:
 *     - uses 'sealed' and 'permits' keywords
 *     - classes that extends must be sealed/non-sealed/final
 * - Hidden classes - un-discoverable during runtime
 * - Foreign-Memory API enhancements:
 *     - support parallel processing using spliterator interface
 *     - custom memory access via var handles
 *     - ability to manipulate and dereference addresses
 * - Garbage Collector:
 *    - ZGC (Z Garbage Collector) & Shenandoah became a standard GCs
 * <p>
 *  ------------------------------------------------ Java 16 ----------------------------------------------------------
 * - Pattern matching for 'instanceof' became a standard feature
 * - Invoke default methods in interfaces from via a dynamic proxy
 * - new day period support in DateTimeFormatter for 'am/pm'
 * - Stream toList() method to reduce boilerplate code for collect(Collectors.toList());
 * - Vector API incubator feature for high-performance operations on vectors
 *  - records improved to be defined as class members of inner classes
 * <p>
------------------------------------------------ Java 17 ----------------------------------------------------------
 * - Sealed classes became a standard feature
 * - Always restrict floating point on all OS
 * - Enhanced pseudo-random number generators:
 *     - Different types of random number generators added
 *     - stream based support
 *     - java.util.Random, SplittableRandom and SecureRandom now extend the new RandomGenerator interface)
 * - Pattern matching for switch:
 * - Foreign Function and Memory API:
 *     - Can access code outside the JVM (e.g. from C library)
 *     - Manage memory outside the JVM heap
 * - MacOS changes:
 *     - new rendering pipeline for swing UI - using Apple metal API. (OpenGL is deprecated)
 *     - Aarch64 port (JDK can run onAarch64)
 * - Strongly encapsulate JDK internals (removal of -illegal-access flag)
 * - Vector API:
 *     - Single instruction multiple data (SIMD) support
 *     - meaning various instructions executed in parallel e.g. image processing, character processing, heavy algebra application...
 * - Context specific deserialization filters:
 *     - At JVM level to validating incoming serialized data from untrusted sources
 *     - Improve security.
 * - LTS support changed to 2 years instead of 3.
 **/

@Slf4j
public class Java8To17Features {

    private static JavaHttpClient javaHttpClient = new JavaHttpClient();

    public static void main(String[] args) {
        // private methods inside interfaces
        InterfaceFeatures privateMethodFeature = new InterfaceFeatures() {
        };
        var privateString = privateMethodFeature.printString();
        log.info(privateString);

        // new java http client Java9
        var url = "/posts/1";

        try {
            HttpRequest request = javaHttpClient.getRequest(url);
            log.info("Response from client is: {}", javaHttpClient.getResponse(request, Map.class));
        } catch (URISyntaxException e) {
            log.error("URI {} is not valid", url, e);
        } catch (IOException e) {
            log.error("Failed to send request to {}", url, e);
        } catch (InterruptedException e) {
            log.error("Failed while performing request to {}", url, e);
            Thread.currentThread().interrupt();
        }

        // Optional to stream
        Optional<String> value = Optional.ofNullable(privateString);
        List<String> listFromOptional = value.stream().filter(str -> str.contains("Java 9 feature")).collect(Collectors.toList());
        log.info("Result of optional String is: {}", listFromOptional);

        // immutable collections: List.of, Set.of, Map.of Java9
        Set<String> immutableSet9 = Set.of("key1", "key2", "key3");

        //further enhanced with copy.of in Java10
        Set<String> immutableSet10 = Set.copyOf(immutableSet9);
        log.info("Unmodifiable set: {}", immutableSet10);

        // toUnmodifiableList in Java10
        listFromOptional.add("Java 10 feature: toUnmodifiableList");
        List<String> immutableList10 = listFromOptional.stream().collect(Collectors.toUnmodifiableList());
        log.info("Unmodifiable list: {}", immutableList10);

        // orElseThrow() method Java 10
        var str = immutableList10.stream().filter(s -> s.contains("Java 10 feature")).findFirst().orElseThrow();
        log.info("Result of orElseThrow: {}", str);

        //some new String methods (Java 11)
        String lineString = "This is a new LTS version.\n With some cool some new changes.\n\t LTS will last 3 years \n";
        List<String> lines = lineString.lines()
                .filter(Predicate.not(String::isBlank))
                .map(String::strip)
                .toList();
        log.info("Lines with size {} contains: {}", lines.size(), lines);

        try {
            Path file1 = Files.createTempFile("test1", "txt");
            Path file2 = Files.createTempFile("test2", "txt");

            // writeString Java 11
            file1 = Files.writeString(file1, "Testing writeString java 11 new feature");
            file2 = Files.writeString(file2, "Testing readString java 11 new feature");

            // file mismatch Java12
            long mismatch = Files.mismatch(file1, file2);

            log.info("Files are a mismatch: {}", (mismatch != -1));
            log.info("Mismatch at point: {}", (mismatch == -1 ? "N/A" : mismatch));
        } catch (IOException e) {
            log.error("Error creating files", e);
        }

        // collection teeing
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);

        double mean = stream.collect(Collectors.teeing(Collectors.summingInt(i -> i),
                Collectors.counting(), (sum, count) -> (double) sum / count));

        log.info("Mean from collection Teeing is {}", mean);

        // new switch implementation
        DayOfWeek day = LocalDate.now().getDayOfWeek();
        int numExample = switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> {
                log.info("Weekday, today is: {}", day);
                yield day.getValue();
            }
            case SATURDAY, SUNDAY -> {
                log.info("It's a weekend, day: {}", day);
                yield -1;
            }
        };
        log.info("It is the {} day of the week", numExample);

        //java 13 textblocks
        String textBlock = """
                {
                    "name": "John Doe",
                    "age": 35,
                    "city": "New York"
                }
                """;
        log.info(textBlock);

        //Java 14 - instanceOf pattern matching
        log.info("Length of string is: {}", patternMatchingInstanceOf("instanceOf pattern matching"));

        //java 14 - record
        Person p = new Person("Don", "Trump", 65, "1234567890");
        log.info("Details of Person's record are: name {} {}, aged {} with phone number: {}", p.firstname(), p.lastname(), p.age(), p.phone());

        //java 14 - npe improved
        String stringToLowerCase = null;
        // todo onlu run to check the helpful logging for NPE
        //log.info(stringToLowerCase.toLowerCase());

        // sealed classes java15/16
        Shape shape = new Square();
        new Java8To17Features().printSealedShape(shape);

        // invoke default interface method from dynamic proxy
        Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{InterfaceFeatures.class},
                (proxy1, method, args1) -> {
                    if (method.isDefault()) {
                        return InvocationHandler.invokeDefault(proxy1, method, args1);
                    }
                    return null;
                });

        try {
            Method methodToInvoke = proxy.getClass().getMethod("multiply", int.class, int.class);
            log.info("Interface default method for multiplying 3 and 5 will give  {}", methodToInvoke.invoke(proxy, 3, 5));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.warn("Method not found", e);
        }

        // java 16 - day period suppport - e.g. 2 in the afternoon.
        LocalDateTime dayTime = LocalDateTime.now();
        log.info("Time now is: {}", dayTime.format(DateTimeFormatter.ofPattern("h B")));

        // java 16 - Stream toList() method
        List<String> listInts = List.of("1", "2", "3", "4", "5");
        // the old way of converting to list of ints also allows modification e.g listChange.add(6);
        List<Integer> listCollectionViaStream = listInts.stream().map(Integer::parseInt).collect(Collectors.toList());

        // now becomes
        List<Integer> newUnmodifiableList = listInts.stream().map(Integer::parseInt).toList();
        // will not allow modification e.g. ints.add(7);
        log.info("Unmodifiable list of integers: {}", newUnmodifiableList);

        // java 17 pattern matching for switch
        log.info("Java 17 pattern matching for switch: " + patternMatchingSwitch(new Person("Tester", "Tester", 30, "1234567890")));

    }


    public static int patternMatchingInstanceOf(Object obj) {
        if (obj instanceof String str) {
            return str.length();
        }
        return 0;
    }

    private void printSealedShape(Shape shape) {
        if (shape instanceof Circle circle) {
            circle.printShape();
        } else if (shape instanceof Square square) {
            square.printShape();
        } else if (shape instanceof Rectangle rectangle) {
            rectangle.printShape();
        }
    }

    private static String patternMatchingSwitch(Object o) {
        return switch (o) {
            case Person p -> p.firstname();
            case Shape s -> "Shape";
            case String str -> "String";
            default -> "Unknown";
        };
    }
}

