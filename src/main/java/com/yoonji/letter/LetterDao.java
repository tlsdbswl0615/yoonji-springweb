package com.yoonji.letter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LetterDao {

<<<<<<< HEAD
	static final String LIST_LETTERS_RECEIVED = "select letterId,title,senderId,senderName,left(cdate,19) cdate from letter where receiverId=? order by letterId desc limit ?,?";
	static final String LIST_LETTERS_SENT = "select letterId,title,receiverId,receiverName,left(cdate,19) cdate from letter where senderId=? order by letterId desc limit ?,?";

	static final String COUNT_LETTERS_RECEIVED = "select count(letterId) from letter where receiverId=?";
	static final String COUNT_LETTERS_SENT = "select count(letterId) from letter where senderId=?";

	static final String GET_LETTER = "select letterId,title,content,senderId,senderName,receiverId,receiverName,left(cdate,19) cdate from letter where letterId=? and (senderId=? or receiverId=?)";
	static final String ADD_LETTER = "insert letter(title,content,senderId,senderName,receiverId,receiverName) values(?,?,?,?,?,?)";
	static final String DELETE_LETTER = "delete from letter where letterId=? and (senderId=? or receiverId=?)";

=======
	static final String WRITE_LETTER ="insert letter(title,content,senderId,senderName,receiverId,receiverName) values(?,?,?,?,?,?)";
	static final String SEND_LETTER ="select letterId,title,receiverId,receiverName,cdate from letter where senderId=? order by letterId desc limit ?,?";
	static final String RECEIVE_LETTER="select letterId,title,senderId,senderName,cdate from letter where receiverId=? order by letterId desc limit ?,?";
	static final String GET_LETTER="select letterId,title,content,senderId,senderName,receiverId,receiverName,cdate from letter where letterId=?";
	static final String DELETE_LETTER="delete from letter where letterId=? and (senderId=? or receiverId=?)";
	static final String COUNT_SEND = "select count(letterId) from letter where senderId=?";
	static final String COUNT_RECEIVE = "select count(letterId) from letter where receiverId=?";
>>>>>>> b8a8eb8ebbb2fe9f2a569125829d9c8570b98834
	@Autowired
	JdbcTemplate jdbcTemplate;

	RowMapper<Letter> letterRowMapper = new BeanPropertyRowMapper<>(
			Letter.class);

	/**
	 * 받은 목록
	 */
	public List<Letter> listLettersReceived(String receiverId, int offset,
			int count) {
		return jdbcTemplate.query(LIST_LETTERS_RECEIVED, letterRowMapper,
				receiverId, offset, count);
	}
<<<<<<< HEAD

	/**
	 * 보낸 목록
	 */
	public List<Letter> listLettersSent(String senderId, int offset,
			int count) {
		return jdbcTemplate.query(LIST_LETTERS_SENT, letterRowMapper, senderId,
				offset, count);
	}

	/**
	 * 받은 편지 갯수
	 */
	public int countLettersReceived(String receiverId) {
		return jdbcTemplate.queryForObject(COUNT_LETTERS_RECEIVED,
				Integer.class, receiverId);
	}

	/**
	 * 보낸 편지 갯수
	 */
	public int countLettersSent(String senderId) {
		return jdbcTemplate.queryForObject(COUNT_LETTERS_SENT, Integer.class,
				senderId);
	}

	/**
	 * 조회
	 */
	public Letter getLetter(String letterId, String memberId) {
		return jdbcTemplate.queryForObject(GET_LETTER, letterRowMapper,
				letterId, memberId, memberId);
	}

	/**
	 * 추가
	 */
	public int addLetter(Letter letter) {
		return jdbcTemplate.update(ADD_LETTER, letter.getTitle(),
				letter.getContent(), letter.getSenderId(),
				letter.getSenderName(), letter.getReceiverId(),
				letter.getReceiverName());
	}

	/**
	 * 삭제
	 */
	public int deleteLetter(String letterId, String memberId) {
		return jdbcTemplate.update(DELETE_LETTER, letterId, memberId, memberId);
	}

}
=======
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
>>>>>>> b8a8eb8ebbb2fe9f2a569125829d9c8570b98834
