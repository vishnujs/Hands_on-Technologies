package com.delibot.feedbackservice.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * <pre>
 *  Controller to redirect request to swagger-ui page
 * </pre>
 */
@Controller
public class SwaggerUIController {

	@RequestMapping(value = "/")
	public String redirect() {
		return "redirect:swagger-ui/#/";
	}
}
