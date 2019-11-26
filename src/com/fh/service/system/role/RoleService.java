package com.fh.service.system.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.system.Role;
import com.fh.util.PageData;

@Service("roleService")
public class RoleService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	public List<Role> listAllERRoles() throws Exception {
		return (List<Role>) dao.findForList("RoleMapper.listAllERRoles", null);
		
	}
	
	
	public List<Role> listAllappERRoles() throws Exception {
		return (List<Role>) dao.findForList("RoleMapper.listAllappERRoles", null);
		
	}
	
	
	public List<Role> listAllRoles() throws Exception {
		return (List<Role>) dao.findForList("RoleMapper.listAllRoles", null);
		
	}
	
	//閫氳繃褰撳墠鐧诲綍鐢ㄧ殑瑙掕壊id鑾峰彇绠＄悊鏉冮檺鏁版嵁
	public PageData findGLbyrid(PageData pd) throws Exception {
		return (PageData) dao.findForObject("RoleMapper.findGLbyrid", pd);
	}
	
	//閫氳繃褰撳墠鐧诲綍鐢ㄧ殑瑙掕壊id鑾峰彇鐢ㄦ埛鏉冮檺鏁版嵁
	public PageData findYHbyrid(PageData pd) throws Exception {
		return (PageData) dao.findForObject("RoleMapper.findYHbyrid", pd);
	}
	
	//鍒楀嚭姝よ鑹蹭笅鐨勬墍鏈夌敤鎴�
	public List<PageData> listAllUByRid(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("RoleMapper.listAllUByRid", pd);
		
	}
	
	//鍒楀嚭姝よ鑹蹭笅鐨勬墍鏈変細鍛�
	public List<PageData> listAllAppUByRid(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("RoleMapper.listAllAppUByRid", pd);
		
	}
	
	/**
	 * 鍒楀嚭姝ら儴闂ㄧ殑鎵�鏈変笅绾�
	 */
	public List<Role> listAllRolesByPId(PageData pd) throws Exception {
		return (List<Role>) dao.findForList("RoleMapper.listAllRolesByPId", pd);
		
	}
	
	//鍒楀嚭K鏉冮檺琛ㄩ噷鐨勬暟鎹� 
	public List<PageData> listAllkefu(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("RoleMapper.listAllkefu", pd);
	}
	
	//鍒楀嚭G鏉冮檺琛ㄩ噷鐨勬暟鎹� 
	public List<PageData> listAllGysQX(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("RoleMapper.listAllGysQX", pd);
	}
	
	//鍒犻櫎K鏉冮檺琛ㄩ噷瀵瑰簲鐨勬暟鎹�
	public void deleteKeFuById(String ROLE_ID) throws Exception {
		dao.delete("RoleMapper.deleteKeFuById", ROLE_ID);
	}
	
	//鍒犻櫎G鏉冮檺琛ㄩ噷瀵瑰簲鐨勬暟鎹�
	public void deleteGById(String ROLE_ID) throws Exception {
		dao.delete("RoleMapper.deleteGById", ROLE_ID);
	}
	
	public void deleteRoleById(String ROLE_ID) throws Exception {
		dao.delete("RoleMapper.deleteRoleById", ROLE_ID);
		
	}

	public Role getRoleById(String roleId) throws Exception {
		return (Role) dao.findForObject("RoleMapper.getRoleById", roleId);
		
	}

	public void updateRoleRights(Role role) throws Exception {
		dao.update("RoleMapper.updateRoleRights", role);
	}
	
	/**
	 * 鏉冮檺(澧炲垹鏀规煡)
	 */
	public void updateQx(String msg,PageData pd) throws Exception {
		dao.update("RoleMapper."+msg, pd);
	}
	
	/**
	 * 瀹㈡湇鏉冮檺
	 */
	public void updateKFQx(String msg,PageData pd) throws Exception {
		dao.update("RoleMapper."+msg, pd);
	}
	
	/**
	 * Gc鏉冮檺
	 */
	public void gysqxc(String msg,PageData pd) throws Exception {
		dao.update("RoleMapper."+msg, pd);
	}
	
	/**
	 * 缁欏叏閮ㄥ瓙鑱屼綅鍔犺彍鍗曟潈闄�
	 */
	public void setAllRights(PageData pd) throws Exception {
		dao.update("RoleMapper.setAllRights", pd);
		
	}
	
	/**
	 * 娣诲姞
	 */
	public void add(PageData pd) throws Exception {
		dao.findForList("RoleMapper.insert", pd);
	}
	
	/**
	 * 淇濆瓨瀹㈡湇鏉冮檺
	 */
	public void saveKeFu(PageData pd) throws Exception {
		dao.findForList("RoleMapper.saveKeFu", pd);
	}
	
	/**
	 * 淇濆瓨G鏉冮檺
	 */
	public void saveGYSQX(PageData pd) throws Exception {
		dao.findForList("RoleMapper.saveGYSQX", pd);
	}
	
	/**
	 * 閫氳繃id鏌ユ壘
	 */
	public PageData findObjectById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("RoleMapper.findObjectById", pd);
	}
	
	/**
	 * 缂栬緫瑙掕壊
	 */
	public PageData edit(PageData pd) throws Exception {
		return (PageData)dao.findForObject("RoleMapper.edit", pd);
	}
	
	
}
