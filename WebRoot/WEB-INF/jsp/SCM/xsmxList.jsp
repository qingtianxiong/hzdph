<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="../system/admin/top.jsp"%>
</head>
<body>

	<div class="container-fluid" id="main-container">


		<div id="page-content" class="clearfix">

			<div class="row-fluid">

				<div class="row-fluid">

					<!-- 检索  -->
					<form action="xsbb/xsmxlist.d" method="post" name="Form" id="Form">
						<table>
							<tr>
								<td>查询：</td>
								<td><input class="span10 date-picker" name="lastLoginStart"
									id="lastLoginStart" value="${pd.lastLoginStart}" type="text"
									data-date-format="yyyy-mm-dd" readonly="readonly"
									style="width: 88px;" placeholder="开始日期" /></td>
								<td>至</td>
								<td><input class="span10 date-picker" name="lastLoginEnd"
									id="lastLoginEnd" value="${pd.lastLoginEnd}" type="text"
									data-date-format="yyyy-mm-dd" readonly="readonly"
									style="width: 88px;" placeholder="结束日期" /></td>
								<td><span class="input-icon"><input
										autocomplete="off" id="nav-search-input" type="text"
										name="GOODS" value="${pd.GOODS}" placeholder="品名规格" /> <i
										id="nav-search-icon" class="icon-search"></i> </span></td>
								<td><span class="input-icon"><input
										autocomplete="off" id="nav-search-input" type="text"
										name="GOODS_ID" value="${pd.GOODS_ID}" placeholder="商品编码" /> <i
										id="nav-search-icon" class="icon-search"></i> </span></td>
									
								<td style="vertical-align: top;">
									<select class="chzn-select" name="SHOP_ID" data-placeholder="选择门店" style="vertical-align: top; width: 130px;">
											<option value=""></option>
											<c:forEach items="${listDot}" var="t">
												<option value="${t.SHOP_ID }" <c:if test="${t.SHOP_ID==pd.SHOP_ID}"><c:out value="selected"/></c:if>>
													${t.SHOP_NAME}
												</option>
												<%-- <option value="${t.SHOP_ID}" > ${t.SHOP_NAME }</option> --%>
											</c:forEach>
									</select>
								</td>
												
								<td style="vertical-align: top;"><button
										class="btn btn-mini btn-light" onclick="search();" title="检索">
										<i id="nav-search-icon" class="icon-search"></i>
									</button></td>
								<c:if test="${QX.cha == 1 }">
								
									<td style="vertical-align: top;"><a
										class="btn btn-mini btn-light" onclick="toExcel();"
										title="导出到EXCEL"><i id="nav-search-icon"
											class="icon-download-alt"></i></a></td>
								</c:if>
							</tr>
						</table>
						<!-- 检索  -->


						<table id="table_report"
							class="table table-striped table-bordered table-hover">

							<thead>
								<tr>
									<th class="center">序号</th>
									<th class="center">商品编码</th>
									<th class="center">品名规格</th>
									<th class="center">品牌</th>
									<th class="center">销售数量</th>
									<th class="center">销价金额</th>
									<th class="center">退货数量</th>
									<th class="center">退货金额</th>
									<th class="center">总折扣</th>
									<th class="center">扣率（%）</th>
									<th class="center">实际销售</th>
									<th class="center">毛利额</th>
									<th class="center">毛利率（%）</th>
									<!-- <th class="center">修改</th> -->
								</tr>
							</thead>

							<tbody>

								<!-- 开始循环 -->
								<c:choose>
									<c:when test="${not empty varList}">
										<c:if test="${QX.cha == 1 }">
											<c:forEach items="${varList}" var="var" varStatus="vs">
												<tr>
													<td class='center' style="width: 30px;">${vs.index+1}</td>
												    <td style="text-align:left">${var.GOODS_ID}</td>
												    <td style="text-align:left">${var.GOODS}</td>
												    <td style="text-align:right">${var.PP_DESC}</td>
												    <td style="text-align:right">${var.XSSL}</td>
												    <td style="text-align:right">${var.XSJE}</td>
												    <td style="text-align:right">
												    	<c:if test="${var.XSTHSL > 0 }">
															<font style="color:red;font-weight:bold">${var.XSTHSL}</font>
														</c:if>
												    	<c:if test="${var.XSTHSL <= 0 }">
															${var.XSTHSL}
														</c:if>
												    </td>	
												    <td style="text-align:right">
												    	<c:if test="${var.XSTHJE > 0 }">
															<font style="color:red;font-weight:bold">${var.XSTHJE}</font>
														</c:if>
												    	<c:if test="${var.XSTHJE <= 0 }">
															${var.XSTHJE}
														</c:if>
												    	
												    </td>	
												    <td style="text-align:right">${var.ZZKJE}</td>	
												    <td style="text-align:right">${var.zlv}</td>	
												    <td style="text-align:right">${var.ZRJE}</td>	
												    <td style="text-align:right">${var.PROFIT}</td>	
												    <td style="text-align:right">${var.mlv}</td>	
												    <%-- <td style="width: 30px">
												      <a style="cursor:pointer;" onclick="edit('${var.trader_id}');">修改</a>
												    </td> --%>
												</tr>
												
											</c:forEach>
										</c:if>
										<c:if test="${QX.cha == 0 }">
											<tr>
												<td colspan="100" class="center">您无权查看</td>
											</tr>
										</c:if>
									</c:when>
									<c:otherwise>
										<tr class="main_info">
											<td colspan="100" class="center">没有相关数据</td>
										</tr>
									</c:otherwise>
								</c:choose>

								<tr>
													<td class='center' style="width: 30px;">合计</td>
												    <td style="text-align:right">---</td>
												    <td style="text-align:right">---</td>
												    <td style="text-align:right">---</td>
												    <td style="text-align:right">${spmxhz.XSSL}</td>
												    <td style="text-align:right">${spmxhz.XSJE}</td>
												    <td style="text-align:right">${spmxhz.XSTHSL}</td>	
												    <td style="text-align:right">${spmxhz.XSTHJE}</td>	
												    <td style="text-align:right">${spmxhz.ZZKJE}</td>	
												    <td style="text-align:right">---</td>	
												    <td style="text-align:right">${spmxhz.ZRJE}</td>	
												    <td style="text-align:right">${spmxhz.PROFIT}</td>	
												    <td style="text-align:right">---</td>	
												    <%-- <td style="width: 30px">
												      <a style="cursor:pointer;" onclick="edit('${var.trader_id}');">修改</a>
												    </td> --%>
												</tr>
							</tbody>
						</table>

						<!-- 分页必备参数 -->
						<input type="hidden" id="example" name="fnt"/>
						<input type="hidden"  name="jmys" value="${pd.jmys}"/>
						<div style="text-align: center">
							<input type="text" name="yema" style="width:30px;padding-left:8px;margin-top:10px;border: 2px solid rgb(135,184,127)"  onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')">
							<input type="button" value="跳转"  onclick="fenye('1');" style="color: #fff;background-color: rgb(135,184,127);padding:5px 10px">
							<input type="button" value="首页"  onclick="fenye('2');" style="color: #fff;background-color: rgb(135,184,127);padding:5px 10px">
							<input type="button" value="上一页"  onclick="fenye('3');" style="color: #fff;background-color: rgb(135,184,127);padding:5px 10px">
							<input type="button" value="下一页"  onclick="fenye('4');" style="color: #fff;background-color: rgb(135,184,127);padding:5px 10px">
							<input type="button" value="尾页"  onclick="fenye('5');" style="color: #fff;background-color: rgb(135,184,127);padding:5px 10px">
							<a >第${pd.jmys}页</a> 
							
						</div>
					</form>
				</div>




				<!-- PAGE CONTENT ENDS HERE -->
			</div>
			<!--/row-->

		</div>
		<!--/#page-content-->
	</div>
	<!--/.fluid-container#main-container-->

	<!-- 返回顶部  -->
	<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse"> <i
		class="icon-double-angle-up icon-only"></i>
	</a>

	<!-- 引入 -->
	<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>

	<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
	<!-- 下拉框 -->
	<script type="text/javascript"
		src="static/js/bootstrap-datepicker.min.js"></script>
	<!-- 日期框 -->
	<script type="text/javascript" src="static/js/bootbox.min.js"></script>
	
	<!-- 确认窗口 -->
	<!-- 引入 -->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<!--提示框-->
	<script type="text/javascript">
		
		$(top.hangge());
		
		//检索
		function search(){
			top.jzts();
			$("#Form").submit();
		}
		//分页
		function fenye(a){
			document.getElementById("example").value=a; 
			top.jzts();
			$("#Form").submit();
		}
<%-- 		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>Sales/goAdd.do';
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		} --%>
<%-- 
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('TRADER_ID').length;i++)
					{
						  if(document.getElementsByName('TRADER_ID')[i].checked){
						  	if(str=='') str += document.getElementsByName('TRADER_ID')[i].value;
						  	else str += ',' + document.getElementsByName('TRADER_ID')[i].value;
						  }
					}
					if(str==''){
						bootbox.dialog("您没有选择任何内容!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}
							 ]
						);
						
						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>Sales/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage(${page.currentPage});
									 });
									 location.href="Sales/list.do";
								}
							});
						}
					}
				}
			});
		}
		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>Sales/goEdit.do?TRADER_ID='+Id;
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		} --%>
		</script>

	<script type="text/javascript">
		
		$(function() {
			
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
			
		});
		
		
		
		
		//导出excel
		function toExcel(){
			document.Form.action="xsbb/excel.do";
			Form.submit(); 
			<%-- window.location.href='<%=basePath%>xsbb/excel.do'; --%>
		}
		</script>

</body>
</html>

