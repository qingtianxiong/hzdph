package com.fh.controller.system.user;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Role;
import com.fh.service.system.menu.MenuService;
import com.fh.service.system.role.RoleService;
import com.fh.service.system.user.UserService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.FileDownload;
import com.fh.util.FileUpload;
import com.fh.util.GetPinyin;
import com.fh.util.Jurisdiction;
import com.fh.util.MD5;
import com.fh.util.ObjectExcelRead;
import com.fh.util.PageData;
import com.fh.util.ObjectExcelView;
import com.fh.util.PathUtil;
import com.fh.util.Tools;
import com.fh.util.WX_Parameter;

/** 
 * 绫诲悕绉帮細UserController
 * 鍒涘缓浜猴細FH 
 * 鍒涘缓鏃堕棿锛�2014骞�6鏈�28鏃�
 * @version
 */
@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {
	
	String menuUrl = "user/listUsers.do"; //鑿滃崟鍦板潃(鏉冮檺鐢�)
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="roleService")
	private RoleService roleService;
	@Resource(name="menuService")
	private MenuService menuService;
	
	
	/**
	 * 淇濆瓨鐢ㄦ埛
	 */
	@RequestMapping(value="/saveU")
	public ModelAndView saveU(PrintWriter out) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		pd.put("USER_ID", this.get32UUID());	//ID
		pd.put("RIGHTS", "");					//鏉冮檺
		pd.put("LAST_LOGIN", "");				//鏈�鍚庣櫥褰曟椂闂�
		pd.put("IP", "");						//IP
		pd.put("STATUS", "0");					//鐘舵��
		pd.put("SKIN", "default");				//榛樿鐨偆
		
		pd.put("PASSWORD", new SimpleHash("SHA-1", pd.getString("USERNAME"), pd.getString("PASSWORD")).toString());
		
		if(null == userService.findByUId(pd)){
			if(Jurisdiction.buttonJurisdiction(menuUrl, "add")){userService.saveU(pd);} //鍒ゆ柇鏂板鏉冮檺
			mv.addObject("msg","success");
		}else{
			mv.addObject("msg","failed");
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 鍒ゆ柇鐢ㄦ埛鍚嶆槸鍚﹀瓨鍦�
	 */
	@RequestMapping(value="/hasU")
	@ResponseBody
	public Object hasU(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(userService.findByUId(pd) != null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//杩斿洖缁撴灉
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 鍒ゆ柇閭鏄惁瀛樺湪
	 */
	@RequestMapping(value="/hasE")
	@ResponseBody
	public Object hasE(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			
			if(userService.findByUE(pd) != null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//杩斿洖缁撴灉
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 鍒ゆ柇缂栫爜鏄惁瀛樺湪
	 */
	@RequestMapping(value="/hasN")
	@ResponseBody
	public Object hasN(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(userService.findByUN(pd) != null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);				//杩斿洖缁撴灉
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 淇敼鐢ㄦ埛
	 */
	@RequestMapping(value="/editU")
	public ModelAndView editU() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

		pd.put("PASSWORD", MD5.md5(pd.getString("PASSWORD")).toUpperCase());
		
		if(Jurisdiction.buttonJurisdiction(menuUrl, "edit")){userService.editU(pd);}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 鍘讳慨鏀圭敤鎴烽〉闈�
	 */
	@RequestMapping(value="/goEditU")
	public ModelAndView goEditU() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String userName = WX_Parameter.QUSERNAME;
		String userid = WX_Parameter.Quserid;
		mv.setViewName("system/user/user_edit");
		
		mv.addObject("msg", "editU");
		mv.addObject("userName", userName);
		mv.addObject("userid", userid);
		mv.addObject("pd", pd);
		
		return mv;
	}
	
	/**
	 * 鍘绘柊澧炵敤鎴烽〉闈�
	 */
	@RequestMapping(value="/goAddU")
	public ModelAndView goAddU()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<Role> roleList;
		
		roleList = roleService.listAllERRoles();			//鍒楀嚭鎵�鏈変簩绾ц鑹�
		
		mv.setViewName("system/user/user_edit");
		mv.addObject("msg", "saveU");
		mv.addObject("pd", pd);
		mv.addObject("roleList", roleList);

		return mv;
	}
	
	/**
	 * 鏄剧ず鐢ㄦ埛鍒楄〃(鐢ㄦ埛缁�)
	 */
	@RequestMapping(value="/listUsers")
	public ModelAndView listUsers(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String USERNAME = pd.getString("USERNAME");
		
		if(null != USERNAME && !"".equals(USERNAME)){
			USERNAME = USERNAME.trim();
			pd.put("USERNAME", USERNAME);
		}
		
		String lastLoginStart = pd.getString("lastLoginStart");
		String lastLoginEnd = pd.getString("lastLoginEnd");
		
		if(lastLoginStart != null && !"".equals(lastLoginStart)){
			lastLoginStart = lastLoginStart+" 00:00:00";
			pd.put("lastLoginStart", lastLoginStart);
		}
		if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
			lastLoginEnd = lastLoginEnd+" 00:00:00";
			pd.put("lastLoginEnd", lastLoginEnd);
		} 
		
		page.setPd(pd);
		List<PageData>	userList = userService.listPdPageUser(page);			//鍒楀嚭鐢ㄦ埛鍒楄〃
		List<Role> roleList = roleService.listAllERRoles();						//鍒楀嚭鎵�鏈変簩绾ц鑹�
		
		mv.setViewName("system/user/user_list");
		mv.addObject("userList", userList);
		mv.addObject("roleList", roleList);
		mv.addObject("pd", pd);
		mv.addObject(Const.SESSION_QX,this.getHC());	//鎸夐挳鏉冮檺
		return mv;
	}

	
	/**
	 * 鏄剧ず鐢ㄦ埛鍒楄〃(tab鏂瑰紡)
	 */
	@RequestMapping(value="/listtabUsers")
	public ModelAndView listtabUsers(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData>	userList = userService.listAllUser(pd);			//鍒楀嚭鐢ㄦ埛鍒楄〃
		mv.setViewName("system/user/user_tb_list");
		mv.addObject("userList", userList);
		mv.addObject("pd", pd);
		mv.addObject(Const.SESSION_QX,this.getHC());	//鎸夐挳鏉冮檺
		return mv;
	}
	
	/**
	 * 鍒犻櫎鐢ㄦ埛
	 */
	@RequestMapping(value="/deleteU")
	public void deleteU(PrintWriter out){
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(Jurisdiction.buttonJurisdiction(menuUrl, "del")){userService.deleteU(pd);}
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 鎵归噺鍒犻櫎
	 */
	@RequestMapping(value="/deleteAllU")
	@ResponseBody
	public Object deleteAllU() {
		PageData pd = new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String USER_IDS = pd.getString("USER_IDS");
			
			if(null != USER_IDS && !"".equals(USER_IDS)){
				String ArrayUSER_IDS[] = USER_IDS.split(",");
				if(Jurisdiction.buttonJurisdiction(menuUrl, "del")){userService.deleteAllU(ArrayUSER_IDS);}
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	//===================================================================================================
	
	
	
	/*
	 * 瀵煎嚭鐢ㄦ埛淇℃伅鍒癊XCEL
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			if(Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
				//妫�绱㈡潯浠�===
				String USERNAME = pd.getString("USERNAME");
				if(null != USERNAME && !"".equals(USERNAME)){
					USERNAME = USERNAME.trim();
					pd.put("USERNAME", USERNAME);
				}
				String lastLoginStart = pd.getString("lastLoginStart");
				String lastLoginEnd = pd.getString("lastLoginEnd");
				if(lastLoginStart != null && !"".equals(lastLoginStart)){
					lastLoginStart = lastLoginStart+" 00:00:00";
					pd.put("lastLoginStart", lastLoginStart);
				}
				if(lastLoginEnd != null && !"".equals(lastLoginEnd)){
					lastLoginEnd = lastLoginEnd+" 00:00:00";
					pd.put("lastLoginEnd", lastLoginEnd);
				} 
				//妫�绱㈡潯浠�===
				
				Map<String,Object> dataMap = new HashMap<String,Object>();
				List<String> titles = new ArrayList<String>();
				
				titles.add("鐢ㄦ埛鍚�"); 		//1
				titles.add("缂栧彿");  		//2
				titles.add("濮撳悕");			//3
				titles.add("鑱屼綅");			//4
				titles.add("鎵嬫満");			//5
				titles.add("閭");			//6
				titles.add("鏈�杩戠櫥褰�");		//7
				titles.add("涓婃鐧诲綍IP");	//8
				
				dataMap.put("titles", titles);
				
				List<PageData> userList = userService.listAllUser(pd);
				List<PageData> varList = new ArrayList<PageData>();
				for(int i=0;i<userList.size();i++){
					PageData vpd = new PageData();
					vpd.put("var1", userList.get(i).getString("USERNAME"));		//1
					vpd.put("var2", userList.get(i).getString("NUMBER"));		//2
					vpd.put("var3", userList.get(i).getString("NAME"));			//3
					vpd.put("var4", userList.get(i).getString("ROLE_NAME"));	//4
					vpd.put("var5", userList.get(i).getString("PHONE"));		//5
					vpd.put("var6", userList.get(i).getString("EMAIL"));		//6
					vpd.put("var7", userList.get(i).getString("LAST_LOGIN"));	//7
					vpd.put("var8", userList.get(i).getString("IP"));			//8
					varList.add(vpd);
				}
				dataMap.put("varList", varList);
				ObjectExcelView erv = new ObjectExcelView();					//鎵цexcel鎿嶄綔
				mv = new ModelAndView(erv,dataMap);
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 鎵撳紑涓婁紶EXCEL椤甸潰
	 */
	@RequestMapping(value="/goUploadExcel")
	public ModelAndView goUploadExcel()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/user/uploadexcel");
		return mv;
	}
	
	/**
	 * 涓嬭浇妯＄増
	 */
	@RequestMapping(value="/downExcel")
	public void downExcel(HttpServletResponse response)throws Exception{
		
		FileDownload.fileDownload(response, PathUtil.getClasspath() + Const.FILEPATHFILE + "Users.xls", "Users.xls");
		
	}
	
	/**
	 * 浠嶦XCEL瀵煎叆鍒版暟鎹簱
	 */
	@RequestMapping(value="/readExcel")
	public ModelAndView readExcel(
			@RequestParam(value="excel",required=false) MultipartFile file
			) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;}
		if (null != file && !file.isEmpty()) {
			String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//鏂囦欢涓婁紶璺緞
			String fileName =  FileUpload.fileUp(file, filePath, "userexcel");							//鎵ц涓婁紶
			
			List<PageData> listPd = (List)ObjectExcelRead.readExcel(filePath, fileName, 2, 0, 0);	//鎵ц璇籈XCEL鎿嶄綔,璇诲嚭鐨勬暟鎹鍏ist 2:浠庣3琛屽紑濮嬶紱0:浠庣A鍒楀紑濮嬶紱0:绗�0涓猻heet
			
			/*瀛樺叆鏁版嵁搴撴搷浣�======================================*/
			pd.put("RIGHTS", "");					//鏉冮檺
			pd.put("LAST_LOGIN", "");				//鏈�鍚庣櫥褰曟椂闂�
			pd.put("IP", "");						//IP
			pd.put("STATUS", "0");					//鐘舵��
			pd.put("SKIN", "default");				//榛樿鐨偆
			
			List<Role> roleList = roleService.listAllERRoles();	//鍒楀嚭鎵�鏈変簩绾ц鑹�
			
			pd.put("ROLE_ID", roleList.get(0).getROLE_ID());	//璁剧疆瑙掕壊ID涓洪殢渚跨涓�涓�
			/**
			 * var0 :缂栧彿
			 * var1 :濮撳悕
			 * var2 :鎵嬫満
			 * var3 :閭
			 * var4 :澶囨敞
			 */
			for(int i=0;i<listPd.size();i++){		
				pd.put("USER_ID", this.get32UUID());										//ID
				pd.put("NAME", listPd.get(i).getString("var1"));							//濮撳悕
				
				String USERNAME = GetPinyin.getPingYin(listPd.get(i).getString("var1"));	//鏍规嵁濮撳悕姹夊瓧鐢熸垚鍏ㄦ嫾
				pd.put("USERNAME", USERNAME);	
				if(userService.findByUId(pd) != null){										//鍒ゆ柇鐢ㄦ埛鍚嶆槸鍚﹂噸澶�
					USERNAME = GetPinyin.getPingYin(listPd.get(i).getString("var1"))+Tools.getRandomNum();
					pd.put("USERNAME", USERNAME);
				}
				pd.put("BZ", listPd.get(i).getString("var4"));								//澶囨敞
				if(Tools.checkEmail(listPd.get(i).getString("var3"))){						//閭鏍煎紡涓嶅灏辫烦杩�
					pd.put("EMAIL", listPd.get(i).getString("var3"));						
					if(userService.findByUE(pd) != null){									//閭宸插瓨鍦ㄥ氨璺宠繃
						continue;
					}
				}else{
					continue;
				}
				
				pd.put("NUMBER", listPd.get(i).getString("var0"));							//缂栧彿宸插瓨鍦ㄥ氨璺宠繃
				pd.put("PHONE", listPd.get(i).getString("var2"));							//鎵嬫満鍙�
				
				pd.put("PASSWORD", new SimpleHash("SHA-1", USERNAME, "123").toString());	//榛樿瀵嗙爜123
				if(userService.findByUN(pd) != null){
					continue;
				}
				userService.saveU(pd);
			}
			/*瀛樺叆鏁版嵁搴撴搷浣�======================================*/
			
			mv.addObject("msg","success");
		}
		
		mv.setViewName("save_result");
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	

	/* ===============================鏉冮檺================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro绠＄悊鐨剆ession
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================鏉冮檺================================== */
}
