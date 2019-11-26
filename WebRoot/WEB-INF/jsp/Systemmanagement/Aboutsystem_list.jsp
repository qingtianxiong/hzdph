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
<style type="text/css">
	.con{margin-top:50px;font-family:黑体;}
	.main{
		width:100%;
		height:200px;
		/* border: 1px solid ; */
		position: relative;
		margin:0;
		padding:0;
		}
	.main p{
		font-size:20px;
		/* color:rgb(128,0,0); */
		font-family:黑体;
	}
	.main p span{
		font-size:18px;
		/* color:rgb(128,0,0); */
		font-family:宋体;
	}	
	.main .p1{
		position: absolute;
		left:30%;
		top:30%;
	}
	.main .p2{
		position: absolute;
		left:55%;
		top:30%;
	}
	.main .p3{
		position: absolute;
		left:30%;
		top:70%;
	}
	.main .p4{
		position: absolute;
		left:55%;
		top:70%;
	}
	.box{
		height:outo;
		width:100%;
		/* border:1px solid; */
		padding-left:38%;
	}
	.box h4{
		padding-Bottom:20px;
		font-family:黑体;	
	}
	.box h4 span{
		font-size:18px;
		 color:rgb(128,0,0); 
		font-family:宋体;
	}
</style>
<body>

	<div class="container-fluid" id="main-container">


		<div id="page-content" class="clearfix">

			<div class="row-fluid">

				<div class="row-fluid">

					<!-- 检索  -->
					<form action="Aboutsystem/list.do" method="post" name="Form" id="Form">
						
					<div id="main" class="con">
							<h1 style="text-align:center; ">关于我们</h1>
							<!-- <div class="box">
								<p>海南惠智商业信息技术有限公司创立于2006年10月，以“让大数据更简洁”为使命，为客户提供商业软件产品预订服务，产品全面，价格透明，全年365天24小时电话预订，并提供丰富的后续服务和保障。</p>
								<p>倾听客户的声音，我们愿意并能够协助客户成功以最经济的成本、最良好的品质，由此牢不可分的伙伴关系，我们深知客户的成功是公司成长的动力。</p>
								<p>我公司在蓬勃发展的历程中不断探索进取，努力提升技术水准、管理水平，同时注重业务拓展，努力建立与客户的良好沟通。</p>
							</div> -->
						</div>
						<div class="contact-card main">
                    		<!-- <h1>联系我们</h1> -->
                    		<!-- <h3>海南惠智商业信息技术有限公司</h3> -->
                    		<!-- <p>地址：海口市龙华区大同街道华发大厦A505</p>
                    		<p>邮编：570100</p>
                    		<p>电话：0898-66501955</p>
                   	 		<p>传真：0898-66501955</p> -->
                   	 		<p class="p1">版本号：<span>ShopCol Ver2.0</span></p>
                   	 		<p class="p2">许可认证编码：<span>HZSHOPCOL0001</span></p>
                   	 		<p class="p3">许可期限：<span>2019-05-01</span></p>
                   	 		<p class="p4">站点数：<span></span></p>
                		</div>
                		<br>
						<div class="box">
							<!-- <div class="tit"><span>我们的产品</span></div>
							<div class="line"><span>惠智百货系统：</span> </div>
							<div class="line"><span>惠智进销存系统：</span></div>
							<div class="line"><span>惠智餐饮系统：</span></div>
							<div class="line"><span>惠智供应链系统：</span></div>
							<div class="line"><span>惠智网上商城：</span></div> -->
							<h4>公司：海南惠智商业信息技术有限公司</h4>
							<h4>地址：中国 海南省海口市大同路25号B906室</h4>
							<h4>电话：<span>0898-66501995</span></h4>
							<h4>传真：<span>0898-36316501</span></h4>
							<h4>网址：<span>www.huisoft.net</span></h4>
							<h4>电邮：<span>cheng8160@sina.com</span></h4>
		    			</div>
		    			<br>
		    			<!-- <div class="box">
							<div class="tit"><span>为什么选择我们？</span></div>
							<div class="line"><span>产品丰富：</span>精选出性价比高的优质线路，组成丰富的产品线。</div>
							<div class="line"><span>性价比高：</span>同类产品选择途牛更实惠。</div>
							<div class="line"><span>省心便捷：</span>在线轻松预订，专属客服24小时快速反应，足不出户，服务到家</div>
							<div class="line"><span>量身定制：</span>专业团队，丰富的产品线，满足您量身定制的个性化需求。</div>
							<div class="line"><span>双重保障：</span>售中、售后跟踪服务以及质检，使用中出现任何质量问题我们帮您维权到底，使您的权益得到切实保障，选择我们让您拥有双重保障。</div>
 						</div>	 -->
						
						
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
		
		//去会员详细资料页面
		function sendCUSTOMER_ID(CUSTOMER_ID){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="查看会员详细资料";
			 diag.URL = '<%=basePath%>Memberarchives/list.do?CUSTOMER_ID='+CUSTOMER_ID;
			 diag.Width = 1200;
			 diag.Height = 700;
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
			 };
			 diag.show();
		}
		
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>Aboutsystem/goAdd.do';
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
		}
		
		//删除
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>Aboutsystem/delete.do?GOODS_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}
		
		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>Aboutsystem/goEdit.do?GOODS_ID='+Id;
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		}
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
		
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
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
								url: '<%=basePath%>Aboutsystem/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage(${page.currentPage});
									 });
								}
							});
						}
					}
				}
			});
		}
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>Aboutsystem/excel.do';
		}
		</script>

</body>
</html>

