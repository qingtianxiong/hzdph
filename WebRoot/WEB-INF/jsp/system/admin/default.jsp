<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<!-- jsp文件头和头部 -->
<%@ include file="top.jsp"%>

</head>
<body>

	<div class="container-fluid" id="main-container">
		

			<div id="page-content" class="clearfix">

				<div class="page-header position-relative">
					<h1>
						后台首页 <small><i class="icon-double-angle-right"></i> </small>
					</h1>
				</div>
				<!--/page-header-->

				<div class="row-fluid">
					<div id="main1" style="width: 1250px;height:550px;margin-left:10px;margin-top:25px;"></div>	
					
					<div id="main" style="width: 1250px;height:550px;margin-left:10px;margin-top:25px;"></div>	
				</div>
				<!--/row-->

			</div>
		<!-- #main-content -->
	</div>
	<!--/.fluid-container#main-container-->
	<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse"> <i
		class="icon-double-angle-up icon-only"></i>
	</a>
	<!-- basic scripts -->
	<script src="static/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="static/js/echarts/echarts.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/jquery-ui-1.10.2.custom.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.ui.touch-punch.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.easy-pie-chart.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.sparkline.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.flot.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.flot.pie.min.js"></script>
	<script type="text/javascript" src="static/js/jquery.flot.resize.min.js"></script>
	<!-- ace scripts -->
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>
	<!-- inline scripts related to this page -->


	<script type="text/javascript">

		$(top.hangge());
		
		var myChart = echarts.init(document.getElementById('main1'));
		option = {
			    title: {
			        text: '月销售',
			        subtext: ''
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['最高销售额','最低销售额']
			    },
			    toolbox: {
			        show: true,
			        feature: {
			            dataZoom: {
			                yAxisIndex: 'none'
			            },
			            dataView: {readOnly: false},
			            magicType: {type: ['line', 'bar']},
			            restore: {},
			            saveAsImage: {}
			        }
			    },
			    xAxis:  {
			        type: 'category',
			        boundaryGap: false,
			        data: [<c:forEach var="a" items="${sylist}">'${a.RQ}',</c:forEach>""]
			    },
			    yAxis: {
			        type: 'value',
			        axisLabel: {
			            formatter: '{value} 元'
			        }
			    },
			    series: [
			        {
			        	//控制线条的颜色
						itemStyle : {
							normal : {
								color : '#ff0000',
								borderColor : 'rgba(137,189,2,0.27)',
								borderWidth : 12

							}
						},
						
			            name:'本月销售',
			            type:'line',
			            data:[<c:forEach var="a" items="${bylist}">'${a.XSJE}',</c:forEach>""],
			            markPoint: {
			                data: [
			                    {type: 'max', name: '最大值'},
			                    {type: 'min', name: '最小值'}
			                ]
			            },
			            markLine: {
			                data: [
			                    {type: 'average', name: '平均值'}
			                ]
			            }
			        },
			        {
			        	//控制线条的颜色
						itemStyle : {
							normal : {
								color : '#0000ff',
								borderColor : 'rgba(137,189,2,0.27)',
								borderWidth : 12

							}
						},
						
			            name:'上月销售',
			            type:'line',
			            data:[<c:forEach var="a" items="${sylist}">'${a.XSJE}',</c:forEach>""],
			            markPoint: {
			                data: [
			                    /* {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5} */
			                    {type: 'max', name: '最大值'},
			                    {type: 'min', name: '最小值'}
			                ]
			            },
			            markLine: {
			                data: [
			                    {type: 'average', name: '平均值'},
			                    [{
			                        symbol: 'none',
			                        x: '90%',
			                        yAxis: 'max'
			                    }, {
			                        symbol: 'circle',
			                        label: {
			                            normal: {
			                                position: 'start',
			                                formatter: '最大值'
			                            }
			                        },
			                        type: 'max',
			                        name: '最高点'
			                    }]
			                ]
			            }
			        }
			    ]
			};
		
		 myChart.setOption(option);
		
		 
		 /* var myChart1 = echarts.init(document.getElementById('main'));
			option1 = {
				    title : {
				        text: '上本月销售',
				        subtext: ''
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['最高销售额','最低销售额']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : [<c:forEach var="a" items="${sylist}">'${a.RQ}',</c:forEach>""]
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            axisLabel : {
				                formatter: '{value} 元'
				            }
				        }
				    ],
				    series : [
				        {

				        	//控制线条的颜色
							itemStyle : {
								normal : {
									color : '#0000ff',
									borderColor : 'rgba(137,189,2,0.27)',
									borderWidth : 12

								}
							},

				            name:'最高销售额',
				            type:'line',
				            data:[<c:forEach var="a" items="${sylist}">'${a.XSJE}',</c:forEach>""],
				            markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
				                ]
				            },
				            markLine : {
				                data : [
				                    {type : 'average', name: '平均值'}
				                ]
				            }
				        
				        }
				    ]
				};
			 myChart1.setOption(option1); */
	</script>
</body>
</html>
