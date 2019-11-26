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
					<form action="xsqs/lslist.do" method="post" name="Form" id="Form">
						<table>
							<tr>
								<td>查询：</td>
								<td><input class="span10 date-picker" name="TIMESTART"
									id="lastLoginStart" value="${pd.lastLoginStart}" type="text"
									data-date-format="yyyy-mm-dd" readonly="readonly"
									style="width: 88px;" placeholder="开始日期" /></td>
								<td>至</td>
								<td><input class="span10 date-picker" name="TIMEEND"
									id="lastLoginEnd" value="${pd.lastLoginEnd}" type="text"
									data-date-format="yyyy-mm-dd" readonly="readonly"
									style="width: 88px;" placeholder="结束日期" /></td>
								<td><span class="input-icon"><input
										autocomplete="off" id="nav-search-input" type="text"
										name="WS_ID" value="${pd.WS_ID}" placeholder="门店号" /> <i
										id="nav-search-icon" class="icon-search"></i> </span></td>

							
								<td style="vertical-align: top;"><button
										class="btn btn-mini btn-light" onclick="search();" title="检索">
										<i id="nav-search-icon" class="icon-search"></i>
									</button></td>
								<!--<c:if test="${QX.cha == 1 }">
								
									<td style="vertical-align: top;"><a
										class="btn btn-mini btn-light" onclick="toExcel();"
										title="导出到EXCEL"><i id="nav-search-icon"
											class="icon-download-alt"></i></a></td>
								</c:if>-->
							</tr>
						</table>
						<!-- 检索  -->


						<table id="table_report"
							class="table table-striped table-bordered table-hover">

							<thead>
								<tr>
									<th class="center">序号</th>
									<th class="center">订单类型</th>
									<th class="center">订单号</th>
									<th class="center">合同号</th>
									<th class="center">门店号</th>
									<th class="center">门店名称</th>
									<th class="center">订单日期</th>
									<th class="center">到货日期</th>
									<th class="center">录入时间</th>
									<th class="center">录入人</th>
									<th class="center">备注</th>
									
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
												    <td style="text-align:left">${var.BILLTYPE_DESC}</td>
												    <td style="text-align:left"><a href="xsqs/goEdit.do?SHEET_ID=${var.SHEET_ID}">${var.SHEET_ID}</a></td>
												    <td style="text-align:right">${var.CONTRACT_ID}</td>
												    <td style="text-align:right">${var.WS_ID}</td>
												    <td style="text-align:left">${var.SHOP_NAME}</td>
												    <td style="text-align:right">${var.SHEET_DT}</td>	
												    <td style="text-align:right">${var.DHRQ}</td>
												    <td style="text-align:right">${var.OPER_DT}</td>
												    <td style="text-align:right">${var.OPER_ID}</td>											    
												    <td style="text-align:right">${var.M_NOTE}</td>
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
			window.location.href='<%=basePath%>IntegratedQuery/excel.do';
		}
		</script>

</body>
</html>

