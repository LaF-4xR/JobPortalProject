<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Post Job</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f7fb; margin: 0; }
        .container { width: 700px; margin: 40px auto; background: #fff; padding: 30px; border-radius: 12px; box-shadow: 0 8px 24px rgba(0,0,0,0.08); }
        .row { display: flex; gap: 15px; }
        .form-group { margin-bottom: 15px; flex: 1; }
        label { display: block; margin-bottom: 6px; font-weight: bold; }
        input, textarea { width: 100%; padding: 10px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 8px; }
        textarea { min-height: 140px; resize: vertical; }
        button { width: 100%; padding: 10px; border: none; border-radius: 8px; background: #2563eb; color: white; font-size: 16px; cursor: pointer; }
        a { color: #2563eb; text-decoration: none; }
        .msg { padding: 10px; border-radius: 8px; margin-bottom: 15px; background: #fee2e2; color: #991b1b; }
    </style>
</head>
<body>
<div class="container">
    <h2>Post a Job</h2>

    <c:if test="${not empty error}">
        <div class="msg">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/jobs/post" method="post">
        <div class="form-group">
            <label>Job Title</label>
            <input type="text" name="title" required />
        </div>

        <div class="form-group">
            <label>Description</label>
            <textarea name="description" required></textarea>
        </div>

        <div class="row">
            <div class="form-group">
                <label>Salary</label>
                <input type="number" step="0.01" name="salary" required />
            </div>
            <div class="form-group">
                <label>Location</label>
                <input type="text" name="location" required />
            </div>
        </div>

        <button type="submit">Post Job</button>
    </form>

    <p style="margin-top:15px;">
        <a href="${pageContext.request.contextPath}/employer/dashboard">Back to Dashboard</a>
    </p>
</div>
</body>
</html>