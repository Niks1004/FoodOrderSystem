package com.tipTopBites.controller;

import com.tipTopBites.domain.security.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/deliveryCart")
public class DeliveryCartController {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(DeliveryCartController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	DeliveryCartService deliveryCartService;
	
	@Autowired
	private FoodService foodService;
	
	@RequestMapping(value = "/cart")
	public String deliveryCart(Model model, Principal principal) {


		User user = userService.findByUsername(principal.getName());
		DeliveryCart deliveryCart = user.getDeliveryCart(); 

		List<CartItem> cartItemList = cartItemService.findByDeliveryCart(deliveryCart);


		
		deliveryCartService.updateDeliveryCart(deliveryCart);
		
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("deliveryCart", deliveryCart);
		
		return "deliveryCart";
	}
	
	@GetMapping("/addItem")
	public String addItem(
			@ModelAttribute("food") Food food,
			@ModelAttribute("qty") String qty,
			Model model, Principal principal
			) {
		User user = userService.findByUsername((String) principal.getName());
		food = foodService.findOne(food.getId());
		
		if(Integer.parseInt(qty) > 0) {
			model.addAttribute("not available", true);
			return "forward:/foodDetail?id="+food.getId();
			
		}
		
		@SuppressWarnings("unused")
		CartItem cartItem = cartItemService.addFoodToCartItem(food, user, Integer.parseInt(qty));
		model.addAttribute("addFoodSuccess", true);
		
		return "forward:/foodDetail?id="+food.getId();
		
	}
	
	

}
