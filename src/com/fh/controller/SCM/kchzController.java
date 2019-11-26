package com.fh.controller.SCM;
 
	import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import com.fh.service.SCMservice.kchzService;
import com.fh.util.Const;
import com.fh.util.FenyeUtil;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;
import com.fh.util.WX_Parameter;

	/** 
	 * 类名称：BillQueryController
	 * 创建人：FH 
	 * 创建时间：2016-01-27
	 */
	@Controller
	@RequestMapping(value="/kchz")
	public class kchzController extends BaseController {
		
		String menuUrl = "kchz/xlist.do"; //菜单地址(权限用)
		@Resource(name="kchzService")
		private kchzService kchzService;
		
		//库存汇总表 kchz/xlist.do
		@RequestMapping(value="/xlist")
		public ModelAndView list(Page page){
			logBefore(logger, "列表Terminal");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			try{
				pd = this.getPageData();
				page.setPd(pd);
				
				pd.put("shopcode", WX_Parameter.QSHOPCODE);
				
				//分页
				String fnt = pd.getString("fnt");
				PageData finsum =kchzService.xlistConut(page);
				int count =  Integer.parseInt(finsum.get("count").toString());
				
				String yema = pd.getString("yema");
				String jmys = pd.getString("jmys");
				Map<String, Integer> map = FenyeUtil.Fenye(fnt, jmys, yema, count);
				
				pd.put("ys", map.get("ys").toString());
				pd.put("jmys",map.get("jmys"));//页数
				
				List<PageData>	varList = kchzService.list(page);	
				List<PageData> listDot = kchzService.listDot(page);
				
				mv.setViewName("SCM/kchzList");
				mv.addObject("varList", varList);
				mv.addObject("listDot", listDot);
				
				mv.addObject("pd", pd);
				mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			} catch(Exception e){
				logger.error(e.toString(), e);
			}
			return mv;
		}
		
		//库存明细表  kchz/mxlslist.do
		@RequestMapping(value="/mxlist")
		public ModelAndView mxlist(Page page){
			logBefore(logger, "库存明细表");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			try{
				pd = this.getPageData();
				page.setPd(pd);
				
				pd.put("shopcode", WX_Parameter.QSHOPCODE);
				
				//分页
				String fnt = pd.getString("fnt");
				PageData finsum =kchzService.lslistConut(page);
				int count =  Integer.parseInt(finsum.get("count").toString());
				
				String yema = pd.getString("yema");
				String jmys = pd.getString("jmys");
				Map<String, Integer> map = FenyeUtil.Fenye(fnt, jmys, yema, count);
				
				pd.put("ys", map.get("ys").toString());
				pd.put("jmys",map.get("jmys"));//页数
				
				List<PageData>	varList = kchzService.lslist(page);	
				List<PageData> listDot = kchzService.listDot(page);
				
				mv.addObject("listDot", listDot);
				mv.setViewName("SCM/kcmxList");
				mv.addObject("varList", varList);
				
				mv.addObject("pd", pd);
				mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			} catch(Exception e){
				logger.error(e.toString(), e);
			}
			return mv;
		}

		/**
		 * 新增
		 */
		@RequestMapping(value="/save")
		public ModelAndView save() throws Exception{
			logBefore(logger, "新增Dot");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			
			//posService.save(pd);
			mv.addObject("msg","success");
			mv.setViewName("save_result");
			return mv;
		}
		
		/**
		 * 去新增页面
		 * @throws Exception 
		 */
		@RequestMapping(value="/goAdd")
		public ModelAndView goAdd() throws Exception{
			logBefore(logger, "去新增Dot页面");
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			//List<PageData>	varListdot = posService.listDot(pd);
			try {
				mv.setViewName("pos/pos_edit");
				//mv.addObject("varDot",varListdot);
				mv.addObject("msg", "save");
				mv.addObject("pd", pd);
			} catch (Exception e) {
				logger.error(e.toString(), e);
			}						
			return mv;
		}	
		

		/**
		 * 删除用户
		 */
		@RequestMapping(value="/delete")
		public void deleteU(PrintWriter out){
			PageData pd = new PageData();
			try{
				pd = this.getPageData();
				if(Jurisdiction.buttonJurisdiction(menuUrl, "del"))
					{
						//posService.delete(pd);
					}
				out.write("success");
				out.close();
			} catch(Exception e){
				logger.error(e.toString(), e);
			}
			
		}
		/**
		 * qu保存 页面
		 */
		@RequestMapping(value="/edit")
		public ModelAndView edit() throws Exception{
			logBefore(logger, "修改Dot");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			try{
			//pd = posService.findById(pd);
			mv.setViewName("pos/edit");
			//posService.edit(pd);
			mv.addObject("pd", pd);
			mv.addObject("msg","goEdit");
			} catch (Exception e) {
					logger.error(e.toString(), e);
				}	
			return mv;
		}
		/**
		 * 修改
		 */
		@RequestMapping(value="/goEdit")
		public ModelAndView goEdit() throws Exception{
			logBefore(logger, "修改Dot");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			//posService.edit(pd);
			//mv.setViewName("catbox/edit");
			//mv.addObject("msg","success");
			mv.addObject("msg","success");
			mv.setViewName("save_result");
			return mv;
		}
		
		
		
		
		/* ===============================权限================================== */
		public Map<String, String> getHC(){
			Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
			Session session = currentUser.getSession();
			Map<String, String> result =(Map<String, String>) session.getAttribute(Const.SESSION_QX);
			return result;
		}
		/* ===============================权限================================== */
		
		@InitBinder
		public void initBinder(WebDataBinder binder){
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
		}
	}
