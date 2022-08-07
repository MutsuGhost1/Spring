package com.example.springmybatis;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.example.springmybatis.mapper.ArticleMapper;
import com.example.springmybatis.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;

@SpringBootApplication
/// @ImportResource("classpath:beans.xml")
public class SpringMybatisApplication implements CommandLineRunner {

    /// @Autowired inject field is not recommended
    /// https://cloudolife.com/2021/02/27/Programming-Language/Java/FAQs/Field-injection-is-not-recommended-and-Injection-guidelines-in-Java-Spring/#:~:text=The%20reasons%20why%20field%20injection,in%20unit%20tests)%20without%20reflection.
    private ArticleMapper articleMapper;

    public static void main(String[] args)  {
        SpringApplication.run(SpringMybatisApplication.class, args);
    }

    @Autowired
    public SpringMybatisApplication(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        /*
        Article article = articleMapper.getArticle(1L);
        System.out.println(article);
         */
        ActorSystem actorSystem = ActorSystem.create("AkkaClassic");
        ActorRef actorRef = actorSystem.actorOf(MyActor.props(10));
        System.out.println("actorRef:" + actorRef);


    }
}
