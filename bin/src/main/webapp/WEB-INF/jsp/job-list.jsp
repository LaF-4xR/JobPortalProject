<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Job Listings</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f7fb; margin: 0; }
        .topbar { background: #111827; color: #fff; padding: 15px 25px; display: flex; justify-content: space-between; align-items: center; }
        .topbar a { color: #fff; text-decoration: none; margin-left: 15px; }
        .container { padding: 25px; }
        .card { background: #fff; padding: 20px; border-radius: 12px; box-shadow: 0 8px 24px rgba(0,0,0,0.08); margin-bottom: 20px; }
        .job-card { background: #fff; padding: 18px; border-radius: 12px; box-shadow: 0 8px 24px rgba(0,0,0,0.08); margin-bottom: 15px; }
        .muted { color: #6b7280; }
        .search-box { margin-bottom: 20px; }
        input[type="text"] { padding: 10px; width: 300px; border: 1px solid #ccc; border-radius: 8px; }
        button { padding: 10px 14px; border: none; border-radius: 8px; background: #2563eb; color: white; cursor: pointer; }
        a.btn { display: inline-block; padding: 8px 12px; border-radius: 8px; text-decoration: none; background: #2563eb; color: white; }
    </style>
</head>
<body>

<div class="topbar">
    <div>
        <strong>Job Listings</strong>
    </div>
    <div>
        <a href="${pageContext.request.contextPath}/login">Login</a>
        <a href="${pageContext.request.contextPath}/signup">Signup</a>
    </div>
</div>

<div class="container">
    <div class="card">
        <form class="search-box" action="${pageContext.request.contextPath}/jobs" method="get">
            <input type="text" name="keyword" placeholder="Search jobs by title, location, description" value="${keyword}" />
            <button type="submit">Search</button>
        </form>

        <c:choose>
            <c:when test="${empty jobs}">
                <p>No jobs found.</p>
            </c:when>
            <c:otherwise>
                <c:forEach var="job" items="${jobs}">
                    <div class="job-card">
                        <h3>${job.title}</h3>
                        <p>${job.description}</p>
                        <p class="muted"><strong>Salary:</strong> ${job.salary}</p>
                        <p class="muted"><strong>Location:</strong> ${job.location}</p>
                        <p class="muted">
                            <strong>Employer:</strong>
                            <c:if test="${not empty job.employer}">
                                ${job.employer.companyName}
                            </c:if>
                        </p>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>