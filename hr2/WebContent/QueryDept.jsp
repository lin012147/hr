<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>查询部门</title>
		<link rel="stylesheet" href="js/bootstrap/css/bootstrap.css" />
	</head>
	<body>
		
		<div class="container">
			
			<ol class="breadcrumb">
				<li><a href="#">人力资源管理系统</a></li>
				<li><a href="#">部门管理</a></li>
				<li>查询部门数据</li>
			</ol>
			
			<h2 class="page-header">部门数据列表</h2>
			
			<table id="dataTable" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>部门编号</th>
						<th>部门名称</th>
						<th>部门地址</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>	
					<c:forEach items="${list}" var="dept">							
					<tr>
						<td>${dept.deptId}</td>
						<td>${dept.deptName }</td>
						<td>${dept.deptLoc }</td>
						<td>
							<a href="GetDeptServlet?deptId=${dept.deptId}" class="btn btn-primary btn-sm update-btn">
								<span class="glyphicon glyphicon-pencil"></span>
								修改
							</a>
							<button onclick="del(${dept.deptId});" type="button" class="btn btn-danger btn-sm del-btn">
								<span class="glyphicon glyphicon-trash"></span>
								删除
							</button>
						</td>
					</tr>	
					</c:forEach>				
				</tbody>				
			</table>
			
			<div class="row">
				<div class="col-sm-offset-4 col-sm-4">
					<a href="AddDept.html" class="btn btn-success btn-block">
						<span class="glyphicon glyphicon-plus"></span>
						增加新部门
					</a>
				</div>
				<div class="col-sm-4">
					<a href="javascript:writeExcel();" class="btn btn-info btn-block">
						<span class="glyphicon glyphicon-download"></span>
						下载excel报表
					</a>
				</div>
			</div>
			
		</div>		
		
		<script type="text/javascript" src="js/jquery.js" ></script>
		<script type="text/javascript" src="js/global.js" ></script>
		<script type="text/javascript" src="js/bootstrap/js/bootstrap.js" ></script>
		<script type="text/javascript" src="js/layer/layer.js" ></script>
		
		<script>
		
			//删除部门
			function del(deptId) {
				
				var flag = window.confirm("确定要删除此部门吗");
				
				if (flag) {
					location.href = 'DelDeptServlet?deptId=' + deptId;
				}
				
			}
						
			//下载excel报表
			function writeExcel() {
				
				location.href = baseUrl + "/WriteDeptExcelServlet";
				
			}
		</script>
		
	</body>
</html>