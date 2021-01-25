package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PhoneVo;

@Controller
@RequestMapping(value="/phone")
public class PhoneController {
	
	PhoneDao pd;
	
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		
		System.out.println("write");
		
		
		return "writeForm";
	}
	
	
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("list");
		
		 pd = new PhoneDao();
		List<PhoneVo> list = pd.getPhList();
		
		model.addAttribute("pList", list);
		
		
		return "list";
		
	}
	
	
	@RequestMapping(value="/insert", method={RequestMethod.GET, RequestMethod.POST})
	public String insert(@RequestParam ("name") String name, 
						 @RequestParam ("hp")	String hp,
						 @RequestParam ("company")	String company) {
		
		pd = new PhoneDao();
		pd.insert(new PhoneVo(name, hp, company));
		
		
		return "redirect:/phone/list";
	}

	/*
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam ("id") int id) {
		
		PhoneDao pd = new PhoneDao();
		pd.delete(id);
		
		
		return "redirect:/phone/list";
	}
	*/
	
	@RequestMapping(value="/delete/{id}", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable("id") int id) {
		
		pd = new PhoneDao();
		pd.delete(id);
		
		
		return "redirect:/phone/list";
	}
	
	@RequestMapping(value="/updateForm", method={RequestMethod.GET, RequestMethod.POST})
	public String updateForm(@RequestParam("id") int id, Model model) {
		
		pd = new PhoneDao();
		PhoneVo pv = pd.getPerson(id);
		
		model.addAttribute("updatePerson", pv);
		
		return "updateForm";
	}
	
	/*
	@RequestMapping(value="/update", method={RequestMethod.GET, RequestMethod.POST})
	public String update(@RequestParam("name") String name,
						 @RequestParam("hp") String hp,
						 @RequestParam("company") String company,
						 @RequestParam("id") int id) {
		
		PhoneDao pd = new PhoneDao();
		pd.update(new PhoneVo(id, name, hp, company));
				
		return "redirect:/phone/list";
					
	}
	*/
	
	// modelAttribute를 쓰기 위해선 Vo의 setter의 네이밍과 파라미터의 이름이 동일해야한다. 
	@RequestMapping(value="/update", method={RequestMethod.GET, RequestMethod.POST})
	public String update(@ModelAttribute PhoneVo pv) {
		
		pd = new PhoneDao();
		pd.update(pv);
				
		return "redirect:/phone/list";
					
	}
	
}
