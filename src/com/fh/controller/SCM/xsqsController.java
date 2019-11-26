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
import com.fh.service.SCMservice.xsqsService;
import com.fh.util.Const;
import com.fh.util.FenyeUtil;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;
import com.fh.util.WX_Parameter;

	/** 
	 * �����ƣ�BillQueryController
	 * �����ˣ�FH 
	 * ����ʱ�䣺2016-01-27
	 */
	@Controller
	@RequestMapping(value="/xsqs")
	public class xsqsController extends BaseController {
		
		String menuUrl = "xsqs/xlist.do"; //�˵���ַ(Ȩ����)
		@Resource(name="xsqsService")
		private xsqsService xsqsService;
		
		//�¶�����ѯ xsqs/xlist.do
		@RequestMapping(value="/xlist")
		public ModelAndView list(Page page){
			logBefore(logger, "�б�Terminal");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //У��Ȩ��
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			try{
				pd = this.getPageData();
				page.setPd(pd);
				
				pd.put("shopcode", WX_Parameter.QSHOPCODE);
				
				//����
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String lastLoginStart = pd.getString("lastLoginStart");
				String lastLoginEnd = pd.getString("lastLoginEnd");
				if ("".equals(lastLoginEnd)||null==lastLoginEnd) {
					pd.put("lastLoginEnd", formatter.format(date));
				}
				if ("".equals(lastLoginStart)||null==lastLoginStart) {
					pd.put("lastLoginStart", formatter.format(date));
				}
				
				//��ҳ
				String fnt = pd.getString("fnt");
				PageData finsum =xsqsService.xlistConut(page);
				int count =  Integer.parseInt(finsum.get("count").toString());
				
				String yema = pd.getString("yema");
				String jmys = pd.getString("jmys");
				Map<String, Integer> map = FenyeUtil.Fenye(fnt, jmys, yema, count);
				
				pd.put("ys", map.get("ys").toString());
				pd.put("jmys",map.get("jmys"));//ҳ��
				
				List<PageData>	varList = xsqsService.list(page);	
				
				mv.setViewName("SCM/xsqsList");
				mv.addObject("varList", varList);
				
				mv.addObject("pd", pd);
				mv.addObject(Const.SESSION_QX,this.getHC());	//��ťȨ��
			} catch(Exception e){
				logger.error(e.toString(), e);
			}
			return mv;
		}
		
		//��ʷ������ѯ  xsqs/lslist.do
		@RequestMapping(value="/lslist")
		public ModelAndView lslist(Page page){
			logBefore(logger, "�б�Terminal");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //У��Ȩ��
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			try{
				pd = this.getPageData();
				page.setPd(pd);
				
				pd.put("shopcode", WX_Parameter.QSHOPCODE);
				
				//����
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String lastLoginStart = pd.getString("lastLoginStart");
				String lastLoginEnd = pd.getString("lastLoginEnd");
				if ("".equals(lastLoginEnd)||null==lastLoginEnd) {
					pd.put("lastLoginEnd", formatter.format(date));
				}
				if ("".equals(lastLoginStart)||null==lastLoginStart) {
					pd.put("lastLoginStart", formatter.format(date));
				}
				
				//��ҳ
				String fnt = pd.getString("fnt");
				PageData finsum =xsqsService.lslistConut(page);
				int count =  Integer.parseInt(finsum.get("count").toString());
				
				String yema = pd.getString("yema");
				String jmys = pd.getString("jmys");
				Map<String, Integer> map = FenyeUtil.Fenye(fnt, jmys, yema, count);
				
				pd.put("ys", map.get("ys").toString());
				pd.put("jmys",map.get("jmys"));//ҳ��
				
				List<PageData>	varList = xsqsService.lslist(page);	
				
				mv.setViewName("SCM/lsddList");
				mv.addObject("varList", varList);
				
				mv.addObject("pd", pd);
				mv.addObject(Const.SESSION_QX,this.getHC());	//��ťȨ��
			} catch(Exception e){
				logger.error(e.toString(), e);
			}
			return mv;
		}

	
		
		@RequestMapping(value="/goEdit")
		public ModelAndView goEdit(Page page) throws Exception{
			logBefore(logger, "锟睫革拷Dot");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校锟斤拷权锟斤拷
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			page.setPd(pd);
			pd.put("shopcode", WX_Parameter.QSHOPCODE);
			
			PageData lszdxqhz =xsqsService.lszdxqhz(pd);
			List<PageData>	varList = xsqsService.lszdxqList(pd);
			
			pd =xsqsService.lszdxqConut(pd);
			
			mv.addObject("lszdxqhz", lszdxqhz);
			mv.addObject("pd", pd);
			mv.addObject("varList", varList);
			mv.setViewName("SCM/lszdxq");
			
			return mv;
		}
		
		/* ===============================Ȩ��================================== */
		public Map<String, String> getHC(){
			Subject currentUser = SecurityUtils.getSubject();  //shiro�����session
			Session session = currentUser.getSession();
			Map<String, String> result =(Map<String, String>) session.getAttribute(Const.SESSION_QX);
			return result;
		}
		/* ===============================Ȩ��================================== */
		
		@InitBinder
		public void initBinder(WebDataBinder binder){
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
		}
	}
