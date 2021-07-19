package co.kgggy.manager.menu;

import java.util.Scanner;

import co.kgggy.manager.member.service.MemberService;
import co.kgggy.manager.member.serviceImpl.MemberServiceImpl;
import co.kgggy.manager.member.vo.MemberVO;

public class LoginMenu {
	private MemberService memberDao = new MemberServiceImpl();
	private Scanner sc = new Scanner(System.in);

	// 사용자 메뉴객체 생성
	private UserMenu user = new UserMenu();
	// 관리자 메뉴객체 생성
	private AdminMenu admin = new AdminMenu();

	private void loginTitle() {
		System.out.println("===================");
		System.out.println("===== 로 그 인 =====");
		System.out.println("===================");
	}

	private MemberVO loginCheck() {
		MemberVO vo = new MemberVO();
		boolean b = false;
		int loginCount = 1;
		do {
			loginTitle(); // 타이틀 출력.
			System.out.println("아이디를 입력하세요.");
			vo.setId(sc.next());
			sc.nextLine();
			System.out.println("패스워드를 입력하세요.");
			vo.setPassword(sc.next());
			sc.nextLine();

			vo = memberDao.loginCheck(vo);
			if (vo.getName() != null) {
				// System.out.println(vo.getName() + "님, 환영합니다.");
				b = true;
			} else {
				System.out.println("존재하지 않는 아이디 또는 잘못된 패스워드 입니다.");
				if (loginCount == 3) {
					b = true;
					System.out.println(loginCount + "회 이상 실패하였습니다. 관리자에게 문의하세요.");
					vo = new MemberVO(); // 시스템 종료하기 위한 구문(vo 초기화 => null값)
				} else {
					loginCount++;
				}
			}
		} while (!b);
		return vo;
	}

	public void login() {
		MemberVO vo = new MemberVO();
		vo = loginCheck();
		if (vo.getId() != null) {
			// 여기서 주메뉴 호출하면 됨.
			if (vo.getAuthor().equals("ADMIN")) {
				admin.run(vo); // admin객체의 adminMenu 호출.
			} else {
				user.run(vo);
			}
		}
		sc.close();
	}
}
