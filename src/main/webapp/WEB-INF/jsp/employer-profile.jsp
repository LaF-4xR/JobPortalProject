<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employer Profile</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f7fb; margin: 0; }
        .container { width: 700px; margin: 40px auto; background: #fff; padding: 30px; border-radius: 12px; box-shadow: 0 8px 24px rgba(0,0,0,0.08); }
        .row { display: flex; gap: 15px; }
        .form-group { margin-bottom: 15px; flex: 1; }
        label { display: block; margin-bottom: 6px; font-weight: bold; }
        input { width: 100%; padding: 10px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 8px; }
        button { width: 100%; padding: 10px; border: none; border-radius: 8px; background: #2563eb; color: white; font-size: 16px; cursor: pointer; }
        a { color: #2563eb; text-decoration: none; }
        .msg { padding: 10px; border-radius: 8px; margin-bottom: 15px; background: #fee2e2; color: #991b1b; }
    </style>
</head>
<body>
<div class="container">
    <h2>Employer Profile</h2>

    <c:if test="${not empty error}">
        <div class="msg">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/employer/profile" method="post">
        <div class="row">
            <div class="form-group">
                <label>Name</label>
                <input type="text" name="name" value="${employer.name}" required />
            </div>
            <div class="form-group">
                <label>Company Name</label>
                <input type="text" name="companyName" value="${employer.companyName}" required />
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email" value="${employer.email}" required />
            </div>
            <div class="form-group">
                <label>Phone</label>
                <input type="text" name="phone" value="${employer.phone}" />
            </div>
        </div>

        <div class="form-group">
            <label>Location</label>
            <input type="text" name="location" value="${employer.location}" />
        </div>

        <button type="submit">Update Profile</button>
    </form>

    <p style="margin-top:15px;">
        <a href="${pageContext.request.contextPath}/employer/dashboard">Back to Dashboard</a>
    </p>
</div>
</body>
</html>