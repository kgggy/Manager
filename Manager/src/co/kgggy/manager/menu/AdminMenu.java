package co.kgggy.manager.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.kgggy.manager.board.service.BoardService;
import co.kgggy.manager.board.serviceImpl.BoardServiceImpl;
import co.kgggy.manager.board.vo.BoardVO;
import co.kgggy.manager.member.service.MemberService;
import co.kgggy.manager.member.serviceImpl.MemberServiceImpl;
import co.kgggy.manager.member.vo.MemberVO;

public class AdminMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberService memDao = new MemberServiceImpl();
	private BoardService boDao = new BoardServiceImpl();

	private void title() {
		System.out.println("====== 관리자 메뉴 =======");
		System.out.println("----- 1.사용자 관리 ------");
		System.out.println("----- 2.게시판 관리 ------");
		System.out.println("--------- 3.종료 --------");
		System.out.println("========================");
		System.out.println("원하는 작업을 선택하세요>");
	}

	private void subMemberTitle() {
		System.out.println("====== 사용자 관리 =======");
		System.out.println("---- 1.멤버 정보 목록 -----");
		System.out.println("---- 2.멤버 정보 조회 -----");
		System.out.println("---- 3.멤버 정보 삭제 -----");
		System.out.println("------- 4.돌아가기 -------");
		System.out.println("========================");
		System.out.println("원하는 작업을 선택하세요>");
	}

	private void subBoardTitle() {
		System.out.println("====== 게시판 관리 =======");
		System.out.println("------- 1.글 목록 -------");
		System.out.println("------- 2.글 쓰기 -------");
		System.out.println("------- 3.글 수정 -------");
		System.out.println("------- 4.글 삭제 -------");
		System.out.println("------- 5.돌아가기 -------");
		System.out.println("========================");
		System.out.println("원하는 작업을 선택하세요>");
	}

	private void adminMenu(MemberVO vo) {
		System.out.println(vo.getName() + "관리자님 환영합니다.");
		boolean b = false;
		do {
			title(); // 주메뉴 출력.
			int choice;
			switch (choice = sc.nextInt()) {
			case 1:
				userManager();
				break;
			case 2:
				boardManager();
				break;
			case 3:
				b = true;
				System.out.println("작업을 종료합니다.");
				break;
			}
		} while (!b); // 참인동안 반복.
	}

	private void userManager() {
		// TODO 사용자 관리 메뉴.
		boolean b = false;
		do {
			int chk;
			subMemberTitle(); // 사용자 메뉴 호출
			switch (chk = sc.nextInt()) {
			case 1:
				memberSelectList(); // 멤버목록 조회
				break;
			case 2:
				sc.nextLine();
				memberSelect(); // 멤버 조회
				break;
			case 3:
				sc.nextLine();
				memberdelete(); // 멤버 삭제
				break;
			case 4:
				b = true;
				System.out.println("목록으로 돌아갑니다.");
				break;
			}
		} while (!b);
	}

	private void boardManager() {
		// TODO 게시판 관리 메뉴.
		boolean b = false;
		do {
			subBoardTitle(); // 게시판 관리 목록
			int chk;
			switch (chk = sc.nextInt()) {
			case 1:
				boardSelectList(); // 글 목록
				break;
			case 2:
				boardInsert(); // 글 쓰기
				break;
			case 3:
				boardUpdate(); // 글 수정
				break;
			case 4:
				boardDelete(); // 글 삭제
				break;
			case 5:
				b = true;
				System.out.println("목록으로 돌아갑니다.");
				break;
			}

		} while (!b);
	}

	private void boardDelete() {
		// TODO 글 삭제
		BoardVO vo = new BoardVO();
		System.out.println("검색할 아이디를 입력하세요>");
		vo.setBoardId(sc.next());
		int n = boDao.boardDelete(vo);
		if( n != 0) {
			System.out.println("정상적으로 삭제되었습니다.");
		} else {
			System.out.println("삭제 실패하였습니다.");
		}
	}

	private void boardUpdate() {
		// TODO 글 수정(내용만)
		BoardVO vo = new BoardVO();
		System.out.println("검색할 아이디를 입력하세요>");
		vo.setBoardId(sc.next());sc.nextLine();
		System.out.println("수정할 내용을 입력하세요>");
		vo.setSubject(sc.nextLine());
		int n = boDao.boardUpdate(vo);
		if(n != 0) {
			System.out.println("정상적으로 수정되었습니다.");
		}else {
			System.out.println("수정 실패하였습니다.");
		}
	}

	private void boardInsert() {
		// TODO 글 쓰기
		BoardVO vo = new BoardVO();
		System.out.println("게시판 아이디를 입력하세요>");
		vo.setBoardId(sc.next());sc.nextLine();
		System.out.println("제목을 입력하세요>");
		vo.setTitle(sc.nextLine()); //nextLine() => 블랭크를 포함해서 받음(엔터칠때까지) // next()
		System.out.println("내용을 입력하세요>");
		vo.setSubject(sc.nextLine());
		System.out.println("작성자를 입력하세요>");
		vo.setWriter(sc.next());sc.nextLine();
		
		int n = boDao.boardInsert(vo);
		if(n != 0) {
			System.out.println("정상적으로 입력되었습니다.");
		}else
			System.out.println("입력이 실패하였습니다.");
	}

	private void boardSelectList() {
		// TODO 글 목록 조회
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = boDao.boardSelectList();
		for(BoardVO vo : list) {
			vo.toString();
		}
	}

	private void memberdelete() {
		// TODO 멤버 삭제
		MemberVO member = new MemberVO();
		System.out.println("검색할 아이디를 입력하세요>");
		member.setId(sc.nextLine());
		int n = memDao.memberDelete(member);
		if (n != 0) {
			System.out.println("정상적으로 삭제되었습니다.");
		} else {
			System.out.println("삭제되지 않았습니다..");
		}

	}

	private void memberSelect() {
		// TODO 멤버 정보 조회
		MemberVO member = new MemberVO();
		System.out.println("검색할 아이디를 입력하세요>");
		member.setId(sc.nextLine());
		member = memDao.memberSelect(member);

		member.toString(); // 한 행이므로 for 반복 할 필요 없음.
	}

	private void memberSelectList() {
		// TODO 멤버 목록 조회
		System.out.println(" <<< 사용자 목록 >>>");
		List<MemberVO> members = new ArrayList<MemberVO>();
		members = memDao.memberSelectList(); // list타입으로 돌려줌 => 받을 그릇 만들어줘야함.
		for (MemberVO member : members) { // MemberVO 타입의 members에서 한 행을 member로 읽겠다.
			member.toString();
		}
	}

	public void run(MemberVO vo) {
		adminMenu(vo);
	}
}
