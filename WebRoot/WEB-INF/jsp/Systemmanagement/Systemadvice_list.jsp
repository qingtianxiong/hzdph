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
	.advise-title-ctn{text-align:center;margin-top:80px;}
	
</style>
<body>

	<div class="container-fluid" id="main-container">


		<div id="page-content" class="clearfix">

			<div class="row-fluid">

				<div class="row-fluid">

					<!-- 检索  -->
					<form action="Systemadvice/list.do" method="post" name="Form" id="Form">
						
					<div class="page-advise">
                    <div class="advise-title-ctn">
                        <h1 class="advise-title">投诉与建议</h1>
                        <!-- <h3 class="advise-sub-title">您的参与将帮助我们改进产品及服务。</h3>
                        <p class="advise-label">非常感谢您使用惠智产品，如果您有提议、投诉及相关建议，可以通过以下方式联系我们:</p> -->
                    </div>
                    
                    <div class="main" style="width=100%;height:auto;padding-left:20%;padding-top:5%;">
                    <table style="width:100%;">               
                <tbody><tr>
                    <td style=" width:150px; text-align:right; padding-right:25px;font-size:18px;font-family:黑体;">标题</td>
                    <td><input name="title" type="text" id="username" placeholder="建议的标题" style="height:25px;border:2px solid #cccccc; "></td>
                </tr>
                               
                <tr>
                    <td style=" width:150px; text-align:right; padding-right:25px;font-size:18px;font-family:黑体;">本人联系电话</td>
                    <td><input name="contact" type="text" id="contact" placeholder="电话或邮件地址" style="height:25px;border:2px solid #cccccc; "></td>
                </tr>
 
                <tr>
                    <td style=" width:150px; text-align:right; padding-right:25px;font-size:18px;font-family:黑体;">意见及建议:</td>
                </tr>
                <tr> 
                	<td style=" width:150px; text-align:right; padding-right:25px;font-size:18px;font-family:黑体;"> </td>   
                    <td><textarea name="comment" id="comment"   placeholder="请输入您的建议：" style="width:70%;height:150px;resize:none;border:2px solid #cccccc;"></textarea></td>
                </tr>               
                 
                <tr>
	                <td colspan="2" align="center" >
	                	
                		<%-- <c:if test="${QX.add == 1 }">
						 <a class="btn btn-small btn-success" >提交建议</a>
						</c:if>  --%>
						
	                	<input type="submit" id="button"class="btn btn-small btn-success" value="提交建议">
	                </td>
                </tr>
</tbody></table>
				</div>
                    <!-- <div class="advise-contact-ctn">
                        <ul class="clearfix">
                            <li>
                                <i></i>
                                <p>联系之前为您</p>
                                <p>服务的客服</p>
                            </li>
                            <li>
                                <i></i>
                                <p>拨打惠智投诉电话</p>
                                <p><em>0898-66501955</em></p>
                            </li>
                            <li>
                                <i></i>
                                <p>发送邮件到惠智邮箱</p>
                                <p>329238824@qq.com</p>
                            </li>
                            <li class="last">
                                <i></i>
                                <p>给我们在线留言</p>
                                <p>我们会尽快回复</p>
                            </li>
                        </ul>
                    </div> -->
                    </div>
                        <!-- <div class="advise-form-item">
                            <p class="advise-label"><span class="required">*</span>选择反馈类型：</p>
                            <ul class="advise-types clearfix J_types">
                                <li>
                                    <span class="advise-txt">订单支付类</span>
                                    <span class="poptip hide">
                                        <i class="icon icon-tri"></i>下单交易过程中的问题
                                    </span>
                                </li>
                                <li>
                                    <span class="advise-txt">系统产品类</span>
                                    <span class="poptip hide">
                                        <i class="icon icon-tri"></i>惠智系统产品方面的问题
                                    </span>
                                </li>
                                <li>
                                    <span class="advise-txt">平台体验类</span>
                                    <span class="poptip hide">
                                        <i class="icon icon-tri"></i>PC、手机、平板等终端设备使用方面的问题
                                    </span>
                                </li>
                                <li class="current">
                                    <span class="advise-txt">客户服务类</span>
                                    <span class="poptip hide">
                                        <i class="icon icon-tri"></i>线下服务方的问题
                                    </span>
                                </li>
                                <li>
                                    <span class="advise-txt">活动促销类</span>
                                    <span class="poptip hide">
                                        <i class="icon icon-tri"></i>活动、促销方面的问题
                                    </span>
                                </li>
                                <li>
                                    <span class="advise-txt">金融类</span>
                                    <span class="poptip hide">
                                        <i class="icon icon-tri"></i>金融方面的问题
                                    </span>
                                </li>
                                <li>
                                    <span class="advise-txt">其他</span>
                                </li>
                            </ul>
                        </div>
                        <div class="advise-form-item">
                            <p class="advise-label"><span class="required">*</span>请详细描述您的建议、意见、问题等：</p>
                            <textarea name="J_detail" id="J_detail"  class="J_detail" rows="8" data-field="co_advantage" placeholder="必填" maxlength="1000" ></textarea>
                             <span class="tip"><i class="icon"></i><span class="tip-txt"></span></span>
                        </div>
                        <div class="advise-form-item">
                            <span class="advise-label">电子邮箱</span>
                            <input type="text" placeholder="必填" id="J_email" class="J_input input" data-field="email" maxlength="50"/>
                            <span class="tip"><i class="icon"></i><span class="tip-txt"></span></span>
                        </div>
                        <div class="advise-form-item">
                            <span class="advise-label">电话号码</span>
                            <input type="text" placeholder="必填" id="J_phone" class="J_input input" data-field="phone" maxlength="11"/>
                            <span class="tip"><i class="icon"></i><span class="tip-txt"></span></span>
                        </div>
                        <div class="btn-block clearfix">
                            <a href="javascript:;" class="form-btn positive-form-btn J_submit">提交</a>
                        </div> -->
						
						
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
			 diag.URL = '<%=basePath%>Systemadvice/goAdd.do';
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
					var url = "<%=basePath%>Systemadvice/delete.do?GOODS_ID="+Id+"&tm="+new Date().getTime();
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
			 diag.URL = '<%=basePath%>Systemadvice/goEdit.do?GOODS_ID='+Id;
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
								url: '<%=basePath%>Systemadvice/deleteAll.do?tm='+new Date().getTime(),
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
			window.location.href='<%=basePath%>Systemadvice/excel.do';
		}
		</script>

</body>
</html>

