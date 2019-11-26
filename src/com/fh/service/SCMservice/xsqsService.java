package com.fh.service.SCMservice;


	import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;

	@Service("xsqsService")
	public class xsqsService{

		@Resource(name = "daoSupport")
		private DaoSupport dao;
		
		/* 新订单 */
		public List<PageData> list(Page page)throws Exception{
			return (List<PageData>)dao.findForList("R13","xsqsMapper.datalistPage", page);
		}
		
		/* 历史新订单 */
		public List<PageData> lslist(Page page)throws Exception{
			return (List<PageData>)dao.findForList("R13","xsqsMapper.lsdatalistPage", page);
		}
		
		public PageData xlistConut(Page page) throws Exception {
			return (PageData) dao.findForObject("R13","xsqsMapper.xlistConut", page);
		}
		
		public PageData lslistConut(Page page) throws Exception {
			return (PageData) dao.findForObject("R13","xsqsMapper.lslistConut", page);
		}
		
		
		public List<PageData> lszdxqList(PageData pd)throws Exception{
			return (List<PageData>)dao.findForList("R13","xsqsMapper.lszdxqList", pd);
		}
		
		public PageData lszdxqConut(PageData pd) throws Exception {
			return (PageData) dao.findForObject("R13","xsqsMapper.lszdxqConut", pd);
		}
		
		public PageData lszdxqhz(PageData pd) throws Exception {
			return (PageData) dao.findForObject("R13","xsqsMapper.lszdxqhz", pd);
		}
	}
