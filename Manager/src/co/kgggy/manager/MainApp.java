package co.kgggy.manager;

import co.kgggy.manager.menu.LoginMenu;

public class MainApp {

	public static void main(String[] args) {
		// TODO 전체목록 동작 확인용.
//		MemberService memberDao = new MemberServiceImpl();
//		List<MemberVO> members = new ArrayList<MemberVO>();
//		
//		members = memberDao.memberSelectList();
//		for(MemberVO vo : members) {
//			vo.toString();
//		}
		
		LoginMenu menu = new LoginMenu();
		menu.login();
		
	}
}
