package com.kh.springdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.springdb.model.UserModel;
import com.kh.springdb.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//전체 아이디 가지고 오기 위해서 GetMapping 사용
	@GetMapping("/users-info")
	public String getAllUsers(Model model){
		List<UserModel> users = userService.getAllUsers();
		model.addAttribute("users",users);
		
		return "users-info"; //return 템플릿명
	}
	
	/*하나의 아이디 가지고 오기
	@GetMapping("/user-info/{id}")
	public String getUserById(@PathVariable int id, Model model) {
		UserModel user = userService.getUserById(id);
		model.addAttribute("user", user);
		
		return "user-info";
	}*/
	
	@GetMapping("/register")
	public String signUp(Model model) {
		model.addAttribute("user", new UserModel()); //저장
		return "register";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginForm", new UserModel()); //왠지 login페이지로 이동은 된다
		return "login";
	}
	
	@GetMapping("/registerSuccess")
	public String getRegisterSuccess() {
		return "registerSuccess";
	}
	
	@PostMapping("/api/user/register")
	public String registerMember(@ModelAttribute("user") @Validated UserModel user, BindingResult result) {
		userService.registerUser(user);
		//user가 회원가입을 성공할 경우, 이동하는 경로
		return "redirect:/registerSuccess";
	}
	
	
	//id에 해당하는 Update문을 사용해서 수정하기
	@GetMapping("/user-update/{id}") //업데이트 들어가기.. (사용자 정보를 가져와서 모델에 추가)
	public String updateUserForm(@PathVariable int id, Model model) {
		//사용자 정보를 가져와서 모델에 추가해줘야 하기 때문에
		//사용자 정보를 가지고오는 모델 추가 코드를 작성
		UserModel user = userService.getUserById(id);
		model.addAttribute("user",user);
		return "user-update";
	}
	
	@PostMapping("/api/user/update/{id}")
	public String updateUser(@PathVariable int id, @ModelAttribute("user") @Validated UserModel user, BindingResult result) {
		
		//결과에 에러 유무를 추가하는 if
		if(result.hasErrors()) {
			return "에러발견";
		} else {
			user.setMno(id); //경로에서 받은 id를 이용해서 사용자 정보를 업데이트
			userService.updateUser(user);
			
			return "redirect:/user-info";
		}
	}
	
	//GET과 POST를 한번에> RequestmMpping 사용해서 get고ㅘpost한 번에너리
	
	@RequestMapping(value="/deleteUser/{id}", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return "redirect:/user-info"; //다른 주소값으로 가지 않고 페이지 내에서 삭제하기
	}
	
} //class UserController

/*ㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔㅑㅐㅔ
	@PathVariable : 
	
	@ModelAttribute("값")
		Thymeleaf 뷰에서 설정한 값의 이름을 사용해서 모델 속성에 엑세스 가능
		**엑세스(access) : 컴퓨터 데이터 또는 리소스를 어떤 방식으로든 사용할 수 있도록 권한을 주거나 권한이 담겨진 것을 의미
						> @ModelAttribute("user") : user라는 이름으로 Model에 User 객체를 추가한 것
	
	@Validatesd
		데이터 유효성 검사를 실시하도록 행하는 어노테이션
		> @Validated(user) : UserModel 객체에 대한 데이터 유효성 검사를 실시하겠슴당

	BindingResult
		@Validated에서 실시한 유효성 검사 결과를 저장하는 객체로, 유효성 검사에서 발생한 오류에 대한 정보가 담긴다

	@RequestMapping
		사용자의 http 요청을 특정 메서드와 할 수 있도록 매핑하는(감싸주는) 역할
		value="" : 사용자가 요청할 때 주고받는 url
		ex) value="/user-delete/{id}" 값이 있을 때, {id}는 PathVariable(경로변수)로
			실제 요청할 경우 해당 url 위치로 들어오는 값을 변수로 활용 가능
			
		method="" : 메서드에서 처리할 http 요청을 메서드에 지정
		예를 들어 RequestMethod.GET, RequestMethod.POST 사용 가능
		
		RequestMethod.GET
			http GET 메서드는 주로 DB에 정보를 요청하기 위해 사용
			사이트에서 URL을 통한 직접 요청이나 링크를 클릭해야하는 상황에서 GET 메서드가 사용됨
			데이터를 서버로 전송하지는 않고, 주로 데이터를 요청하거나 조회할 때만 사용할 수 있음
		RequestMethod.POST
			주로 서버로 데이터를 제출하기 위해 사용
			데이터를 html 본문에 담아 전송하기 때문에 GET보다는 대량의 데이터를 처리하기에 적합함
*/