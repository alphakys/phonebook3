package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PhoneVo;

@Controller
@RequestMapping(value="/phone")
public class PhoneController {
	
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		
		System.out.println("write");
		
		
		return "/WEB-INF/views/writeForm.jsp";
	}
	
	
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("list");
		
		PhoneDao pd = new PhoneDao();
		List<PhoneVo> list = pd.getPhList();
		
		model.addAttribute("pList", list);
		
		
		return "/WEB-INF/views/list.jsp";
		
	}
	
	
	@RequestMapping(value="/insert", method={RequestMethod.GET, RequestMethod.POST})
	public String insert(@RequestParam ("name") String name, 
						 @RequestParam ("hp")	String hp,
						 @RequestParam ("company")	String company) {
		
		PhoneDao pd = new PhoneDao();
		pd.insert(new PhoneVo(name, hp, company));
		
		
		return "redirect:/phone/list";
	}

	
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam ("id") int id) {
		
		PhoneDao pd = new PhoneDao();
		pd.delete(id);
		
		
		return "redirect:/phone/list";
	}
	
	@RequestMapping(value="/updateForm", method={RequestMethod.GET, RequestMethod.POST})
	public String updateForm(@RequestParam("id") int id, Model model) {
		
		PhoneDao pd = new PhoneDao();
		PhoneVo pv = pd.getPerson(id);
		
		model.addAttribute("updatePerson", pv);
		
		return "/WEB-INF/views/updateForm.jsp";
	}
	
	
	@RequestMapping(value="/update", method={RequestMethod.GET, RequestMethod.POST})
	public String update(@RequestParam("name") String name,
						 @RequestParam("hp") String hp,
						 @RequestParam("company") String company,
						 @RequestParam("id") int id) {
		
		PhoneDao pd = new PhoneDao();
		pd.update(new PhoneVo(id, name, hp, company));
				
		return "redirect:/phone/list";
					
	}
	
	
	
}
