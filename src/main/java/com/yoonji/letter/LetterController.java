package com.yoonji.letter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.yoonji.book.chap11.Member;
import com.yoonji.book.chap11.MemberDao;

@Controller
public class LetterController {

	@Autowired
	LetterDao letterDao;
	@Autowired
	MemberDao memberDao;

	static final Logger logger = LogManager.getLogger();
	
	@GetMapping("/letter/receivelist")
	public void receiveList(@SessionAttribute("MEMBER") Member member,
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {

		final int COUNT = 100;
		int offset = (page - 1) * COUNT;

		List<Letter> letterList = letterDao.receiveLetter(member.getMemberId(), offset,COUNT);
		model.addAttribute("letterList", letterList);
	}
	@GetMapping("/letter/sendlist")
	public void sendList(@SessionAttribute("MEMBER") Member member,
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {

		final int COUNT = 100;
		int offset = (page - 1) * COUNT;

		List<Letter> letterList = letterDao.sendLetter(member.getMemberId(), offset,COUNT);
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
	 * 글 등록
	 */
	@PostMapping("/letter/add")
	public String articleAdd(Letter letter,
			@RequestParam(value="receiverId") String receiverId,
			@SessionAttribute("MEMBER") Member member) {
		letter.setSenderId(member.getMemberId());
		letter.setSenderName(member.getName());
		letter.setReceiverId(receiverId);
		letter.setReceiverName(memberDao.selectByEmail(receiverId).getName());
		letterDao.writeLetter(letter);
		return "redirect:/app/letter/sendlist";
	}
	//편지삭제
	@GetMapping("/letter/delete")
	public String delete(
			@RequestParam(value="letterId") String letterId,
			@SessionAttribute("MEMBER") Member member)
	{
		Letter letter = letterDao.getLetter(letterId);
		if(!member.getMemberId().equals(letter.getReceiverId())&&!member.getMemberId().equals(letter.getSenderId()))
			return "redirect:/app/letter/view?letterId="+letterId;
		letterDao.deleteLetter(letterId,member.getMemberId(),member.getMemberId());
		return "redirect:/app/member/memberInfo";
	}	
}
