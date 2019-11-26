package com.fh.service.system.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.User;
import com.fh.util.PageData;


@Service("userService")
public class UserService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	//======================================================================================
	
	/*
	* 閫氳繃id鑾峰彇鏁版嵁
	*/
	public PageData findByUiId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserXMapper.findByUiId", pd);
	}
	/*
	* 閫氳繃loginname鑾峰彇鏁版嵁
	*/
	public PageData findByUId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserXMapper.findByUId", pd);
	}
	
	/*
	* 閫氳繃閭鑾峰彇鏁版嵁
	*/
	public PageData findByUE(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserXMapper.findByUE", pd);
	}
	
	/*
	* 閫氳繃缂栧彿鑾峰彇鏁版嵁
	*/
	public PageData findByUN(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserXMapper.findByUN", pd);
	}
	
	/*
	* 淇濆瓨鐢ㄦ埛
	*/
	public void saveU(PageData pd)throws Exception{
		dao.save("UserXMapper.saveU", pd);
	}
	/*
	* 淇敼鐢ㄦ埛
	*/
	/*
	 * public void editU(PageData pd)throws Exception{
	 * dao.update("UserXMapper.editU", pd); }
	 */
	/*
	* 鎹㈢毊鑲�
	*/
	public void setSKIN(PageData pd)throws Exception{
		dao.update("UserXMapper.setSKIN", pd);
	}
	/*
	* 鍒犻櫎鐢ㄦ埛
	*/
	public void deleteU(PageData pd)throws Exception{
		dao.delete("UserXMapper.deleteU", pd);
	}
	/*
	* 鎵归噺鍒犻櫎鐢ㄦ埛
	*/
	public void deleteAllU(String[] USER_IDS)throws Exception{
		dao.delete("UserXMapper.deleteAllU", USER_IDS);
	}
	/*
	*鐢ㄦ埛鍒楄〃(鐢ㄦ埛缁�)
	*/
	public List<PageData> listPdPageUser(Page page)throws Exception{
		return (List<PageData>) dao.findForList("UserXMapper.userlistPage", page);
	}
	
	/*
	*鐢ㄦ埛鍒楄〃(鍏ㄩ儴)
	*/
	public List<PageData> listAllUser(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("UserXMapper.listAllUser", pd);
	}
	
	/*
	*鐢ㄦ埛鍒楄〃(渚涘簲鍟嗙敤鎴�)
	*/
	public List<PageData> listGPdPageUser(Page page)throws Exception{
		return (List<PageData>) dao.findForList("UserXMapper.userGlistPage", page);
	}
	/*
	* 淇濆瓨鐢ㄦ埛IP
	*/
	public void saveIP(PageData pd)throws Exception{
		dao.update("UserXMapper.saveIP", pd);
	}
	
	/*
	* 鐧诲綍鍒ゆ柇
	*/
	public PageData getUserByNameAndPwd(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserXMapper.getUserInfo", pd);
	}
	/*
	* 璺熸柊鐧诲綍鏃堕棿
	*/
	public void updateLastLogin(PageData pd)throws Exception{
		dao.update("UserXMapper.updateLastLogin", pd);
	}
	
	/*
	*閫氳繃id鑾峰彇鏁版嵁
	*/
	public User getUserAndRoleById(String USER_ID) throws Exception {
		return (User) dao.findForObject("UserMapper.getUserAndRoleById", USER_ID);
	}
	
	public List<PageData> bylist(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("R13","UserXMapper.bylist", pd);
	}
	
	public List<PageData> sylist(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("R13","UserXMapper.sylist", pd);
	}
	
	public void editU(PageData pd)throws Exception{
		dao.update("R13","UserXMapper.editU", pd);
	}
}
