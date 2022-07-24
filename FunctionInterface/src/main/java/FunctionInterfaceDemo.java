import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionInterfaceDemo {
    private static List<Person> personList = List.of(
            new Person("Sara", 20),
            new Person("Nancy", 22),
            new Person("Bob", 20),
            new Person("Paula", 32),
            new Person("Paul", 32),
            new Person("Jack", 3),
            new Person("Bill", 72),
            new Person("Jill", 11)
    );

    public static void main(String[] args) {
        /// demoJoining();
        /// demoFunctionalInterfaces();
        /// demoFunctionalInterfaces2();
        /// demoFunctionalInterfaceDefaultComposeFunction();

        System.out.println(TimeUnit.HOURS.toMillis(2));
    }

    private static void demoFunctionalInterfaceDefaultComposeFunction() {
        /// obj -> obj.toString() is equal to Object::toString
        Function<Integer, String> intToString = Objects::toString;
        Function<String, String> wrapQuote = str -> "'" + str + "'";
        /// compose function will be executed first
        Function<Integer, String> wrapIntWithQuoteString = wrapQuote.compose(intToString);
        /* output
           '10'
         */
        System.out.println(wrapIntWithQuoteString.apply(10));
    }

    private static void demoFunctionalInterfaces2() {
        Map<String, Integer> nameMap = new HashMap<>();
        /*
            Below function declaration means a single argument is represented by the
              Function Interface which is parameterized by the types of
              - its argument (T)
              - and return value (R)
            public interface Function<T, R> { … }

            @Contract(mutates = "this")
            public V computeIfAbsent(K key,
                @NotNull  java.util.function.Function<? super K, ? extends V> mappingFunction)
         */
        /* OUTPUT
            4
            5
            {Johny=5, John=4}
         */
        System.out.println(nameMap.computeIfAbsent("John", s -> s.length()));
        System.out.println(nameMap.computeIfAbsent("Johny", String::length));
        System.out.println(nameMap);
    }

    private static void demoFunctionalInterfaces() {
        /// Without Functional Interfaces
        System.out.println("=====================================");
        personList.stream().forEachOrdered(new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                System.out.println(person);
            }
        });
        /// With lambda expression
        /// Syntactic improvement in the form of lambda expression
        /// Lambda expression can be converted to Functional Interface
        System.out.println("=====================================");
        personList.stream().forEachOrdered(p -> System.out.println(p));
        /// With method reference
        System.out.println("=====================================");
        personList.stream().forEachOrdered(System.out::println);
    }

    private static void demoJoining() {
        /// requirements
        /// create comma separated the names in uppercase of people older than 30
        StringBuilder strBuilder = new StringBuilder();
        for(Person person : personList) {
            if(person.getAge() > 30)
                strBuilder.append(person.getName().toUpperCase() + ", ");
        }
        System.out.println(strBuilder);

        String result = personList.stream()
                .filter(p -> p.getAge() > 30)
                .map(Person::getName)
                .map(String::toUpperCase)
                .collect(Collectors.joining(", "));
        System.out.println(result);
    }

    private static void demoPartition() {
        /*
        public abstract <R, A> R collect( java.util.stream.Collector<? super T, A, R> collector )
        Performs a mutable reduction operation on the elements of this stream using a Collector. A Collector encapsulates the functions used as arguments to collect(Supplier, BiConsumer, BiConsumer), allowing for reuse of collection strategies and composition of collect operations such as multiple-level grouping or partitioning.
                If the stream is parallel, and the Collector is concurrent, and either the stream is unordered or the collector is unordered, then a concurrent reduction will be performed (see Collector for details on concurrent reduction.)
        This is a terminal operation.
                When executed in parallel, multiple intermediate results may be instantiated, populated, and merged so as to maintain isolation of mutable data structures. Therefore, even when executed in parallel with non-thread-safe data structures (such as ArrayList), no additional synchronization is needed for a parallel reduction.
                Params:
        collector – the Collector describing the reduction
        Returns:
        the result of the reduction
        API Note:
        The following will accumulate strings into a List:
        List<String> asList = stringStream.collect(Collectors.toList());
        The following will classify Person objects by city:
        Map<String, List<Person>> peopleByCity      = personStream.collect(Collectors.groupingBy(Person::getCity));
        The following will classify Person objects by state and city, cascading two Collectors together:
        Map<String, Map<String, List<Person>>> peopleByStateAndCity      = personStream.collect(Collectors.groupingBy(Person::getState, Collectors.groupingBy(Person::getCity)));
         */
    }
}
