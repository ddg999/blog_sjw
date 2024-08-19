<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${statusCode}Error-PageNotFound</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<style type="text/css">
body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.error--message {
	text-align: center;
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="text-center">
			<p class="error--message">제목 혹은 내용을 20자 이내로 작성해주세요</p>
			<a href="javascript:history.back()" class="btn btn-primary">돌아가기</a>
		</div>
	</div>
</body>
</html>