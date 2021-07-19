package co.kgggy.manager.member.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.kgggy.manager.Dao;
import co.kgggy.manager.member.service.MemberService;
import co.kgggy.manager.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private Connection conn = Dao.getConnection(); // static 바로 불러옴. //DBMS와 연결하는 객체.
	private PreparedStatement psmt; // Connection을 통해 sql명령을 전달하고 실행하는 객체.
	private ResultSet rs; // select 구문을 호출시 결과를 돌려받는 객체.

	@Override
	public List<MemberVO> memberSelectList() {
		// TODO 회원 전체 목록.
		List<MemberVO> members = new ArrayList<MemberVO>();
		MemberVO member;// 하나씩 담기위해.
		String sql = "select * from member";
		try {
			psmt = conn.prepareStatement(sql); // sql명령 실어서 보내줌.
			rs = psmt.executeQuery(); // 결과 받아옴.
			while (rs.next()) { // 몇 행이 올지 모르므로 while
				member = new MemberVO(); // memberVO 객체 초기화.
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setAddress(rs.getString("address"));
				member.setTel(rs.getString("tel"));
				member.setAge(rs.getInt("age"));
				member.setAuthor(rs.getString("author"));
				members.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		// TODO 회원 조회.
		String sql = "select * from member where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();
			if (rs.next()) { // 한 행만 불러오므로 if
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setAge(rs.getInt("age"));
				vo.setAuthor(rs.getString("author"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public MemberVO loginCheck(MemberVO vo) {
		// TODO 로그인 과정.
		String sql = "select * from member where id = ? and password = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPassword());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setName(rs.getString("name"));
				vo.setAuthor(rs.getString("author"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int memberInsert(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		// TODO 멤버 삭제
		int n = 0;
		String sql = "delete from member where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// TODO 멤버 주소, 전화번호만 수정 가능.
		int n = 0;
		String sql = "update member set address = ?, tel = ? where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getAddress());
			psmt.setString(2, vo.getTel());
			psmt.setString(3, vo.getId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

}
