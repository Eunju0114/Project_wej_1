package com.wej.exam.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wej.exam.demo.repository.ArticleRepository;
import com.wej.exam.demo.utill.Ut;
import com.wej.exam.demo.vo.Article;
import com.wej.exam.demo.vo.ResultData;

@Service
public class ArticleService {

	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	// 서비스 메서드 시작

	public ResultData writeArticle(int memberId, String title, String body) {
		articleRepository.writeArticle(memberId, title, body);

		int id = articleRepository.getLastInsertId();
		
		return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다.", id),"id", id);
	}

	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}

	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}

	public ResultData modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
		
		Article article = getArticle(id);
		
		return ResultData.from("S-1", Ut.f("%d번 게시물이 수정되었습니다.", id),"article", article);
		}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public ResultData actorCanModify(int actorId, Article article) {
		if( article == null ) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다");
		}
		
		if( article.getMemberId() != actorId ) {
			return ResultData.from("F-1", "권한이 없습니다.");
		}
		
		return ResultData.from("S-1", "게시물 삭제가 가능합니다.");
	}
	
	public ResultData actorCanDelete(int actorId, Article article) {
		if ( article == null ) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다.");
		}

		if ( article.getMemberId() !=  actorId) {
			return ResultData.from("F-2", "권한이 없습니다.");
		}

		return ResultData.from("S-1", "게시물 삭제가 가능합니다.");
	}

}
