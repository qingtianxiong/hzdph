package com.fh.controller.system.login;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Menu;
import com.fh.entity.system.Role;
import com.fh.entity.system.User;
import com.fh.service.system.menu.MenuService;
import com.fh.service.system.role.RoleService;
import com.fh.service.system.user.UserService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.MD5;
import com.fh.util.PageData;
import com.fh.util.RightsHelper;
import com.fh.util.Tools;
import com.fh.util.WX_Parameter;
/*
 * 鎬诲叆鍙�
 */
@Controller
public class LoginController extends BaseController {

	@Resource(name="userService")
	private UserService userService;
	@Resource(name="menuService")
	private MenuService menuService;
	@Resource(name="roleService")
	private RoleService roleService;


	/**
	 * 鑾峰彇鐧诲綍鐢ㄦ埛鐨処P
	 * @throws Exception 
	 */
	public void getRemortIP(String USERNAME) throws Exception {  
		PageData pd = new PageData();
		HttpServletRequest request = this.getRequest();
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {  
			ip = request.getRemoteAddr();  
	    }else{
	    	ip = request.getHeader("x-forwarded-for");  
	    }
		pd.put("USERNAME", USERNAME);
		pd.put("IP", ip);
		userService.saveIP(pd);
	}  
	
	
	/**
	 * 璁块棶鐧诲綍椤�
	 * @return
	 */
	@RequestMapping(value="/login_toLogin")
	public ModelAndView toLogin()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //璇诲彇绯荤粺鍚嶇О
		mv.setViewName("system/admin/login");
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value="/login_login" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object login()throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String errInfo = "";
		String KEYDATA[] = pd.getString("KEYDATA").replaceAll("qq313596790fh", "").replaceAll("QQ978336446fh", "").split(",fh,");
		WX_Parameter.QSHOPCODE = pd.getString("shopcode");//获取shopcode全局KEYDATA=qq313596790fhadmin,fh,1QQ978336446fh,fh,wy60
		String userid = KEYDATA[0];
		String pdw  = MD5.md5(KEYDATA[1]).toUpperCase();
		pd.put("userid", userid);//C4CA4238A0B923820DCC509A6F75849B
		WX_Parameter.Quserid = userid;
		
		PageData findShopcode = menuService.findShopcode(pd);
		WX_Parameter.QUSERNAME = findShopcode.getString("USER_NAME");
		if (!findShopcode.getString("USER_ID").trim().equals(userid)||!findShopcode.getString("USER_PWD").trim().equals(pdw)) {
			errInfo = "usererror"; 				//用户名或密码有误
			map.put("result", errInfo);
			return AppUtil.returnObject(new PageData(), map);
		}
		
		//判断shopcode是否存在
		if (findShopcode.size() > 0 ) {
			long beg = df.parse(findShopcode.get("BEG_DATE").toString()).getTime();//有效时间前1554134400000
			long end = df.parse(findShopcode.get("END_DATE").toString()).getTime();//有效时间后1585756800000
			long now = new Date().getTime();//1570694430132
			
			//判断账户是否过期
			if (now < end || beg > now) {
			
				if(null != KEYDATA && KEYDATA.length == 3){
					//shiro管理的session
					Subject currentUser = SecurityUtils.getSubject();  
					Session session = currentUser.getSession();
					String sessionCode = (String)session.getAttribute(Const.SESSION_SECURITY_CODE);		//获取session中的验证码
					
					String code = KEYDATA[2];
					if(null == code || "".equals(code)){
						errInfo = "nullcode"; //验证码为空
					}else{
						String USERNAME = "admin";
						String PASSWORD  = "1";
						pd.put("USERNAME", USERNAME);
						if(Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(code)){
							String passwd = new SimpleHash("SHA-1", USERNAME, PASSWORD).toString();	//瀵嗙爜鍔犲瘑
							pd.put("PASSWORD", passwd);
							pd = userService.getUserByNameAndPwd(pd);
							if(pd != null){
								pd.put("LAST_LOGIN",DateUtil.getTime().toString());
								userService.updateLastLogin(pd);
								User user = new User();
								user.setUSER_ID(pd.getString("USER_ID"));
								user.setUSERNAME(pd.getString("USERNAME"));
								user.setPASSWORD(pd.getString("PASSWORD"));
								user.setNAME(pd.getString("NAME"));
								user.setRIGHTS(pd.getString("RIGHTS"));
								user.setROLE_ID(pd.getString("ROLE_ID"));
								user.setLAST_LOGIN(pd.getString("LAST_LOGIN"));
								user.setIP(pd.getString("IP"));
								user.setSTATUS(pd.getString("STATUS"));
								session.setAttribute(Const.SESSION_USER, user);
								session.removeAttribute(Const.SESSION_SECURITY_CODE);
								
								//shiro加入身份验证
								Subject subject = SecurityUtils.getSubject(); 
							    UsernamePasswordToken token = new UsernamePasswordToken(USERNAME, PASSWORD); 
							    try { 
							        subject.login(token); 
							    } catch (AuthenticationException e) { 
							    	errInfo = "身份验证失败！";
							    }
							    
							}else{
								errInfo = "usererror"; 				//用户名或密码有误
							}
						}else{
							errInfo = "codeerror";				 	//验证码输入有误
						}
						if(Tools.isEmpty(errInfo)){
							errInfo = "success";					//验证成功
						}
					}
				}else{
					errInfo = "error";	//缺少参数
				}
			}else {
				errInfo = "outTime";	//商户号没在试用期内			
			}	
		}else {
			errInfo = "noshopcode";	//不存在的shopcode
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 璁块棶绯荤粺棣栭〉
	 */
	@RequestMapping(value="/main/{changeMenu}")
	public ModelAndView login_index(@PathVariable("changeMenu") String changeMenu){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			
			//shiro绠＄悊鐨剆ession
			Subject currentUser = SecurityUtils.getSubject();  
			Session session = currentUser.getSession();
			
			User user = (User)session.getAttribute(Const.SESSION_USER);
			if (user != null) {
				
				User userr = (User)session.getAttribute(Const.SESSION_USERROL);
				if(null == userr){
					user = userService.getUserAndRoleById(user.getUSER_ID());
					session.setAttribute(Const.SESSION_USERROL, user);
				}else{
					user = userr;
				}
				Role role = user.getRole();
				String roleRights = role!=null ? role.getRIGHTS() : "";
				//閬垮厤姣忔鎷︽埅鐢ㄦ埛鎿嶄綔鏃舵煡璇㈡暟鎹簱锛屼互涓嬪皢鐢ㄦ埛鎵�灞炶鑹叉潈闄愩�佺敤鎴锋潈闄愰檺閮藉瓨鍏ession
				session.setAttribute(Const.SESSION_ROLE_RIGHTS, roleRights); 		//灏嗚鑹叉潈闄愬瓨鍏ession
				session.setAttribute(Const.SESSION_USERNAME, user.getUSERNAME());	//鏀惧叆鐢ㄦ埛鍚�
				
				List<Menu> allmenuList = new ArrayList<Menu>();
				
				if(null == session.getAttribute(Const.SESSION_allmenuList)){
					allmenuList = menuService.listAllMenu();
					if(Tools.notEmpty(roleRights)){
						for(Menu menu : allmenuList){
							menu.setHasMenu(RightsHelper.testRights(roleRights, menu.getMENU_ID()));
							if(menu.isHasMenu()){
								List<Menu> subMenuList = menu.getSubMenu();
								for(Menu sub : subMenuList){
									sub.setHasMenu(RightsHelper.testRights(roleRights, sub.getMENU_ID()));
								}
							}
						}
					}
					session.setAttribute(Const.SESSION_allmenuList, allmenuList);			//鑿滃崟鏉冮檺鏀惧叆session涓�
				}else{
					allmenuList = (List<Menu>)session.getAttribute(Const.SESSION_allmenuList);
				}
				
				//鍒囨崲鑿滃崟=====
				List<Menu> menuList = new ArrayList<Menu>();
				//if(null == session.getAttribute(Const.SESSION_menuList) || ("yes".equals(pd.getString("changeMenu")))){
				if(null == session.getAttribute(Const.SESSION_menuList) || ("yes".equals(changeMenu))){
					List<Menu> menuList1 = new ArrayList<Menu>();
					List<Menu> menuList2 = new ArrayList<Menu>();
					
					//鎷嗗垎鑿滃崟
					for(int i=0;i<allmenuList.size();i++){
						Menu menu = allmenuList.get(i);
						if("1".equals(menu.getMENU_TYPE())){
							menuList1.add(menu);
						}else{
							menuList2.add(menu);
						}
					}
					
					session.removeAttribute(Const.SESSION_menuList);
					if("2".equals(session.getAttribute("changeMenu"))){
						session.setAttribute(Const.SESSION_menuList, menuList1);
						session.removeAttribute("changeMenu");
						session.setAttribute("changeMenu", "1");
						menuList = menuList1;
					}else{
						session.setAttribute(Const.SESSION_menuList, menuList2);
						session.removeAttribute("changeMenu");
						session.setAttribute("changeMenu", "2");
						menuList = menuList2;
					}
				}else{
					menuList = (List<Menu>)session.getAttribute(Const.SESSION_menuList);
				}
				//鍒囨崲鑿滃崟=====
				
				if(null == session.getAttribute(Const.SESSION_QX)){
					session.setAttribute(Const.SESSION_QX, this.getUQX(session));	//鎸夐挳鏉冮檺鏀惧埌session涓�
				}
				
				//FusionCharts 鎶ヨ〃
			 	String strXML = "<graph caption='鍓�12涓湀璁㈠崟閿�閲忔煴鐘跺浘' xAxisName='鏈堜唤' yAxisName='鍊�' decimalPrecision='0' formatNumberScale='0'><set name='2013-05' value='4' color='AFD8F8'/><set name='2013-04' value='0' color='AFD8F8'/><set name='2013-03' value='0' color='AFD8F8'/><set name='2013-02' value='0' color='AFD8F8'/><set name='2013-01' value='0' color='AFD8F8'/><set name='2012-01' value='0' color='AFD8F8'/><set name='2012-11' value='0' color='AFD8F8'/><set name='2012-10' value='0' color='AFD8F8'/><set name='2012-09' value='0' color='AFD8F8'/><set name='2012-08' value='0' color='AFD8F8'/><set name='2012-07' value='0' color='AFD8F8'/><set name='2012-06' value='0' color='AFD8F8'/></graph>" ;
			 	mv.addObject("strXML", strXML);
			 	//FusionCharts 鎶ヨ〃
			 	
				mv.setViewName("system/admin/index");
				mv.addObject("user", user);
				mv.addObject("menuList", menuList);
			}else {
				mv.setViewName("system/admin/login");//session澶辨晥鍚庤烦杞櫥褰曢〉闈�
			}
			
			
		} catch(Exception e){
			mv.setViewName("system/admin/login");
			logger.error(e.getMessage(), e);
		}
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //璇诲彇绯荤粺鍚嶇О
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**
	 * 杩涘叆tab鏍囩
	 * @return
	 */
	@RequestMapping(value="/tab")
	public String tab(){
		return "system/admin/tab";
	}
	
	/**
	 * 杩涘叆棣栭〉鍚庣殑榛樿椤甸潰
	 * @return
	 */
	@RequestMapping(value="/login_default")
	public ModelAndView defaultPage(Page page){
		logBefore(logger, "首页");
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			pd.put("shopcode", WX_Parameter.QSHOPCODE);
			
			List<PageData> bylist= userService.bylist(pd);
			List<PageData> sylist= userService.sylist(pd);
	        
			mv.addObject("sylist", sylist);
			mv.addObject("bylist", bylist);
			mv.setViewName("system/admin/default");
			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 鐢ㄦ埛娉ㄩ攢
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		//shiro绠＄悊鐨剆ession
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(Const.SESSION_ROLE_RIGHTS);
		session.removeAttribute(Const.SESSION_allmenuList);
		session.removeAttribute(Const.SESSION_menuList);
		session.removeAttribute(Const.SESSION_QX);
		session.removeAttribute(Const.SESSION_userpds);
		session.removeAttribute(Const.SESSION_USERNAME);
		session.removeAttribute(Const.SESSION_USERROL);
		session.removeAttribute("changeMenu");
		
		//shiro閿�姣佺櫥褰�
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
		
		pd = this.getPageData();
		String  msg = pd.getString("msg");
		pd.put("msg", msg);
		
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); //璇诲彇绯荤粺鍚嶇О
		mv.setViewName("system/admin/login");
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**
	 * 鑾峰彇鐢ㄦ埛鏉冮檺
	 */
	public Map<String, String> getUQX(Session session){
		PageData pd = new PageData();
		Map<String, String> map = new HashMap<String, String>();
		try {
			String USERNAME = session.getAttribute(Const.SESSION_USERNAME).toString();
			pd.put(Const.SESSION_USERNAME, USERNAME);
			String ROLE_ID = userService.findByUId(pd).get("ROLE_ID").toString();
			
			pd.put("ROLE_ID", ROLE_ID);
			
			PageData pd2 = new PageData();
			pd2.put(Const.SESSION_USERNAME, USERNAME);
			pd2.put("ROLE_ID", ROLE_ID);
			
			pd = roleService.findObjectById(pd);																
				
			pd2 = roleService.findGLbyrid(pd2);
			if(null != pd2){
				map.put("FX_QX", pd2.get("FX_QX").toString());
				map.put("FW_QX", pd2.get("FW_QX").toString());
				map.put("QX1", pd2.get("QX1").toString());
				map.put("QX2", pd2.get("QX2").toString());
				map.put("QX3", pd2.get("QX3").toString());
				map.put("QX4", pd2.get("QX4").toString());
			
				pd2.put("ROLE_ID", ROLE_ID);
				pd2 = roleService.findYHbyrid(pd2);
				map.put("C1", pd2.get("C1").toString());
				map.put("C2", pd2.get("C2").toString());
				map.put("C3", pd2.get("C3").toString());
				map.put("C4", pd2.get("C4").toString());
				map.put("Q1", pd2.get("Q1").toString());
				map.put("Q2", pd2.get("Q2").toString());
				map.put("Q3", pd2.get("Q3").toString());
				map.put("Q4", pd2.get("Q4").toString());
			}
			
			map.put("adds", pd.getString("ADD_QX"));
			map.put("dels", pd.getString("DEL_QX"));
			map.put("edits", pd.getString("EDIT_QX"));
			map.put("chas", pd.getString("CHA_QX"));
			
			//System.out.println(map);
			
			this.getRemortIP(USERNAME);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}	
		return map;
	}
	
}
