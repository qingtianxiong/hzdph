package com.fh.service.SCMservice;


	import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;

	@Service("kchzService")
	public class kchzService{

		@Resource(name = "daoSupport")
		private DaoSupport dao;
		
		/* 库存汇总表 */
		public List<PageData> list(Page page)throws Exception{
			return (List<PageData>)dao.findForList("R13","kchzMapper.datalistPage", page);
		}
		
		/* 库存明细表 */
		public List<PageData> lslist(Page page)throws Exception{
			return (List<PageData>)dao.findForList("R13","kchzMapper.lsdatalistPage", page);
		}
		
		public List<PageData> listDot(Page page)throws Exception{
			return (List<PageData>)dao.findForList("R13","kchzMapper.selectDot", page);
		}
		
		public PageData xlistConut(Page page) throws Exception {
			return (PageData) dao.findForObject("R13","kchzMapper.xlistConut", page);
		}
		
		public PageData lslistConut(Page page) throws Exception {
			return (PageData) dao.findForObject("R13","kchzMapper.lslistConut", page);
		}
	}
