package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;

	public int countAll() {
		return sqlSession.selectOne("board.countAll");
	}

	public List<BoardVo> findByPage(int currentPage, int displayRow) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("currentPage", (currentPage-1)*displayRow);
		map.put("displayRow",displayRow);
		return sqlSession.selectList("board.findByPage",map);
	}

	public BoardVo findAny(Long no) {
		return sqlSession.selectOne("board.view",no);
	}

	public boolean delete(Long no) {
		int count = sqlSession.delete("board.delete",no);
		return count == 1;
	}

	public void hitUpdate(Long no) {
		sqlSession.update("board.hitUpdate",no);
	}
	
	public void modify(BoardVo vo) {
		sqlSession.update("board.modify",vo);		
	}

	public void insertNew(BoardVo vo) {
		sqlSession.insert("board.insertNew",vo);
	}

	public void updateOrderNo(int groupNo, int orderNo) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo+1);
		sqlSession.update("board.updateOrderNo",map);
	}

	public void insertComment(BoardVo vo) {
		sqlSession.insert("board.insertComment",vo);
	}

	public int getKwdCount(String combo, String kwd) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("combo", combo);
		map.put("kwd", kwd);

		return sqlSession.selectOne("board.getKwdCount",map);
	}

	public List<BoardVo> findByKwd(String combo, String kwd, int page, int displayRow) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("combo", combo);
		map.put("kwd", kwd);
		map.put("currentPage", (page-1)*displayRow);
		map.put("displayRow",displayRow);
		return sqlSession.selectList("board.findByKwd",map);
	}

}
