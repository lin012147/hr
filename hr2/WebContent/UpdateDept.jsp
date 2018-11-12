<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>修改部门</title>
		<link rel="stylesheet" href="js/bootstrap/css/bootstrap.css" />
	</head>
	<body>
		
		<div class="container">
			
			<ol class="breadcrumb">
				<li><a href="#">人力资源管理系统</a></li>
				<li><a href="#">部门管理</a></li>
				<li>修改部门</li>
			</ol>
			
			<h2 class="page-header">修改部门</h2>
			
			<form id="form1" method="post" action="UpdateDeptServlet" class="form-horizontal">
				<div class="form-group">
					<label for="" class="col-sm-2">部门名称</label>
					<div class="col-sm-8">
						<input value="${dept.deptName}" type="text" id="deptName" name="deptName" 
							placeholder="请输入部门名称" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label for="" class="col-sm-2">部门地址</label>
					<div class="col-sm-8">
						<input value="${dept.deptLoc}" type="text" id="deptLoc" name="deptLoc" 
							placeholder="请输入部门地址" class="form-control" />
					</div>
				</div>
				<div class="form-group">					
					<div class="col-sm-8 col-sm-offset-2">
						<!--隐藏域存储部门的id-->
						<input value="${dept.deptId}" type="hidden" id="deptId" name="deptId" />
						<input id="updateBtn" type="submit" value="修改部门" class="btn btn-success"/>
						<input type="button" value="取消" class="btn btn-danger" onclick="history.back();" />
					</div>
				</div>
			</form>
			
		</div>		
		
		<script type="text/javascript" src="js/jquery.js" ></script>
		<script type="text/javascript" src="js/global.js" ></script>
		<script type="text/javascript" src="js/bootstrap/js/bootstrap.js" ></script>
		<script type="text/javascript" src="js/layer/layer.js" ></script>
		<script type="text/javascript" src="js/param.js" ></script>
		
		
	</body>
</html>