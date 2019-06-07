package com.yoonji.letter;
import java.util.List;

<<<<<<< HEAD
import com.yoonji.book.chap11.Member;
=======
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
>>>>>>> b8a8eb8ebbb2fe9f2a569125829d9c8570b98834
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

<<<<<<< HEAD
	/**
	 * 받은 목록
	 */
	@GetMapping("/letter/listReceived")
	public void listReceived(
=======
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
>>>>>>> b8a8eb8ebbb2fe9f2a569125829d9c8570b98834
			@RequestParam(value = "page", defaultValue = "1") int page,
			@SessionAttribute("MEMBER") Member member, Model model) {

		// 페이지당 행의 수와 페이지의 시작점
		final int ROWS_PER_PAGE = 20;
		int offset = (page - 1) * ROWS_PER_PAGE;

		List<Letter> letters = letterDao.listLettersReceived(
				member.getMemberId(), offset, ROWS_PER_PAGE);
		int count = letterDao.countLettersReceived(member.getMemberId());

<<<<<<< HEAD
		model.addAttribute("letters", letters);
		model.addAttribute("count", count);
=======
		List<Letter> letterList = letterDao.sendLetter(member.getMemberId(), offset,COUNT);
		model.addAttribute("letterList", letterList);
>>>>>>> b8a8eb8ebbb2fe9f2a569125829d9c8570b98834
	}

	/**
	 * 보낸 목록
	 */
	@GetMapping("/letter/listSent")
	public void listSent(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@SessionAttribute("MEMBER") Member member, Model model) {

		// 페이지당 행의 수와 페이지의 시작점
		final int ROWS_PER_PAGE = 20;
		int offset = (page - 1) * ROWS_PER_PAGE;

		List<Letter> letters = letterDao.listLettersSent(member.getMemberId(),
				offset, ROWS_PER_PAGE);
		int count = letterDao.countLettersSent(member.getMemberId());

		model.addAttribute("letters", letters);
		model.addAttribute("count", count);
	}
<<<<<<< HEAD

	/**
	 * 보기
	 */
	@GetMapping("/letter/view")
	public void view(@RequestParam("letterId") String letterId,
			@SessionAttribute("MEMBER") Member member, Model model) {

		// 자신의 편지가 아닐 경우 EmptyResultDataAccessException 발생함
		Letter letter = letterDao.getLetter(letterId, member.getMemberId());
		model.addAttribute("letter", letter);
	}

=======
>>>>>>> b8a8eb8ebbb2fe9f2a569125829d9c8570b98834
	/**
	 * 편지 저장
	 */
	@PostMapping("/letter/add")
<<<<<<< HEAD
	public String add(Letter letter,
			@SessionAttribute("MEMBER") Member member) {
		letter.setSenderId(member.getMemberId());
		letter.setSenderName(member.getName());
		letterDao.addLetter(letter);
		return "redirect:/app/letter/listSent";
=======
	public String articleAdd(Letter letter,
			@RequestParam(value="receiverId") String receiverId,
			@SessionAttribute("MEMBER") Member member) {
		letter.setSenderId(member.getMemberId());
		letter.setSenderName(member.getName());
		letter.setReceiverId(receiverId);
		letter.setReceiverName(memberDao.selectByEmail(receiverId).getName());
		letterDao.writeLetter(letter);
		return "redirect:/app/letter/sendlist";
>>>>>>> b8a8eb8ebbb2fe9f2a569125829d9c8570b98834
	}

	/**
	 * 편지 삭제
	 */
	@GetMapping("/letter/delete")
	public String delete(
<<<<<<< HEAD
			@RequestParam(value = "mode", required = false) String mode,
			@RequestParam("letterId") String letterId,
			@SessionAttribute("MEMBER") Member member) {
		int updatedRows = letterDao.deleteLetter(letterId,
				member.getMemberId());
		if (updatedRows == 0)
			// 자신의 편지가 아닐 경우 삭제되지 않음
			throw new RuntimeException("No Authority!");

		if ("SENT".equals(mode))
			return "redirect:/app/letter/listSent";
		else
			return "redirect:/app/letter/listReceived";
	}
=======
			@RequestParam(value="letterId") String letterId,
			@SessionAttribute("MEMBER") Member member)
	{
		Letter letter = letterDao.getLetter(letterId);
		if(!member.getMemberId().equals(letter.getReceiverId())&&!member.getMemberId().equals(letter.getSenderId()))
			return "redirect:/app/letter/view?letterId="+letterId;
		letterDao.deleteLetter(letterId,member.getMemberId(),member.getMemberId());
		return "redirect:/app/member/memberInfo";
	}	
>>>>>>> b8a8eb8ebbb2fe9f2a569125829d9c8570b98834
}

