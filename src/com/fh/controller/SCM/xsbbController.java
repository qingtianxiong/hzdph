package com.fh.controller.SCM;
 
	import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.fh.service.SCMservice.xsbbService;
import com.fh.util.Const;
import com.fh.util.FenyeUtil;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.WX_Parameter;

	
	@Controller
	@RequestMapping(value="/xsbb")
	public class xsbbController extends BaseController {
		
		String menuUrl = "xsbb/xlist.do"; //閿熷壙纰夋嫹閿熸枻鎷峰潃(鏉冮敓鏂ゆ嫹閿熸枻鎷�)
		@Resource(name="xsbbService")
		private xsbbService xsbbService;
		
		//闁匡拷閸烆喛绉奸崝鎸庡Г鐞涳拷 xsbb/xsqslist.do
		@RequestMapping(value="/xsqslist")
		public ModelAndView xsqslist(Page page){
			logBefore(logger, "閸掓銆� xsqslist");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} 
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			try{
				pd = this.getPageData();
				page.setPd(pd);
				
				pd.put("shopcode", WX_Parameter.QSHOPCODE);
				
				//日期
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
				
			/*
			 * //分页 String fnt = pd.getString("fnt"); PageData finsum
			 * =xsbbService.xsqsConut(page); int count =
			 * Integer.parseInt(finsum.get("count").toString());
			 * 
			 * String yema = pd.getString("yema"); String jmys = pd.getString("jmys");
			 * Map<String, Integer> map = FenyeUtil.Fenye(fnt, jmys, yema, count);
			 * 
			 * pd.put("ys", map.get("ys").toString()); pd.put("jmys",map.get("jmys"));//页数
			 */
				
				List<PageData>	varList = xsbbService.list(page);
				List<PageData>	List = xsbbService.xslist(page);
				List<PageData> listDot = xsbbService.listDot(page);
				
				mv.addObject("listDot", listDot);
				mv.setViewName("SCM/xsbbList");
				mv.addObject("varList", varList);
				mv.addObject("List", List);
				
				mv.addObject("pd", pd);
				mv.addObject(Const.SESSION_QX,this.getHC());	
			} catch(Exception e){
				logger.error(e.toString(), e);
			}
			return mv;
		}
		
		//閸熷棗鎼ч柨锟介崬顔芥缂佸棜銆�  xsbb/xsmxlist.do
		@RequestMapping(value="/xsmxlist")
		public ModelAndView xsmxlist(Page page){
			logBefore(logger, "閸掓銆� xsmxlist");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //鏍￠敓鏂ゆ嫹鏉冮敓鏂ゆ嫹
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			try{
				pd = this.getPageData();
				page.setPd(pd);
				
				pd.put("shopcode", WX_Parameter.QSHOPCODE);
				
				//日期
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
				
				//分页
				String fnt = pd.getString("fnt");
				PageData finsum =xsbbService.xsmxConut(page);
				int count =  Integer.parseInt(finsum.get("count").toString());
				
				String yema = pd.getString("yema");
				String jmys = pd.getString("jmys");
				Map<String, Integer> map = FenyeUtil.Fenye(fnt, jmys, yema, count);
				
				pd.put("ys", map.get("ys").toString());
				pd.put("jmys",map.get("jmys"));//页数
				
				PageData spmxhz =xsbbService.spmxhz(pd);
				List<PageData>	varList = xsbbService.lslist(page);	
				List<PageData> listDot = xsbbService.listDot(page);
				
				mv.addObject("listDot", listDot);
				mv.setViewName("SCM/xsmxList");
				mv.addObject("varList", varList);
				mv.addObject("spmxhz", spmxhz);
				
				mv.addObject("pd", pd);
				mv.addObject(Const.SESSION_QX,this.getHC());	//閿熸枻鎷烽挳鏉冮敓鏂ゆ嫹
			} catch(Exception e){
				logger.error(e.toString(), e);
			}
			return mv;
		}
		
		
		//閸忋儱绨遍妴渚�锟斤拷鐠愌勭叀鐠囷拷  xsbb/rkthlist.do
		@RequestMapping(value="/rkthlist")
		public ModelAndView rkthlist(Page page){
			logBefore(logger, "閸掓銆� xsmxlist");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //鏍￠敓鏂ゆ嫹鏉冮敓鏂ゆ嫹
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			try{
				pd = this.getPageData();
				page.setPd(pd);
				pd.put("shopcode", WX_Parameter.QSHOPCODE);
				
				//日期
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
				
				//分页
				String fnt = pd.getString("fnt");
				PageData finsum =xsbbService.rkthConut(page);
				int count =  Integer.parseInt(finsum.get("count").toString());
				
				String yema = pd.getString("yema");
				String jmys = pd.getString("jmys");
				Map<String, Integer> map = FenyeUtil.Fenye(fnt, jmys, yema, count);
				
				pd.put("ys", map.get("ys").toString());
				pd.put("jmys",map.get("jmys"));//页数
				
				List<PageData>	varList = xsbbService.rkthlist(pd);	
				List<PageData> listDot = xsbbService.listDot1(page);
				
				mv.addObject("listDot", listDot);
				mv.setViewName("SCM/rkthList");
				mv.addObject("varList", varList);
				
				mv.addObject("pd", pd);
				mv.addObject(Const.SESSION_QX,this.getHC());	//閿熸枻鎷烽挳鏉冮敓鏂ゆ嫹
			} catch(Exception e){
				logger.error(e.toString(), e);
			}
			return mv;
		}
		
		
		//濠婄偤鏀㈤崯鍡楁惂鐞涳拷  xsbb/zxsplist.do
		@RequestMapping(value="/zxsplist")
		public ModelAndView zxsplist(Page page){
			logBefore(logger, "閸掓銆� zxsplist");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //鏍￠敓鏂ゆ嫹鏉冮敓鏂ゆ嫹
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			try{
				pd = this.getPageData();
				page.setPd(pd);
				
				pd.put("shopcode", WX_Parameter.QSHOPCODE);
				
				//分页
				String fnt = pd.getString("fnt");
				PageData finsum =xsbbService.zxspConut(page);
				int count =  Integer.parseInt(finsum.get("count").toString());
				
				String yema = pd.getString("yema");
				String jmys = pd.getString("jmys");
				Map<String, Integer> map = FenyeUtil.Fenye(fnt, jmys, yema, count);
				
				pd.put("ys", map.get("ys").toString());
				pd.put("jmys",map.get("jmys"));//页数
				
				List<PageData>	varList = xsbbService.zxsplist(page);	
				
				mv.setViewName("SCM/zxspList");
				mv.addObject("varList", varList);
				
				mv.addObject("pd", pd);
				mv.addObject(Const.SESSION_QX,this.getHC());	//閿熸枻鎷烽挳鏉冮敓鏂ゆ嫹
			} catch(Exception e){
				logger.error(e.toString(), e);
			}
			return mv;
		}
		
		
		//鐠愮喎绨辩�涙ɑ濮ょ悰锟�  xsbb/fkclist.do
		@RequestMapping(value="/fkclist")
		public ModelAndView fkclist(Page page){
			logBefore(logger, "閸掓銆� fkclist");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //鏍￠敓鏂ゆ嫹鏉冮敓鏂ゆ嫹
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			try{
				pd = this.getPageData();
				page.setPd(pd);
				
				pd.put("shopcode", WX_Parameter.QSHOPCODE);
				
				//分页
				String fnt = pd.getString("fnt");
				PageData finsum =xsbbService.fkcConut(page);
				int count =  Integer.parseInt(finsum.get("count").toString());
				
				String yema = pd.getString("yema");
				String jmys = pd.getString("jmys");
				Map<String, Integer> map = FenyeUtil.Fenye(fnt, jmys, yema, count);
				
				pd.put("ys", map.get("ys").toString());
				pd.put("jmys",map.get("jmys"));//页数
				
				
				List<PageData>	varList = xsbbService.fkclist(page);	
				
				mv.setViewName("SCM/fkcList");
				mv.addObject("varList", varList);
				
				mv.addObject("pd", pd);
				mv.addObject(Const.SESSION_QX,this.getHC());	//閿熸枻鎷烽挳鏉冮敓鏂ゆ嫹
			} catch(Exception e){
				logger.error(e.toString(), e);
			}
			return mv;
		}
		
		//缂傞缚鎻ｉ崯鍡楁惂鐞涳拷  xsbb/qhsplist.do
		@RequestMapping(value="/qhsplist")
		public ModelAndView qhsplist(Page page){
			logBefore(logger, "閸掓銆� qhsplist");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //鏍￠敓鏂ゆ嫹鏉冮敓鏂ゆ嫹
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			try{
				pd = this.getPageData();
				page.setPd(pd);
				
				pd.put("shopcode", WX_Parameter.QSHOPCODE);
				
				//分页
				String fnt = pd.getString("fnt");
				PageData finsum =xsbbService.qhspConut(page);
				int count =  Integer.parseInt(finsum.get("count").toString());
				
				String yema = pd.getString("yema");
				String jmys = pd.getString("jmys");
				Map<String, Integer> map = FenyeUtil.Fenye(fnt, jmys, yema, count);
				
				pd.put("ys", map.get("ys").toString());
				pd.put("jmys",map.get("jmys"));//页数
				
				List<PageData>	varList = xsbbService.qhsplist(page);	
				
				mv.setViewName("SCM/qhspList");
				mv.addObject("varList", varList);
				
				mv.addObject("pd", pd);
				mv.addObject(Const.SESSION_QX,this.getHC());	//閿熸枻鎷烽挳鏉冮敓鏂ゆ嫹
			} catch(Exception e){
				logger.error(e.toString(), e);
			}
			return mv;
		}
				
				
		
		@RequestMapping(value="/goEdit")
		public ModelAndView goEdit(Page page) throws Exception{
			logBefore(logger, "閿熺潾闈╂嫹Dot");
			if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //鏍￠敓鏂ゆ嫹鏉冮敓鏂ゆ嫹
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			page.setPd(pd);
			pd.put("shopcode", WX_Parameter.QSHOPCODE);
			
			PageData rkthxqhz =xsbbService.rkthxqhz(pd);
			List<PageData>	varList = xsbbService.rkthxqList(pd);	
			pd =xsbbService.rkthxqConut(pd);
			
			mv.addObject("rkthxqhz", rkthxqhz);
			mv.addObject("pd", pd);
			mv.addObject("varList", varList);
			mv.setViewName("SCM/rkthxq");
			
			return mv;
		}
		
		
		/*
		 * 导出到excel
		 * @return
		 */
		@RequestMapping(value="/excel")
		public ModelAndView exportExcel(Page page){
			logBefore(logger, "导出Pictures到excel");
			ModelAndView mv = new ModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			page.setPd(pd);
			pd.put("shopcode", WX_Parameter.QSHOPCODE);
			
			try{
				Map<String,Object> dataMap = new HashMap<String,Object>();
				List<String> titles = new ArrayList<String>();
				titles.add("商品编码");	//1
				titles.add("品名规格");	//2
				titles.add("品牌");	//3
				titles.add("销售数量");	//4
				titles.add("销价金额");	//5
				titles.add("退货数量");	//6
				titles.add("退货金额");	//1
				titles.add("总折扣");	//2
				
				titles.add("实际销售");	//4
				titles.add("毛利额");	
				
				dataMap.put("titles", titles);
			
			  List<PageData> varOList = xsbbService.xsmxecel(page); 
			  List<PageData> varList = new ArrayList<PageData>(); 
			  for (PageData pageData : varOList) {
				  PageData vpd = new PageData(); 
				  String GOODS_ID = "";
				  String GOODS = "";
				  String PP_DESC = "";
				 
				  if (null!=pageData.getString("GOODS_ID")) { GOODS_ID = pageData.getString("GOODS_ID");}
				  if (null!=pageData.getString("GOODS")) { GOODS = pageData.getString("GOODS");}
				  if (null!=pageData.getString("PP_DESC")) { PP_DESC = pageData.getString("PP_DESC");}
				  
				  vpd.put("var1", GOODS_ID); //1
				  vpd.put("var2", GOODS); //2 
				  vpd.put("var3", PP_DESC); //3 
				  vpd.put("var4", String.valueOf(pageData.get("XSSL"))); //4 
				  vpd.put("var5", String.valueOf(pageData.get("XSJE"))); //5 
				  vpd.put("var6", String.valueOf(pageData.get("XSTHSL"))); //6 
				  vpd.put("var7", String.valueOf(pageData.get("XSTHJE"))); //1
				  vpd.put("var8", String.valueOf(pageData.get("ZZKJE"))); //2 
				 
				  vpd.put("var10", String.valueOf(pageData.get("ZRJE"))); //4 
				  vpd.put("var11", String.valueOf(pageData.get("PROFIT"))); //5 
				  
				  varList.add(vpd); 
			  }
			  
			  dataMap.put("varList", varList);
			 
				ObjectExcelView erv = new ObjectExcelView();
				mv = new ModelAndView(erv,dataMap);
			} catch(Exception e){
				logger.error(e.toString(), e);
			}
			return mv;
		}
		
		/* ===============================鏉冮敓鏂ゆ嫹================================== */
		public Map<String, String> getHC(){
			Subject currentUser = SecurityUtils.getSubject();  //shiro閿熸枻鎷烽敓鏂ゆ嫹閿熺氮ession
			Session session = currentUser.getSession();
			Map<String, String> result =(Map<String, String>) session.getAttribute(Const.SESSION_QX);
			return result;
		}
		/* ===============================鏉冮敓鏂ゆ嫹================================== */
		
		@InitBinder
		public void initBinder(WebDataBinder binder){
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
		}
	}
