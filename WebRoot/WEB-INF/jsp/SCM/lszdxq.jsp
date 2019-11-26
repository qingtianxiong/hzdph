<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title></title>
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="static/css/font-awesome.min.css" />
<!-- 下拉框 -->
<link rel="stylesheet" href="static/css/chosen.css" />
<link rel="stylesheet" href="static/css/ace.min.css" />
<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
<link rel="stylesheet" href="static/css/ace-skins.min.css" />
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<!--提示框-->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>

<script type="text/javascript">
	$(top.hangge());
	$(document).ready(function(){
		if($("#user_id").val()!=""){
			$("#loginname").attr("readonly","readonly");
			$("#loginname").css("color","gray");
		}
	});
	
	
</script>
</head>
<body>	
	<form action="xsqs/goEdit.do" method="post" name="Form" id="Form">
		<table class="table" style="border:1px solid #000;margin-top:15px;">
			<tr>
				<td style="width:50px;text-align: right;padding-top: 15px;border:1px solid #000">订单类型:</td>
				<td style="width:150px;text-align: left;padding-top: 15px;border:1px solid #000">${pd.BILLTYPE_DESC}</td>
				
				<td style="width:50px;text-align: right;padding-top: 15px;border:1px solid #000">订单号:</td>
				<td style="width:150px;text-align: left;padding-top: 15px;border:1px solid #000">${pd.SHEET_ID}</td>
				
				<td style="width:50px;text-align: right;padding-top: 15px;border:1px solid #000">合同号:</td>
				<td style="width:150px;text-align: left;padding-top: 15px;border:1px solid #000">${pd.CONTRACT_ID}</td>
			</tr>
			
			<tr>
				<td style="width:50px;text-align: right;padding-top: 15px;border:1px solid #000">门店号:</td>
				<td style="width:150px;text-align: left;padding-top: 15px;border:1px solid #000">${pd.WS_ID}</td>
				
				<td style="width:50px;text-align: right;padding-top: 15px;border:1px solid #000">门店名称:</td>
				<td style="width:150px;text-align: left;padding-top: 15px;border:1px solid #000">${pd.SHOP_NAME}</td>
				
				<td style="width:50px;text-align: right;padding-top: 15px;border:1px solid #000">订单日期:</td>
				<td style="width:150px;text-align: left;padding-top: 15px;border:1px solid #000">${pd.SHEET_DT}</td>
			</tr>
			
			<tr>
				<td style="width:50px;text-align: right;padding-top: 15px;border:1px solid #000">到货日期:</td>
				<td style="width:150px;text-align: left;padding-top: 15px;border:1px solid #000">${pd.DHRQ}</td>
				
				<td style="width:50px;text-align: right;padding-top: 15px;border:1px solid #000">录入时间:</td>
				<td style="width:150px;text-align: left;padding-top: 15px;border:1px solid #000">${pd.OPER_DT}</td>
				
				<td style="width:50px;text-align: right;padding-top: 15px;border:1px solid #000">录入人:</td>
				<td style="width:150px;text-align: left;padding-top: 15px;border:1px solid #000">${pd.OPER_ID}</td>
			</tr>
			
			<tr>
				<td style="width:50px;text-align: right;padding-top: 15px;border:1px solid #000">备注:</td>
				<td style="width:150px;text-align: left;padding-top: 15px;border:1px solid #000">${pd.M_NOTE}</td>
				
				<td style="width:50px;text-align: right;padding-top: 15px;border:1px solid #000"></td>
				<td style="width:150px;text-align: left;padding-top: 15px;border:1px solid #000"></td>
				
				<td style="width:50px;text-align: right;padding-top: 15px;border:1px solid #000"></td>
				<td style="width:150px;text-align: left;padding-top: 15px;border:1px solid #000"></td>
			</tr>
		</table>
		<table id="table_report" class="table table-striped table-bordered table-hover">

			<thead>
				<tr>
					<th class="center">序号</th>
					<th class="center">店内码</th>
					<th class="center">商品编码</th>
					<th class="center">商品名称</th>
					<th class="center">规格</th>
					<th class="center">单位</th>
					<th class="center">包装含量</th>
					<th class="center">订货数</th>
					<th class="center">售价</th>
					<th class="center">含税金额</th>
					<th class="center">含税进价</th>
					<th class="center">销售金额</th>
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
								    <td style="text-align:left">${var.ZJM}</td>
								    <td style="text-align:left">${var.GOODS_ID}</td>
								    <td style="text-align:left">${var.GOODS}</td>
								    <td style="text-align:left">${var.GUIGE}</td>
								    <td style="text-align:left">${var.UNIT}</td>	
								    <td style="text-align:right">${var.BZHL}</td>
								    <td style="text-align:right">${var.SL}</td>
								    <td style="text-align:right">${var.PRICE}</td>											    
								    <td style="text-align:right">${var.HSJJJE}</td>
								    <td style="text-align:right">${var.HSJJ}</td>
								    <td style="text-align:right">${var.LSJE}</td>
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
				    <td style="text-align:right">---</td>
				    <td style="text-align:right">---</td>	
				    <td style="text-align:right">---</td>
				    <td style="text-align:right">${rkthxqhz.SL}</td>
				    <td style="text-align:right">---</td>											    
				    <td style="text-align:right">${rkthxqhz.HSJJJE}</td>
				    <td style="text-align:right">---</td>
				    <td style="text-align:right">${rkthxqhz.LSJE}</td>
				</tr>
			</tbody>
		</table>

		
		
	</form>
	
	<!-- 引入 -->
	<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>
	<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
	<!-- 下拉框 -->

	<script type="text/javascript">
		
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		</script>

</body>
</html>