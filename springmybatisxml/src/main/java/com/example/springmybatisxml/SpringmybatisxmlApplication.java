package com.example.springmybatisxml;

import com.example.springmybatisxml.mapper.ArticleMapper;
import com.example.springmybatisxml.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class SpringmybatisxmlApplication implements CommandLineRunner {

    /// @Autowired inject field is not recommended
    /// https://cloudolife.com/2021/02/27/Programming-Language/Java/FAQs/Field-injection-is-not-recommended-and-Injection-guidelines-in-Java-Spring/#:~:text=The%20reasons%20why%20field%20injection,in%20unit%20tests)%20without%20reflection.
    private ArticleMapper articleMapper;

    public static void main(String[] args)  {
        SpringApplication.run(SpringmybatisxmlApplication.class, args);
    }

    @Autowired
    public SpringmybatisxmlApplication(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        Article article = articleMapper.getArticle(1L);
        System.out.println(article);
    }
}
