package dsmt.control.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dsmt.model.entities.Account;
import dsmt.model.entities.Role;
import dsmt.model.services.AccountService;
import dsmt.model.services.RoleService;
import dsmt.model.utils.SendMail;
import dsmt.util.HTMLUtil;
import dsmt.util.HTMLUtil.BGR;
import dsmt.util.Random;

@Controller
@CrossOrigin("*")
@RequestMapping("/security")
public class SecurityControl {

	private final String BACK_HOME = "/pages/home";
	private String general = Random.UpperCase("DSMT", 10);
	private String email = null;
	
	// @formatter:on
	private @Autowired HttpServletRequest req;
	private @Autowired AccountService dao; // CRUD account
	private @Autowired RoleService rDao; // CRUD account
	private @Autowired SendMail mail;
	
	@RequestMapping({"/deniedPage"})
	public String denied() {
		return "/pages/denied";
	}
	
	@GetMapping("/{page}") public String pageURI() {
		return new StringBuilder("/pages").append(req.getRequestURI()).toString();
	}
	
	// ___________________________________________________________ LOGIN
	@GetMapping("/loginForm") public String loginForm() {
		return pageURI(); // append "/pages" before URI
	}
	
	@GetMapping("/loginSuccess") public String loginSuccess() {
		Principal principal = req.getUserPrincipal();
		String title = "????ng nh???p t??i kho???n th??nh c??ng";
		String message = principal.getName()+" ???? ????ng nh???p th??nh c??ng";
		req.setAttribute("message", HTMLUtil.alert(BGR.SUCCESS, title, message, 3000));
		req.setAttribute("user", principal);
		
		return BACK_HOME; // back home
	}

	@PostMapping("/loginFailed") public String loginFailed() {
		String title = "????ng nh???p t??i kho???n th???t b???i";
		String message = "T??n ????ng nh???p ho???c m???t kh???u kh??ng ????ng!";
		req.setAttribute("message", HTMLUtil.alert(BGR.WARN, title, message, 3000));

		return "/pages/security/loginForm"; // return page
	}
	
	// ___________________________________________________________ LOGOUT
	@GetMapping("/logoutForm") public String logoutForm() {
		String title = "Chuy???n trang ????ng xu???t";
		String body = "X??c nh???n b?????c ti???p ????? ????ng xu???t t??i kho???n";
		req.setAttribute("message", HTMLUtil.alert(BGR.WARN, title, body, 1000));
		return pageURI();
	}
	
	@GetMapping("/logoutSuccess") public String logoutSuccess() {
		String title = "????ng xu???t t??i kho???n";
		String message = "???? ????ng xu???t t??i kho???n";
		req.setAttribute("message", HTMLUtil.alert(BGR.LIGHT, title, message, 3000));
		return BACK_HOME;
	}
	
	// _GET -> FORM _____________POST -> SAVE ____________________ SIGN-UP
	@GetMapping("/register")
	public String register(Model model, @ModelAttribute Account account) {
		account.setRoles(new HashSet<>());
		model.addAttribute("account", account);
		model.addAttribute("roles", getRoles());
		return pageURI(); // get register form
	}
	
	@PostMapping("/register")
	public String register(Model model, Account account, Errors errors) {
		String message, title = "????ng k?? t??i kho???n";
		if(!errors.hasErrors())
			try {
				if(dao.getByEmail(account.getEmail()) != null) {
					throw new Exception(account.getEmail()+" ???? ???????c s??? d???ng, vui l??ng ch???n email kh??c");
				}
				Account a = dao.save(account);
				if(a != null) {
					message = "????ng k?? t??i kho???n "+a.getUsername()+" th??nh c??ng";
					model.addAttribute("message", HTMLUtil.alert(BGR.SUCCESS, title, message, 3000));
					return "/pages/security/loginForm"; // load login page
				} 
				message = "????ng k?? t??i kho???n th???t b???i!";
				model.addAttribute("message", HTMLUtil.alert(BGR.DANGER, title, message, 5000));
			} catch (Exception e) {
				model.addAttribute("message", HTMLUtil.alert(BGR.WARN, title, e.getMessage(), 3000));
			}
		model.addAttribute("roles", getRoles());
		return pageURI(); // callback to re-register input data
	}
	

	// ___________________________________________________________ FORGOT - PASSWORD
	@ResponseBody @RequestMapping("/getCode")
	public ResponseEntity<String> getCodeToMail(@RequestParam String address, HttpServletRequest req) throws Exception {
		String subject = "Y??u c???u m?? x??c th???c t??i kho???n!";
		String text = HTMLUtil.getCode("DSMT g???i m?? x??c th???c t??i kho???n", this.general = Random.UpperCase("DSMT", 10), "nhomTTXBS@gmail.com");

		if(dao.getByEmail(address)==null) throw new Exception(address + " ch??a ???????c ????ng k?? th??ng tin, vui l??ng ki???m tra l???i!");
		try {
			System.out.println("Send mail code: "+this.general);
			mail.sendMimeMessage(subject, text, null, null, this.email=address);
			return ResponseEntity.ok("[\"DSMT ???? g???i m?? x??c th???c g???m "+this.general.length()+" k?? t??? t???i email: "+address+" vui l??ng ki???m tra h???p th??.\"]");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping("/getPassCode") 
	public String getPassCode(Model model, @RequestParam(required = false) String code) throws Exception {
		if(code.equals(this.general)) {
			Account a = dao.getByEmail(this.email);
			if(a==null) throw new Exception(this.email+" t??i kho???n kh??ng t???n t???i!");
			model.addAttribute("account", new Account(a.getUsername(), a.getPassword()));
			return "/pages/security/change_password";
		} else req.setAttribute("message", HTMLUtil.alert(BGR.LIGHT,
				"X??c th???c t??i kho???n","X??c th???c th???t b???i, "+code+" kh??ng ????ng!", 3000)
				);
		return "/pages/security/forgot_password";
	}

	// ___________________________________________________________ CHANGE PASSWORD
	@GetMapping("/change_password") public String changePassword(Model model, @ModelAttribute Account account) {
		Principal principal = req.getUserPrincipal();
		Optional<Account> optional = dao.getOptional(principal.getName()); 
		if(optional.isPresent()) {
			Account a = optional.get();
			account.setUsername(a.getUsername());
			account.setPassword(a.getPassword());			
		} model.addAttribute("account", account);
		return this.pageURI();
	}
	
	@PostMapping("/change_password") public String changePassword(@ModelAttribute Account account,
			@RequestParam String newPass, @RequestParam String rePass) {
		String title = "Thay ?????i m???t kh???u";
		String message = "Nh???p l???i m???t kh???u kh??ng ????ng!";
		
		if(!rePass.equals(newPass)) req.setAttribute("message", HTMLUtil.alert(BGR.WARN, title, message, 3000));
		else {
			try {
				Optional<Account> optional = dao.getOptional(account.getUsername());
				if(optional.isPresent()) {
					if(account.getPassword().equals(optional.get().getPassword())) {
						message = "Thay ?????i m???t kh???u th??nh c??ng";
						req.setAttribute("message", HTMLUtil.alert(BGR.SUCCESS, title, message, 3000));
						account = optional.get();
						account.setPassword(newPass);
						dao.update(account);
						return BACK_HOME;
					}
				} req.setAttribute("message", HTMLUtil.alert(BGR.WHITE, title, "T??i kho???n ho???c m???t kh???u kh??ng ????ng", 3000));
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("message", HTMLUtil.alert(BGR.WHITE, title, e.getMessage(), 3000));
			}
		} return this.pageURI();
	}
	
	// ___________________________________________________________ UPDATE INFO
	@GetMapping("/about_me") public String getAboutMe(Model model) {
		Principal p = req.getUserPrincipal();
		Optional<Account> optional = dao.getOptional(p.getName());
		model.addAttribute("account", optional.isEmpty() ? new Account() : optional.get());
		return this.pageURI();
	}
	
	@PostMapping("/about_me") public String saveAboutMe(Model model, Account account, Errors errors) {
		String message, title = "C???p nh???t th??ng tin";
		
		if(!errors.hasErrors())
			try {
				
				// update account
				Account a = dao.update(account);
				if(a != null) {
					message = "C???p nh???t t??i kho???n "+a.getUsername()+" th??nh c??ng";
					model.addAttribute("message", HTMLUtil.alert(BGR.SUCCESS, title, message, 3000));
				} else {
					message = "C???p nh???t th??ng tin c?? nh??n th???t b???i!";
					model.addAttribute("message", HTMLUtil.alert(BGR.WARN, title, message, 3000));
				}
			} catch (Exception e) {
				model.addAttribute("message", HTMLUtil.alert(BGR.DANGER, title, e.getMessage(), 5000));
			}
		
		return this.pageURI(); // callback to about_me page
	}
	
	private List<Role> getRoles() {
		List<Role> list = rDao.getList();
		for(int i = 0; i < list.size(); i++) {
			String id = list.get(i).getId();
			if(id.equalsIgnoreCase("ADMIN") || id.equalsIgnoreCase("OWNER")) list.remove(i--);
		} return list;
	}
	
	// @formatter:off
}
