package com.wej.exam.demo.reporsitory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wej.exam.vo.Article;

@Component
public class ArticleRepository {

	private List<Article> articles;
	private int articlesLastId;
	// 인스턴스 변수 끝

	public ArticleRepository() {
		articles = new ArrayList<>();
		articlesLastId = 0;
	}

	public void makeTestData() {

		for (int i = 1; i <= 10; i++) {

			String title = "제목" + i;
			String body = "내용" + i;

			writeArticle(title, body);

		}

	}

	public Article writeArticle(String title, String body) {

		int id = articlesLastId + 1;
		Article article = new Article(id, title, body);

		articles.add(article);
		articlesLastId = id;

		return article;
	}

	public Article getArticle(int id) {

		for (Article article : articles) {

			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public void deleteArticle(int id) {

		Article article = getArticle(id);

		articles.remove(article);

	}

	public void modifyArticle(int id, String title, String body) {

		Article article = getArticle(id);

		article.setTitle(title);
		article.setBody(body);

	}
	// 서비스 메서드 끝

	public List<Article> getArticles() {

		return articles;
	}

}
