<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Supervisor Login</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mt-5">
                <div class="card-header">
                    <h3 class="text-center">Supervisor Login</h3>
                </div>
                <div class="card-body">
                    <form action="/login/supervisor" method="post">
                        <div class="form-group">
                            <div>
                                <input type="radio" id="manager" name="dept" value="manager" required>
                                <label for="manager">Manager</label>
                                <input type="radio" id="hrd" name="dept" value="hrd" required>
                                <label for="hrd">HRD</label>
                                <input type="radio" id="admin" name="dept" value="admin" required>
                                <label for="admin">Admin</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sid">아이디</label>
                            <input type="text" class="form-control" id="sid" name="sid" required>
                        </div>
                        <div class="form-group">
                            <label for="spw">비밀번호</label>
                            <input type="password" class="form-control" id="spw" name="spw" required>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">로그인</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
