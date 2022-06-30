package com.wej.exam.demo.reporsitory;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wej.exam.vo.Article;

@Mapper
public interface ArticleRepository {

	@Insert("insert into article set title = #{title}, `body`= #{body}, regDate = now(), updateDate = now()")
	public void writeArticle(@Param("title") String title,@Param("body") String body);

	@Select("select * from article WHERE id = #{id}")
	public Article getArticle(@Param("id") int id);

	@Delete("delete from article WHERE id = #{id}")
	public void deleteArticle(@Param("id") int id);

	@Update("update article set title = #{title}, `body` = #{body}, updateDate = now() WHERE id = #{id}")
	public void modifyArticle(@Param("id") int id,@Param("title") String title,@Param("body") String body);

	@Select("select * from article order by id desc")
	public List<Article> getArticles();

	@Select("select LAST_INSERT_ID()")
	public int getLastInsertId();

}
