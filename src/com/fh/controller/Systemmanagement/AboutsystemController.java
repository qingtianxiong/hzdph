package com.fh.controller.Systemmanagement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.util.Const;
import com.fh.util.PageData;

/** 
 * 绫诲悕绉帮細AboutsystemController
 * 鍒涘缓浜猴細FH 
 * 鍒涘缓鏃堕棿锛�2016-01-27
 */
@Controller
@RequestMapping(value="/Aboutsystem")
public class AboutsystemController extends BaseController {
	
	String menuUrl = "Aboutsystem/list.do"; //鑿滃崟鍦板潃(鏉冮檺鐢�)
	
	/**
	 * 鍒楄〃
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "鍒楄〃Aboutsystem");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //鏍￠獙鏉冮檺
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			
			
			mv.setViewName("Systemmanagement/Aboutsystem_list");
			
			
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//鎸夐挳鏉冮檺
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	/* ===============================鏉冮檺================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro绠＄悊鐨剆ession
		Session session = currentUser.getSession();
		Map<String, String> result =(Map<String, String>) session.getAttribute(Const.SESSION_QX);
		return result;
	}
	/* ===============================鏉冮檺================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
