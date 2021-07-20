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

public class UserMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberService memDao = new MemberServiceImpl(); // 멤버 정보 수정
	private BoardService boDao = new BoardServiceImpl(); // 게시글

	private void title() {
		System.out.println("=======================");
		System.out.println("====== 사용자 메뉴 =======");
		System.out.println("==== 1.나의 정보 수정 ====");
		System.out.println("==== 2.게시글   보기 ====");
		System.out.println("==== 3.게시글   작성 ====");
		System.out.println("==== 4.게시글   수정 ====");
		System.out.println("==== 5.종       료 ====");
		System.out.println("=======================");
		System.out.println("원하는 작업을 선택하세요>");
	}

	private void userMenu(MemberVO vo) {
		System.out.println(vo.getName() + "사용자님 환영합니다.");
		boolean b = false;
		int chk;
		do {
			title();
			switch (chk = sc.nextInt()) {
			case 1:
				infoUpdate();
				break;
			case 2:
				allList();
				break;
			case 3:
				mineWrite();
				break;
			case 4:
				mineUpdate(vo.getId());
				break;
			case 5:
				b = true;
				System.out.println("작업을 종료합니다.");
				break;
			}
		} while (!b);
	}

	private void mineUpdate(String id) {
		// TODO 게시글 수정
		BoardVO vo = new BoardVO();
		System.out.println("글번호를 입력하세요>");
		String boardId = sc.next();
		sc.nextLine();
		vo.setBoardId(boardId);
		vo = boDao.boardSelect(vo);
		if(vo.getWriter().equals(id)) {
			vo = new BoardVO();
			System.out.println("수정하실 내용을 입력하세요>");
			vo.setSubject(sc.nextLine());
			vo.setWriter(id);
			vo.setBoardId(boardId);
			int n = boDao.boardUpdate(vo);
			if (n != 0) {
				System.out.println("정상적으로 수정되었습니다.");
			} else {
				System.out.println("수정 실패하였습니다.");
			}
		}else {
			System.out.println("본인 글만 수정 가능합니다.");
			vo.toString();
		}

	}

	private void mineWrite() {
		// TODO 게시글 작성
		BoardVO vo = new BoardVO();
		System.out.println("글번호를 입력하세요>");
		vo.setBoardId(sc.next());
		sc.nextLine();
		System.out.println("제목을 입력하세요>");
		vo.setTitle(sc.nextLine());
		System.out.println("내용을 입력하세요>");
		vo.setSubject(sc.nextLine());
		System.out.println("작성자를 입력하세요>");
		vo.setWriter(sc.next());

		int n = boDao.boardInsert(vo);
		if (n != 0) {
			System.out.println("게시글이 작성되었습니다.");
		} else {
			System.out.println("게시글 작성에 실패하였습니다.");
		}
	}

	private void allList() {
		// TODO 게시글 보기
		List<BoardVO> boards = new ArrayList<BoardVO>();
		boards = boDao.boardSelectList();
		System.out.println("<< 전체 게시물 >>");
		for (BoardVO list : boards) { // boards에서 출력.
			list.toString();
		}
	}

	private void infoUpdate() {
		// TODO 나의 정보 수정(주소, 전화번호)
		MemberVO vo = new MemberVO();
		System.out.println("수정할 아이디를 입력하세요>");
		vo.setId(sc.next());
		sc.nextLine();
		System.out.println("주소를 입력하세요>");
		vo.setAddress(sc.nextLine());
		System.out.println("전화번호를 입력하세요>");
		vo.setTel(sc.next());

		int n = memDao.memberUpdate(vo);
		if (n != 0) {
			System.out.println("정상적으로 수정되었습니다.");
		} else {
			System.out.println("수정에 실패하였습니다.");
		}
	}

	public void run(MemberVO vo) {
		userMenu(vo);
	}
	
	
}
