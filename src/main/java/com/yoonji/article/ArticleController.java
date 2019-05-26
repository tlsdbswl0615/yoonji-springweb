package com.yoonji.article;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.yoonji.book.chap11.Member;

@Controller
public class ArticleController {

	@Autowired
	ArticleDao articleDao;

	Logger logger = LogManager.getLogger();

	/**
	 * 글 목록
	 */
	@GetMapping("/article/list")
	public void articleList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {

		// 페이지당 행의 수와 페이지의 시작점
		final int COUNT = 100;
		int offset = (page - 1) * COUNT;

		List<Article> articleList = articleDao.listArticles(offset, COUNT);
		int totalCount = articleDao.getArticlesCount();
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("articleList", articleList);
	}

	/**
	 * 글 보기
	 */
	@GetMapping("/article/view")
	public void articleView(@RequestParam("articleId") String articleId,
			Model model) {
		Article article = articleDao.getArticle(articleId);
		model.addAttribute("article", article);
	}

	
	@GetMapping("/article/addForm")
	public String articleAddForm(HttpSession session) {
		
		return "article/addForm";
	}


	@PostMapping("/article/add")
	public String articleAdd(Article article, 
			@SessionAttribute("MEMBER")Member member) {
		article.setUserId(member.getMemberId());
		article.setName(member.getName());
		articleDao.addArticle(article);
		return "redirect:/app/article/list";
	}
	
	@GetMapping("/article/reviseForm")
	public String reviseForm(@RequestParam(value = "articleId") String articleId,
			@SessionAttribute("MEMBER") Member member
			,Model model)
	{
		Article article = articleDao.getArticle(articleId);
		if(!member.getMemberId().equals(article.getUserId()))
			return "redirect:/app/article/view?articleId="+articleId;
			//return "forward:/app/article/articles";
		model.addAttribute("article",article);
		return "article/reviseForm";
	}
	@PostMapping("/article/revise")
	public String revise(Article article,
			@RequestParam(value="articleId") String articleId,
			@SessionAttribute("MEMBER") Member member)
	{
		try {
			articleDao.updateArticle(article);
			return "redirect:/app/article/view?articleId="+articleId;
		} catch (DuplicateKeyException e) {
			return "redirect:/app/article/list";
		}
	}
	@GetMapping("/article/delete")
	public String delete(
			@RequestParam(value="articleId") String articleId,
			@SessionAttribute("MEMBER") Member member)
	{
		Article article = articleDao.getArticle(articleId);
		if(!member.getMemberId().equals(article.getUserId()))
			return "redirect:/app/article/view?articleId="+articleId;
		articleDao.deleteArticle(articleId);
		return "redirect:/app/article/list";
	}
}