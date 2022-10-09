package pillihuaman.com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController2 {	@RequestMapping({ "/" })
	public String firstPage() {
		return "Hello World";
	}}