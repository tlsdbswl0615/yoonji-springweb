package com.yoonji.letter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.yoonji.article.Article;
import com.yoonji.article.ArticleDao;
import com.yoonji.book.chap11.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class LetterController {

	@Autowired
	LetterDao letterDao;

	static final Logger logger = LogManager.getLogger();
	
	@GetMapping("/letter/list")
	public void letterList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {

		final int COUNT = 100;
		int offset = (page - 1) * COUNT;

		List<Letter> letterList = letterDao.listLetters(offset, COUNT);
		int totalCount = letterDao.getLettersCount();
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("letterList", letterList);
	}
	/**
	 * 글 보기
	 */
	@GetMapping("/letter/view")
	public void letterView(@RequestParam("letterId") String letterId,Model model) {
		Letter letter = letterDao.getLetter(letterId);
		model.addAttribute("letter", letter);
	}

	/**
	 * 글 등록 화면
	 */
	@GetMapping("/letter/addForm")
	public String letterAddForm(HttpSession session) {
		return "letter/addForm";
	}

	/**
	 * 글 등록
	 */
	@PostMapping("/letter/add")
	public String articleAdd(Letter letter,
			@SessionAttribute("MEMBER") Member member) {
		letter.setUserId(member.getMemberId());
		letter.setName(member.getName());
		letterDao.addLetter(letter);
		return "redirect:/app/letter/list";
	}
	//편지삭제
	@GetMapping("/letter/delete")
	public String delete(
			@RequestParam(value="letterId") String letterId,
			@SessionAttribute("MEMBER") Member member)
	{
		Letter letter = letterDao.getArticle(letterId);
		if(!member.getMemberId().equals(letter.getUserId()))
			return "redirect:/app/letter/view?letterId="+letterId;
		letterDao.deleteLetter(letterId);
			return "redirect:/app/letter/list";
	}	
}
