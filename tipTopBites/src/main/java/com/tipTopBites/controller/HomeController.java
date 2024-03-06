package com.tipTopBites.controller;

import com.tipTopBites.domain.security.*;
import com.tipTopBites.securityConfiguration.MailConstructor;
import com.tipTopBites.securityConfiguration.SecurityUtility;
import com.tipTopBites.securityConfiguration.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.*;

@Controller
public class HomeController<JavaMailSender> {
    @SuppressWarnings("serial")
	final static Map<String, String> RADIO_ITEMS =
            Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
                {
                    put("None", "None");
                    put("Gluten", "Gluten");
                    put("Egg", "Egg");
                    put("Milk", "Milk");
                    put("Peanuts", "Peanuts");
                    put("Fish", "Fish");
                    put("Soy", "Soy");
                }
            });

    @SuppressWarnings("unused")
	@Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private FoodService foodService;
    
    @Autowired
    private UserPaymentService userPaymentService;
    
    @Autowired
    private UserDeliveryService userDeliveryService;    

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);
        return "myAccount";
    }
    
    @GetMapping("/myProfile")
    public String myProfile(Model model, Principal principal) {
    	User user = userService.findByUsername(principal.getName());
    	model.addAttribute("user", user);
    	
    	model.addAttribute("userPaymentList", user.getUserPaymentList());
    	 model.addAttribute("userDeliverytList", user.getUserDeliveryList());
    	/*  model.addAttribute("orderList", user.getOrderList()); */
    	   
    	
    	 UserDelivery userDelivery = new UserDelivery();
    	model.addAttribute("userDelivery", userDelivery); 
    	
    	model.addAttribute("listOfCreditCards", true);
    	model.addAttribute("listOfDeliveryAddresses", true);
    	
    	
    	
    	model.addAttribute("classActiveEdit", true);
    	
    	return "myProfile";
    }
    
    @GetMapping("/listOfCreditCards")
    public String listOfCreditCards(
    		Model model, Principal principal, HttpServletRequest request
    		) {
    	User user = userService.findByUsername(principal.getName());
    	model.addAttribute("user", user);
    	model.addAttribute("userPaymentList", user.getUserPaymentList());
    	model.addAttribute("userDeliveryList", user.getUserDeliveryList());
    /*	model.addAttribute("ordertList", user.orderList()); */
    	
    	
    	model.addAttribute("listOfCreditCards", true);
    	model.addAttribute("classActiveBilling", true);
    	model.addAttribute("listOfDeliveryAddresses", true);
    	return "myProfile";
    	
    }
    
    
    @GetMapping("/listOfDeliveryAddresses")
    public String listOfDeliveryAddresses(
    		Model model, Principal principal, HttpServletRequest request
    		) {
    	User user = userService.findByUsername(principal.getName());
    	model.addAttribute("user", user);
    	model.addAttribute("userPaymentList", user.getUserPaymentList());
    	model.addAttribute("userDeliveryList", user.getUserDeliveryList());
    /*	model.addAttribute("ordertList", user.orderList()); */
    	
    	model.addAttribute("listOfCreditCards", true);
    	model.addAttribute("classActiveDelivery", true);
    	model.addAttribute("listOfDeliveryAddresses", true);
    	
    	return "myProfile";
    	
    }
    
    @GetMapping("/addNewCreditCard")
    public String addNewCreditCard(
    		Model model, Principal principal
    		) {
    	User user = userService.findByUsername(principal.getName());
    	model.addAttribute("user", user);
    	model.addAttribute("addNewCreditCard", true);
    	model.addAttribute("listOfCreditCards", true);

    	model.addAttribute("classActiveBilling", true);
    	model.addAttribute("listOfDeliveryAddresses", true);
    	
    	UserBilling userBilling = new UserBilling();
    	UserPayment userPayment = new UserPayment();
    	
    	model.addAttribute("userBilling", userBilling);
    	model.addAttribute("userPayment", userPayment);
    	
    	
    	
    	
    	model.addAttribute("userPaymentList", user.getUserPaymentList());
    	model.addAttribute("userDeliveryList", user.getUserDeliveryList());
    /*	model.addAttribute("ordertList", user.orderList()); */
    	
    	return "myProfile";
    	
    }
    
    @PostMapping("/addNewCreditCard")
    public String addNewCreditCard(
    		@ModelAttribute("userPayment") UserPayment userPayment,
    		@ModelAttribute("userBilling") UserBilling userBilling,
    		Principal principal, Model model
    		
    		) {
    	User user = userService.findByUsername(principal.getName());
    	userService.updateUserBilling(userBilling, userPayment, user);
    	
    	model.addAttribute("user", user);
    	model.addAttribute("userPaymentList", user.getUserPaymentList());
    	model.addAttribute("userDeliveryList", user.getUserDeliveryList());
    	model.addAttribute("listOfCreditCards", true);
    	model.addAttribute("classActiveBilling", true);
    	model.addAttribute("listOfDeliveryAddresses", true);
    	
    	return "myProfile";
    }
    
    @PostMapping("/addNewDeliveryAddress")
    public String addNewDeliveryAddrssPost(
    		@ModelAttribute("userDelivery") UserDelivery userDelivery,
    		Principal principal, Model model
    		
    		) {
    	User user = userService.findByUsername(principal.getName());
    	userService.updateUserDelivery(userDelivery, user);
    	
    	model.addAttribute("user", user);
    	model.addAttribute("userPaymentList", user.getUserPaymentList());
    	model.addAttribute("userDeliveryList", user.getUserDeliveryList());
    	model.addAttribute("listOfDeliveryAddresses", true);
    	model.addAttribute("classActiveDelivery", true);
    	model.addAttribute("listOfCreditCards", true);
    	
    	return "myProfile";
    }
    
    
    
    @GetMapping("/addNewDeliveryAddress")
    public String addNewDeliveryAddress(
    		Model model, Principal principal
    		) {
    	User user = userService.findByUsername(principal.getName());
    	model.addAttribute("user", user);
    	model.addAttribute("addNewDeliveryAddress", true);
    	model.addAttribute("classActiveDelivery", true);
    	model.addAttribute("listOfCreditCards", true);

    	
    	UserDelivery userDelivery = new UserDelivery();
    	
    	
    	model.addAttribute("userDelivery", userDelivery);
    	
    	
    	
    	
    	model.addAttribute("userPaymentList", user.getUserPaymentList());
    	model.addAttribute("userDeliveryList", user.getUserDeliveryList());
    /*	model.addAttribute("ordertList", user.orderList()); */
    	
    	return "myProfile";
    	
    }
    
    @PutMapping("/updateCreditCard")
    public String updateCreditCard(
    		@ModelAttribute("id") Long creditCardId, Principal principal, Model model
    		) {
    	User user = userService.findByUsername(principal.getName());
    	UserPayment userPayment = userPaymentService.findById(creditCardId);
    	
    	if(user.getId() != userPayment.getUser().getId()) {
    		return "badRequestPage";
    	}else {
    		model.addAttribute("user", user);
    		UserBilling userBilling = userPayment.getUserBilling();
    		model.addAttribute("userPayment", userPayment);
    		model.addAttribute("userBilling", userBilling);
    		
    		
    		
    		model.addAttribute("addNewCreditCard", true);
    		model.addAttribute("classActiveBilling", true);
    		model.addAttribute("listOfDeliveryAddresses", true);
    		
    		model.addAttribute("userPaymentList", user.getUserPaymentList());
    		model.addAttribute("userDeliveryList", user.getUserDeliveryList());
    		
    		return "myProfile";
    		
    	}
    	
    	
    }
    
    @PutMapping("/updateUserDelivery")
    public String updateUserDelivery(
    		@ModelAttribute("id") Long deliveryAddressId, Principal principal, Model model
    		) {
    	User user = userService.findByUsername(principal.getName());
    	UserDelivery userDelivery = userDeliveryService.findById(deliveryAddressId);
    	
    	if(user.getId() != userDelivery.getUser().getId()) {
    		return "badRequestPage";
    	}else {
    		model.addAttribute("user", user);
    		model.addAttribute("userDelivery", userDelivery);
    		
    		
    		model.addAttribute("addNewDeliveryAddress", true);
    		model.addAttribute("classActiveDelivery", true);
    		model.addAttribute("listOfCreditCards", true);
    		
    		model.addAttribute("userPaymentList", user.getUserPaymentList());
    		model.addAttribute("userDeliveryList", user.getUserDeliveryList());
    		
    		return "myProfile";
    		
    	}
    	
    	
    }
    
    @PostMapping("setDefaultPayment")
    public String setDefaultPayment(
    		@ModelAttribute("defaultUserPaymentId") Long defaultPaymentId, Principal principal, Model model
    		) {
    	User user = userService.findByUsername(principal.getName());
    	userService.setUserDefaultPayment(defaultPaymentId, user);
    	
    	
    	model.addAttribute("user", user);

    	model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfDeliveryAddresses", true);
		
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userDeliveryList", user.getUserDeliveryList());
		
		return "myProfile";

    	
    }
    
    @PostMapping("setDefaultDeliveryAddress")
    public String setDefaultDeliveryAddress(
    		@ModelAttribute("defaultDeliveryAddressId") Long defaultDeliveryId, Principal principal, Model model
    		) {
    	User user = userService.findByUsername(principal.getName());
    	userService.setUserDefaultDelivery(defaultDeliveryId, user);
    	
    	
    	model.addAttribute("user", user);

    	model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveDelivery", true);
		model.addAttribute("listOfDeliveryAddresses", true);
		
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userDeliveryList", user.getUserDeliveryList());
		
		return "myProfile";

    	
    }
    		
    
    
    @DeleteMapping("/removeCreditCard")
    public String romoveCreditCard(
    		@ModelAttribute("id") Long creditCardId, Principal principal, Model model
    		) {
    	User user = userService.findByUsername(principal.getName());
    	UserPayment userPayment = userPaymentService.findById(creditCardId);
    	
    	if(user.getId() != userPayment.getUser().getId()) {
    		return "badRequestPage";
    	}else {
    		model.addAttribute("user", user);
    		userPaymentService.removeById(creditCardId);
    		
    		
    		
    		model.addAttribute("listOfCreditCards", true);
    		model.addAttribute("classActiveBilling", true);
    		model.addAttribute("listOfDeliveryAddresses", true);
    		
    		model.addAttribute("userPaymentList", user.getUserPaymentList());
    		model.addAttribute("userDeliveryList", user.getUserDeliveryList());
    		
    		return "myProfile";
    		
    	}
    	
    	
    }
    
    
    @DeleteMapping("/removeUserDelivery")
    public String removeUserDelivery(
    		@ModelAttribute("id") Long userDeliveryId, Principal principal, Model model
    		) {
    	User user = userService.findByUsername(principal.getName());
    	UserDelivery userDelivery = userDeliveryService.findById(userDeliveryId);
    	
    	if(user.getId() != userDelivery.getUser().getId()) {
    		return "badRequestPage";
    	}else {
    		model.addAttribute("user", user);

    		userDeliveryService.removeById(userDeliveryId);
    		
    		
    		model.addAttribute("listOfDeliveryAddresses", true);
    		model.addAttribute("classActiveDelivery", true);
    		
    		model.addAttribute("userPaymentList", user.getUserPaymentList());
    		model.addAttribute("userDeliveryList", user.getUserDeliveryList());
    		
    		return "myProfile";
    		
    	}
    	
    }
    
    
    
    
    

    @GetMapping("/menu")
    public String menu(Model model) {
        List<Food> foodList = foodService.findAll();
        model.addAttribute("foodList", foodList);
        return "menu";
    }

    @GetMapping("/calories")
    public String calories(Model model) {
        List<Food> foodList = foodService.findAll();
        model.addAttribute("foodList", foodList);
        model.addAttribute("allergyItems", RADIO_ITEMS);
        model.addAttribute("allergy", "None");
        return "calories";
    }

    @GetMapping("/foodDetail")
    public String foodDetail(
            @PathParam("id") Long id, Model model, Principal principal
    ) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        Food food = foodService.findOne(id);
        model.addAttribute("food", food);
        List<Integer> qtyList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        model.addAttribute("qtyList", qtyList);
        model.addAttribute("qty", 1);
        return "foodDetail";
    }

    @GetMapping("/forgetPassword")
    public String forgetPassword(
            HttpServletRequest request,
            @ModelAttribute("email") String email,
            Model model
    ) {
        model.addAttribute("classActiveForgetPassword", true);
        User user = userService.findByEmail(email);
        if (user == null) {
            model.addAttribute("emailNotExist", true);
            return "myAccount";
        }
        String password = SecurityUtility.randomPassword();
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);
        userService.save(user);
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        @SuppressWarnings("unused")
		SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);
//        mailSender.send(newEmail);
        model.addAttribute("forgetPasswordEmailSent", "true");
        return "myAccount";
    }

    @PostMapping("/newUser")
    public String newUserPost(
            HttpServletRequest request,
            @ModelAttribute("email") String userEmail,
            @ModelAttribute("username") String username,
            Model model
    ) throws Exception {
        model.addAttribute("classActiveNewAccount", true);
        model.addAttribute("email", userEmail);
        model.addAttribute("username", username);
        if (userService.findByUsername(username) != null) {
            model.addAttribute("usernameExists", true);
            return "myAccount";
        }
        if (userService.findByEmail(userEmail) != null) {
            model.addAttribute("emailExists", true);
            return "myAccount";
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(userEmail);
        String password = SecurityUtility.randomPassword();
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);
        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        userService.createUser(user, userRoles);
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        @SuppressWarnings("unused")
		SimpleMailMessage email = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);
//        mailSender.send(email);
        model.addAttribute("emailSent", "true");
        return "myAccount";
    }

    @GetMapping("/newUser")
    public String newUser(Locale locale, @RequestParam("token") String token, Model model) {
        PasswordResetToken passToken = userService.getPasswordResetToken(token);
        if (passToken == null) {
            String message = "Invalid Token.";
            model.addAttribute("message", message);
            return "redirect:/badRequest";
        }
        User user = passToken.getUser();
        String username = user.getUsername();
        UserDetails userDetails = userSecurityService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addAttribute("user", user);
        model.addAttribute("classActiveEdit", true);
        return "myProfile";
    }
}
