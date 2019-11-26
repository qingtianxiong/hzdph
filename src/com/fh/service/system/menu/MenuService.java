package com.fh.service.system.menu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.system.Menu;
import com.fh.util.PageData;

@Service("menuService")
public class MenuService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	public void deleteMenuById(String MENU_ID) throws Exception {
		dao.save("MenuMapper.deleteMenuById", MENU_ID);
		
	}

	public PageData getMenuById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("MenuMapper.getMenuById", pd);
		
	}

	//鍙栨渶澶d
	public PageData findMaxId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("MenuMapper.findMaxId", pd);
		
	}
	
	public List<Menu> listAllParentMenu() throws Exception {
		return (List<Menu>) dao.findForList("MenuMapper.listAllParentMenu", null);
		
	}

	public void saveMenu(Menu menu) throws Exception {
		if(menu.getMENU_ID()!=null && menu.getMENU_ID() != ""){
			//dao.update("MenuMapper.updateMenu", menu);
			dao.save("MenuMapper.insertMenu", menu);
		}else{
			dao.save("MenuMapper.insertMenu", menu);
		}
	}

	public List<Menu> listSubMenuByParentId(String parentId) throws Exception {
		return (List<Menu>) dao.findForList("MenuMapper.listSubMenuByParentId", parentId);
		
	}
		
	public List<Menu> listAllMenu() throws Exception {
		List<Menu> rl = this.listAllParentMenu();
		for(Menu menu : rl){
			List<Menu> subList = this.listSubMenuByParentId(menu.getMENU_ID());
			menu.setSubMenu(subList);
		}
		return rl;
	}

	public List<Menu> listAllSubMenu() throws Exception{
		return (List<Menu>) dao.findForList("MenuMapper.listAllSubMenu", null);
		
	}
	
	/**
	 * 缂栬緫
	 */
	public PageData edit(PageData pd) throws Exception {
		return (PageData)dao.findForObject("MenuMapper.updateMenu", pd);
	}
	/**
	 * 淇濆瓨鑿滃崟鍥炬爣 (椤堕儴鑿滃崟)
	 */
	public PageData editicon(PageData pd) throws Exception {
		return (PageData)dao.findForObject("MenuMapper.editicon", pd);
	}
	
	/**
	 * 鏇存柊瀛愯彍鍗曠被鍨嬭彍鍗�
	 */
	public PageData editType(PageData pd) throws Exception {
		return (PageData)dao.findForObject("MenuMapper.editType", pd);
	}
	
	public PageData findShopcode(PageData pd) throws Exception {
		return (PageData)dao.findForObject("R13","MenuMapper.findShopcode", pd);
	}
	
	
}
