package com.yoonji.letter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yoonji.article.Article;

@Repository
public class LetterDao {

	static final String WRITE_LETTER ="insert letter(title,content,senderId,senderName,receiverId,receiverName) values(?,?,?,?,?,?)";
	static final String SEND_LETTER ="select letterId,title,receiverId,receiverName,cdate where senderId=?";
	static final String RECEIVE_LETTER="select letterId,title,senderId,senderName,cdate from letter where receiverId=?";
	static final String LIST_LETTER="select letterId,title,content,senderId,senderName,receiverId,receiverName,cdate from letter where letterId=? and(senderId=? or receiverId=?";
	static final String DELETE_LETTER="delete from letter where letterId=? and (senderId=? or receiverId=?";

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	RowMapper<Letter> letterRowMapper = new BeanPropertyRowMapper<>(Letter.class);
	//편지쓰기
	public int writeLetter(Letter letter) {
		return jdbcTemplate.update(WRITE_LETTER, letter.getTitle(),
				letter.getContent(), letter.getSenderId(), letter.getSenderName(),letter.getReceiverId(),letter.getReceiverName());
	}
	//보낸목록
	public Letter sendLetter(String letterId) {
		return jdbcTemplate.queryForObject(SEND_LETTER, letterRowMapper,letterId);
	}
	//받은목록
	public Letter receiveLetter(String letterId) {
		return jdbcTemplate.queryForObject(RECEIVE_LETTER, letterRowMapper,letterId);
	}
	//상세내역보기
	public List<Letter> listLetters(int offset, int count) {
		return jdbcTemplate.query(LIST_LETTER, letterRowMapper, offset,count);
	}
	//편지삭제
	public int deleteArticle(String letterId, String senderId, String receiverId) {
		return jdbcTemplate.update(DELETE_LETTER, letterId, receiverId);
	}
}
