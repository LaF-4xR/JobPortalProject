<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Signup - Job Portal</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f7fb; margin: 0; }
        .container { width: 700px; margin: 40px auto; background: #fff; padding: 30px; border-radius: 12px; box-shadow: 0 8px 24px rgba(0,0,0,0.08); }
        h2 { margin-top: 0; text-align: center; }
        .row { display: flex; gap: 15px; }
        .form-group { margin-bottom: 15px; flex: 1; }
        label { display: block; margin-bottom: 6px; font-weight: bold; }
        input, select, textarea { width: 100%; padding: 10px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 8px; }
        textarea { min-height: 90px; resize: vertical; }
        button { width: 100%; padding: 10px; border: none; border-radius: 8px; background: #2563eb; color: white; font-size: 16px; cursor: pointer; }
        button:hover { background: #1d4ed8; }
        .msg { padding: 10px; border-radius: 8px; margin-bottom: 15px; }
        .error { background: #fee2e2; color: #991b1b; }
        .footer { text-align: center; margin-top: 15px; }
        .section-title { margin: 20px 0 10px; font-weight: bold; color: #374151; }
        .hidden { display: none; }
        a { color: #2563eb; text-decoration: none; }
    </style>
    <script>
        function toggleFields() {
            const role = document.getElementById("role").value;
            document.getElementById("employerFields").style.display = role === "EMPLOYER" ? "block" : "none";
            document.getElementById("seekerFields").style.display = role === "SEEKER" ? "block" : "none";
        }
        window.onload = toggleFields;
    </script>
</head>
<body>
<div class="container">
    <h2>Sign Up</h2>

    <c:if test="${not empty error}">
        <div class="msg error">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/signup" method="post">
        <div class="row">
            <div class="form-group">
                <label>Name</label>
                <input type="text" name="name" required />
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email" required />
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" required />
            </div>
            <div class="form-group">
                <label>Role</label>
                <select name="role" id="role" onchange="toggleFields()" required>
                    <option value="EMPLOYER">Employer</option>
                    <option value="SEEKER">Job Seeker</option>
                </select>
            </div>
        </div>

        <div id="employerFields">
            <div class="section-title">Employer Details</div>
            <div class="row">
                <div class="form-group">
                    <label>Company Name</label>
                    <input type="text" name="companyName" />
                </div>
                <div class="form-group">
                    <label>Phone</label>
                    <input type="text" name="phone" />
                </div>
            </div>
            <div class="form-group">
                <label>Location</label>
                <input type="text" name="location" />
            </div>
        </div>

        <div id="seekerFields">
            <div class="section-title">Job Seeker Details</div>
            <div class="row">
                <div class="form-group">
                    <label>Phone</label>
                    <input type="text" name="phone" />
                </div>
                <div class="form-group">
                    <label>Experience (Years)</label>
                    <input type="number" name="experience" min="0" />
                </div>
            </div>
            <div class="form-group">
                <label>Skills</label>
                <textarea name="skills"></textarea>
            </div>
        </div>

        <button type="submit">Create Account</button>
    </form>

    <div class="footer">
        <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a></p>
    </div>
</div>
</body>
</html>