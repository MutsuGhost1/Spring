package com.example.springmybatisxml.mapper;

import com.example.springmybatisxml.pojo.Article;
import org.apache.ibatis.annotations.Select;

public interface ArticleMapper {
    ///@Select("SELECT * FROM ARTICLES WHERE id = #{id}")
    Article getArticle(Long id);
}
