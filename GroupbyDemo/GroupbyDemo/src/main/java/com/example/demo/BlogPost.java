package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlogPost {
    String title;
    String author;
    BlogPostType type;
    int likes;

    public BlogPost(String title, String author, BlogPostType type, int likes) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.likes = likes;
    }

    public BlogPostType getBlogPostType() {
        return type;
    }

    public int getLikes() {
        return likes;
    }

    @Override
    public String toString() {
        return "BlogPost {" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", type=" + type +
                ", likes=" + likes +
                " }";
    }

    public static void main(String[] args) {

        List<BlogPost> blogPostList = List.of(
                new BlogPost("A", "Man", BlogPostType.NEWS, 0),
                new BlogPost("A", "Boy", BlogPostType.REVIEW, 1),
                new BlogPost("A", "Male", BlogPostType.REVIEW, 0),
                new BlogPost("B", "Woman", BlogPostType.NEWS, 0),
                new BlogPost("B", "Girl", BlogPostType.REVIEW, 1),
                new BlogPost("B", "FeMale", BlogPostType.REVIEW, 0)
                );

        /// https://www.baeldung.com/java-8-collectors#:~:text=collect()%20Method-,Stream.,held%20in%20a%20Stream%20instance.
        /*
            1. What's stream().collect()?
                public abstract <R, A> R collect(java.util.stream.Collector<? super T, A, R> collector)

                Performs a mutable reduction operation on the elements of this stream using a Collector.
                A Collector encapsulates the functions used as arguments to collect(Supplier, BiConsumer, BiConsumer),
                allowing for reuse of collection strategies and composition of collect operations such as multiple-level grouping or partitioning.

                If the stream is parallel, and the Collector is concurrent, and either the stream is unordered or the collector is unordered, then a concurrent reduction will be performed (see Collector for details on concurrent reduction.)
                This is a terminal operation.

                When executed in parallel, multiple intermediate results may be instantiated, populated, and merged so as to maintain isolation of mutable data structures.
                Therefore, even when executed in parallel with non-thread-safe data structures (such as ArrayList), no additional synchronization is needed for a parallel reduction.
                Params:
                collector – the Collector describing the reduction
                Returns:
                the result of the reduction
                API Note:
                a. The following will accumulate strings into a List:
                   List<String> asList = stringStream.collect(Collectors.toList());
                b. The following will classify Person objects by city:
                   Map<String, List<Person>> peopleByCity      = personStream.collect(Collectors.groupingBy(Person::getCity));
                c. The following will classify Person objects by state and city, cascading two Collectors together:
                   Map<String, Map<String, List<Person>>> peopleByStateAndCity      = personStream.collect(Collectors.groupingBy(Person::getState, Collectors.groupingBy(Person::getCity)));
            2. Know the function interface in java.util.function
               a. Consumer   - Only Input Parameter with Generic Type and No Return Value
               b. Supplier   - Only Return Value and No Input
               c. Predicator
               d. Function
               e. Operator
         */

        Map<BlogPostType, List<BlogPost>> groupList =
                blogPostList.stream().collect(Collectors.groupingBy(BlogPost::getBlogPostType));
        System.out.println(groupList);

        Map<BlogPostType, Map<Integer, List<BlogPost>>> groupMapList =
                blogPostList.stream().collect(Collectors.groupingBy(BlogPost::getBlogPostType, Collectors.groupingBy(BlogPost::getLikes)));
        System.out.println(groupMapList);
    }
}

/// Understand basic Function interface in Java
/// E.g.