<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login - Job Portal</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f7fb;
            margin: 0;
        }

        .container {
            width: 420px;
            margin: 80px auto;
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 8px 24px rgba(0,0,0,0.08);
        }

        h2 {
            margin-top: 0;
            text-align: center;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 8px;
        }

        button {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 8px;
            background: #2563eb;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background: #1d4ed8;
        }

        .msg {
            padding: 10px;
            border-radius: 8px;
            margin-bottom: 15px;
        }

        .error {
            background: #fee2e2;
            color: #991b1b;
        }

        .success {
            background: #dcfce7;
            color: #166534;
        }

        a {
            color: #2563eb;
            text-decoration: none;
        }

        .footer {
            text-align: center;
            margin-top: 15px;
        }
    </style>
</head>

<body>

<div class="container">
    <h2>Login</h2>

    <!-- ✅ SUCCESS MESSAGE (from session) -->
    <c:if test="${not empty sessionScope.successMessage}">
        <div class="msg success">
            ${sessionScope.successMessage}
        </div>
        <c:remove var="successMessage" scope="session"/>
    </c:if>

    <!-- ERROR MESSAGE -->
    <c:if test="${not empty error}">
        <div class="msg error">
            ${error}
        </div>
    </c:if>

    <!-- LOGIN FORM -->
    <form action="${pageContext.request.contextPath}/login" method="post">

        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" required />
        </div>

        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" required />
        </div>

        <button type="submit">Login</button>
    </form>

    <div class="footer">
        <p>
            New user?
            <a href="${pageContext.request.contextPath}/signup">Sign up here</a>
        </p>
    </div>
</div>

</body>
</html>