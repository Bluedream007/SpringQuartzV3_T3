package com.bluedream.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class HelloWorldController {
	
	private String strConfirmPwd = "MyWebsite@Ctrl%01";
	
	@RequestMapping(value = "/sayHello", method = RequestMethod.GET)
	public ModelAndView dashboard() {
	    	ModelAndView model = new ModelAndView();
	    	// model.addObject("users", getUsers());
	    	model.setViewName("SayHello");
	    	return model;
	}
 
	@RequestMapping(value = "hello")
	public String showForm(Model model) {
		if (schedulerFactory.isRunning())
		{
			model.addAttribute("msg", " job started");
		}
		else
		{
			model.addAttribute("msg", "job stopped");
		}
		return "hello";
	}
 
	/*
	 * Declaring schedulerFactory represent for org.springframework.scheduling.quartz.SchedulerFactoryBean class 
	 * that it is configured in the servlet-quartz.xml file. Through an schedulerFactory to manage 
	 * starting or stopping the job on screen page.
	 */
	@Autowired
	private SchedulerFactoryBean schedulerFactory;
	
	@RequestMapping(value = "hello", method = RequestMethod.POST)
	public String runJob(Model model) {
 
		if (schedulerFactory.isRunning())
		{
			schedulerFactory.stop();
			model.addAttribute("msg", "job stopped");
		}
		else
		{
			schedulerFactory.start();
			model.addAttribute("msg", "job started");
		}
		return "hello";
	}
 
}
