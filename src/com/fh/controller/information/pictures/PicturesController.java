package com.fh.controller.information.pictures;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
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
import com.fh.util.AppUtil;
import com.fh.util.DateUtil;
import com.fh.util.DelAllFile;
import com.fh.util.FileUpload;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.PathUtil;
import com.fh.util.Tools;
import com.fh.util.Watermark;
import com.fh.service.information.pictures.PicturesService;

/** 
 * 绫诲悕绉帮細PicturesController
 * 鍒涘缓浜猴細FH 
 * 鍒涘缓鏃堕棿锛�2015-03-21
 */
@Controller
@RequestMapping(value="/pictures") 
public class PicturesController extends BaseController {
	
	String menuUrl = "pictures/list.do"; //鑿滃崟鍦板潃(鏉冮檺鐢�)
	@Resource(name="picturesService")
	private PicturesService picturesService;
	
	/**
	 * 鏂板
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Object save(
			@RequestParam(required=false) MultipartFile file
			) throws Exception{
		logBefore(logger, "鏂板Pictures");
		Map<String,String> map = new HashMap<String,String>();
		String  ffile = DateUtil.getDays(), fileName = "";
		PageData pd = new PageData();
		if(Jurisdiction.buttonJurisdiction(menuUrl, "add")){
			if (null != file && !file.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//鏂囦欢涓婁紶璺緞
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//鎵ц涓婁紶
			}else{
				System.out.println("涓婁紶澶辫触");
			}
			
			pd.put("PICTURES_ID", this.get32UUID());			//涓婚敭
			pd.put("TITLE", "鍥剧墖");								//鏍囬
			pd.put("NAME", fileName);							//鏂囦欢鍚�
			pd.put("PATH", ffile + "/" + fileName);				//璺緞
			pd.put("CREATETIME", Tools.date2Str(new Date()));	//鍒涘缓鏃堕棿
			pd.put("MASTER_ID", "1");							//闄勫睘涓�
			pd.put("BZ", "鍥剧墖绠＄悊澶勪笂浼�");						//澶囨敞
			//鍔犳按鍗�
			Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);
			picturesService.save(pd);
		}
		map.put("result", "ok");
		return AppUtil.returnObject(pd, map);
	}
	
	/**
	 * 鍒犻櫎
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "鍒犻櫎Pictures");
		PageData pd = new PageData();
		try{
			if(Jurisdiction.buttonJurisdiction(menuUrl, "del")){
				pd = this.getPageData();
				DelAllFile.delFolder(PathUtil.getClasspath()+ Const.FILEPATHIMG + pd.getString("PATH")); //鍒犻櫎鍥剧墖
				picturesService.delete(pd);
			}
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 淇敼
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(
			HttpServletRequest request,
			@RequestParam(value="tp",required=false) MultipartFile file,
			@RequestParam(value="tpz",required=false) String tpz,
			@RequestParam(value="PICTURES_ID",required=false) String PICTURES_ID,
			@RequestParam(value="TITLE",required=false) String TITLE,
			@RequestParam(value="MASTER_ID",required=false) String MASTER_ID,
			@RequestParam(value="BZ",required=false) String BZ
			) throws Exception{
		logBefore(logger, "淇敼Pictures");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if(Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
			pd.put("PICTURES_ID", PICTURES_ID);		//鍥剧墖ID
			pd.put("TITLE", TITLE);					//鏍囬
			pd.put("MASTER_ID", MASTER_ID);			//灞炰簬ID
			pd.put("BZ", BZ);						//澶囨敞
			
			if(null == tpz){tpz = "";}
			String  ffile = DateUtil.getDays(), fileName = "";
			if (null != file && !file.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile;		//鏂囦欢涓婁紶璺緞
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID());				//鎵ц涓婁紶
				pd.put("PATH", ffile + "/" + fileName);				//璺緞
				pd.put("NAME", fileName);
			}else{
				pd.put("PATH", tpz);
			}
			Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);//鍔犳按鍗�
			picturesService.edit(pd);				//鎵ц淇敼鏁版嵁搴�
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 鍒楄〃
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "鍒楄〃Pictures");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			
			String KEYW = pd.getString("keyword");
			
			if(null != KEYW && !"".equals(KEYW)){
				KEYW = KEYW.trim();
				pd.put("KEYW", KEYW);
			}
			
			page.setPd(pd);
			List<PageData>	varList = picturesService.list(page);	//鍒楀嚭Pictures鍒楄〃
			mv.setViewName("information/pictures/pictures_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//鎸夐挳鏉冮檺
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 鍘绘柊澧為〉闈�
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "鍘绘柊澧濸ictures椤甸潰");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("information/pictures/pictures_add");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 鍘讳慨鏀归〉闈�
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "鍘讳慨鏀筆ictures椤甸潰");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = picturesService.findById(pd);	//鏍规嵁ID璇诲彇
			mv.setViewName("information/pictures/pictures_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 鎵归噺鍒犻櫎
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "鎵归噺鍒犻櫎Pictures");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			if(Jurisdiction.buttonJurisdiction(menuUrl, "del")){
				List<PageData> pdList = new ArrayList<PageData>();
				List<PageData> pathList = new ArrayList<PageData>();
				String DATA_IDS = pd.getString("DATA_IDS");
				if(null != DATA_IDS && !"".equals(DATA_IDS)){
					String ArrayDATA_IDS[] = DATA_IDS.split(",");
					pathList = picturesService.getAllById(ArrayDATA_IDS);
					//鍒犻櫎鍥剧墖
					for(int i=0;i<pathList.size();i++){
						DelAllFile.delFolder(PathUtil.getClasspath()+ Const.FILEPATHIMG + pathList.get(i).getString("PATH"));
					}
					picturesService.deleteAll(ArrayDATA_IDS);
					pd.put("msg", "ok");
				}else{
					pd.put("msg", "no");
				}
				pdList.add(pd);
				map.put("list", pdList);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出Pictures到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("标题");	//1
			titles.add("文件名");	//2
			titles.add("路径");	//3
			titles.add("创建时间");	//4
			titles.add("属于");	//5
			titles.add("备注");	//6
			dataMap.put("titles", titles);
			List<PageData> varOList = picturesService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("TITLE"));	//1
				vpd.put("var2", varOList.get(i).getString("NAME"));	//2
				vpd.put("var3", varOList.get(i).getString("PATH"));	//3
				vpd.put("var4", varOList.get(i).getString("CREATETIME"));	//4
				vpd.put("var5", varOList.get(i).getString("MASTER_ID"));	//5
				vpd.put("var6", varOList.get(i).getString("BZ"));	//6
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
	
	
	//鍒犻櫎鍥剧墖
	@RequestMapping(value="/deltp")
	public void deltp(PrintWriter out) {
		logBefore(logger, "鍒犻櫎鍥剧墖");
		try{
			PageData pd = new PageData();
			pd = this.getPageData();
			String PATH = pd.getString("PATH");													 		//鍥剧墖璺緞
			DelAllFile.delFolder(PathUtil.getClasspath()+ Const.FILEPATHIMG + pd.getString("PATH")); 	//鍒犻櫎鍥剧墖
			if(PATH != null){
				picturesService.delTp(pd);																//鍒犻櫎鏁版嵁涓浘鐗囨暟鎹�
			}	
			out.write("success");
			out.close();
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
	}
	
	/* ===============================鏉冮檺================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro绠＄悊鐨剆ession
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================鏉冮檺================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
