package com.wej.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wej.exam.vo.Article;

@Controller
public class UsrArticleController {

	private List<Article> articles;
	private int id = 0;
	
	public UsrArticleController () {
		articles = new ArrayList<>();
	}
	
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {

		id ++;
		Article article = new Article(id, title, body);		
		articles.add(article);
		
		return article;
	}
	

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public List<Article> getArticle() {
		return articles;
	}
}

