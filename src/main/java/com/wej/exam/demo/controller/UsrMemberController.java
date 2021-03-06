package com.wej.exam.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wej.exam.demo.service.MemberService;
import com.wej.exam.demo.utill.Ut;
import com.wej.exam.demo.vo.Member;
import com.wej.exam.demo.vo.ResultData;
import com.wej.exam.demo.vo.Rq;

@Controller
public class UsrMemberController {

	private MemberService memberService;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	// 액션 메서드 시작
	@RequestMapping("/usr/member/dojoin")
	@ResponseBody
	public Object dojoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {

		if (Ut.empty(loginId)) {
			return ResultData.from("F-1", "loginId(을)를 입력해주세요.");
		}

		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2", "loginPw(을)를 입력해주세요.");
		}

		if (Ut.empty(name)) {
			return ResultData.from("F-3", "name(을)를 입력해주세요.");
		}

		if (Ut.empty(nickname)) {
			return ResultData.from("F-4", "nickname(을)를 입력해주세요.");
		}

		if (Ut.empty(cellphoneNo)) {
			return ResultData.from("F-5", "cellphoneNo(을)를 입력해주세요.");
		}

		if (Ut.empty(email)) {
			return ResultData.from("F-6", "email(을)를 입력해주세요.");
		}

		ResultData joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		if (joinRd.isFail()) {
			return joinRd;
		}

		Member member = memberService.getMemberById((int) joinRd.getData1());

		return ResultData.newData(joinRd, "member", member);
	}

	@RequestMapping("/usr/member/login")
	public String showLogin() {
		return "/usr/member/login";
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest req, String loginId, String loginPw) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		
		if (rq.isLogined()) {
			return Ut.jsHistoryBack("이미 로그인 되었습니다.");

		}

		if (Ut.empty(loginId)) {
			return Ut.jsHistoryBack("loginId(을)를 입력해주세요.");
		}

		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("loginPw(을)를 입력해주세요.");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return Ut.jsHistoryBack("존재하지 않는 로그인 아이디 입니다.");
		}

		if (member.getLoginPw().equals(loginPw) == false) {

			return Ut.jsHistoryBack("비밀번호 오류.");
		}

		rq.login(member);
		
		return Ut.jsReplace(Ut.f("%s님 환영합니다.", member.getNickname()), "/");
	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		
		if (!rq.isLogined()) {
			return Ut.jsHistoryBack("이미 로그아웃 상태입니다.");
		}

		rq.logout();
		

		return Ut.jsReplace("로그아웃 되었습니다.", "/");

	}

}
