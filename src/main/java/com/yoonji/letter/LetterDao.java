package com.yoonji.letter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LetterDao {

	static final String WRITE_LETTER ="insert letter(title,content,senderId,senderName,receiverId,receiverName) values(?,?,?,?,?,?)";
	static final String SEND_LETTER ="select letterId,title,receiverId,receiverName,cdate from letter where senderId=? order by letterId desc limit ?,?";
	static final String RECEIVE_LETTER="select letterId,title,senderId,senderName,cdate from letter where receiverId=? order by letterId desc limit ?,?";
	static final String GET_LETTER="select letterId,title,content,senderId,senderName,receiverId,receiverName,cdate from letter where letterId=?";
	static final String DELETE_LETTER="delete from letter where letterId=? and (senderId=? or receiverId=?)";
	static final String COUNT_SEND = "select count(letterId) from letter where senderId=?";
	static final String COUNT_RECEIVE = "select count(letterId) from letter where receiverId=?";
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	RowMapper<Letter> letterRowMapper = new BeanPropertyRowMapper<>(Letter.class);
	//편지쓰기
	public int writeLetter(Letter letter) {
		return jdbcTemplate.update(WRITE_LETTER, letter.getTitle(),
				letter.getContent(), letter.getSenderId(), letter.getSenderName(),letter.getReceiverId(),letter.getReceiverName());
	}
	//보낸목록
	public List<Letter> sendLetter(String letterId,int offset, int count) {
		return jdbcTemplate.query(SEND_LETTER, letterRowMapper,letterId,offset, count);
	}
	//받은목록
	public List<Letter> receiveLetter(String letterId,int offset, int count) {
		return jdbcTemplate.query(RECEIVE_LETTER, letterRowMapper,letterId,offset, count);
	}
	//상세내역보기
	public Letter getLetter(String letterId) {
		return jdbcTemplate.queryForObject(GET_LETTER, letterRowMapper, letterId);
	}
	//편지삭제
	public int deleteLetter(String letterId, String senderId, String receiverId) {
		return jdbcTemplate.update(DELETE_LETTER, letterId, senderId,receiverId);
	}
	
	
}
