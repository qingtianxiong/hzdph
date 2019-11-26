package com.fh.service.SCMservice;


	import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;

	@Service("xsbbService")
	public class xsbbService{

		@Resource(name = "daoSupport")
		private DaoSupport dao;
		
	
		public List<PageData> list(Page page)throws Exception{
			return (List<PageData>)dao.findForList("R13","xsbbMapper.datalistPage", page);
		}
		
		public List<PageData> xslist(Page page)throws Exception{
			return (List<PageData>)dao.findForList("R13","xsbbMapper.xslist", page);
		}
		
		public List<PageData> lslist(Page page)throws Exception{
			return (List<PageData>)dao.findForList("R13","xsbbMapper.lsdatalistPage", page);
		}
		
		
		public List<PageData> rkthlist(PageData pd)throws Exception{
			return (List<PageData>)dao.findForList("R13","xsbbMapper.rkthlist", pd);
		}
		
		
		public PageData rkthConut(Page page) throws Exception {
			return (PageData) dao.findForObject("R13","xsbbMapper.rkthConut", page);
		}
		
		public PageData xsmxConut(Page page) throws Exception {
			return (PageData) dao.findForObject("R13","xsbbMapper.xsmxConut", page);
		}
		
		public PageData zxspConut(Page page) throws Exception {
			return (PageData) dao.findForObject("R13","xsbbMapper.zxspConut", page);
		}
		
		public PageData fkcConut(Page page) throws Exception {
			return (PageData) dao.findForObject("R13","xsbbMapper.fkcConut", page);
		}
		
		public PageData qhspConut(Page page) throws Exception {
			return (PageData) dao.findForObject("R13","xsbbMapper.qhspConut", page);
		}
		
		public PageData xsqsConut(Page page) throws Exception {
			return (PageData) dao.findForObject("R13","xsbbMapper.xsqsConut", page);
		}
		
		public List<PageData> zxsplist(Page page)throws Exception{
			return (List<PageData>)dao.findForList("R13","xsbbMapper.zxspdatalistPage", page);
		}
		
		public List<PageData> fkclist(Page page)throws Exception{
			return (List<PageData>)dao.findForList("R13","xsbbMapper.fkcdatalistPage", page);
		}
		
		public List<PageData> qhsplist(Page page)throws Exception{
			return (List<PageData>)dao.findForList("R13","xsbbMapper.qhspdatalistPage", page);
		}
		/*
		 * public List<PageData> findShopcode(PageData pd)throws Exception{ return
		 * (List<PageData>) dao.findForList("R13","xsbbMapper.findShopcode", pd); }
		 */
		
		public List<PageData> rkthxqList(PageData pd)throws Exception{
			return (List<PageData>)dao.findForList("R13","xsbbMapper.rkthxqList", pd);
		}
		
		public PageData rkthxqConut(PageData pd) throws Exception {
			return (PageData) dao.findForObject("R13","xsbbMapper.rkthxqConut", pd);
		}
		
		public PageData rkthxqhz(PageData pd) throws Exception {
			return (PageData) dao.findForObject("R13","xsbbMapper.rkthxqhz", pd);
		}
		
		public PageData spmxhz(PageData pd) throws Exception {
			return (PageData) dao.findForObject("R13","xsbbMapper.spmxhz", pd);
		}
		
		public List<PageData> listDot(Page page)throws Exception{
			return (List<PageData>)dao.findForList("R13","xsbbMapper.selectDot", page);
		}
		
		public List<PageData> listDot1(Page page)throws Exception{
			return (List<PageData>)dao.findForList("R13","xsbbMapper.selectDot1", page);
		}
		
		public List<PageData> xsmxecel(Page page)throws Exception{
			return (List<PageData>)dao.findForList("R13","xsbbMapper.xsmxecel", page);
		}
	}
