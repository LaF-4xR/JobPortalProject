<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employer Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f5f7fb; margin: 0; }
        .topbar { background: #111827; color: #fff; padding: 15px 25px; display: flex; justify-content: space-between; align-items: center; }
        .topbar a { color: #fff; text-decoration: none; margin-left: 15px; }
        .container { padding: 25px; }
        .card { background: #fff; padding: 20px; border-radius: 12px; box-shadow: 0 8px 24px rgba(0,0,0,0.08); margin-bottom: 20px; }
        table { width: 100%; border-collapse: collapse; background: #fff; border-radius: 12px; overflow: hidden; }
        th, td { padding: 12px; border-bottom: 1px solid #e5e7eb; text-align: left; vertical-align: top; }
        th { background: #eff6ff; }
        .btn { display: inline-block; padding: 8px 12px; border-radius: 8px; text-decoration: none; border: none; cursor: pointer; }
        .btn-primary { background: #2563eb; color: white; }
        .btn-warning { background: #f59e0b; color: white; }
        .btn-danger { background: #dc2626; color: white; }
        .btn-secondary { background: #6b7280; color: white; }
        form.inline { display: inline; }
        .msg { padding: 10px; border-radius: 8px; margin-bottom: 15px; background: #dcfce7; color: #166534; }
    </style>
</head>
<body>

<div class="topbar">
    <div>
        <strong>Employer Dashboard</strong>
    </div>
    <div>
        <a href="${pageContext.request.contextPath}/jobs">View All Jobs</a>
        <a href="${pageContext.request.contextPath}/jobs/post">Post Job</a>
        <a href="${pageContext.request.contextPath}/employer/profile">Profile</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</div>

<div class="container">

    <c:if test="${param.jobAdded == 'true'}">
        <div class="msg">Job posted successfully.</div>
    </c:if>
    <c:if test="${param.jobUpdated == 'true'}">
        <div class="msg">Job updated successfully.</div>
    </c:if>
    <c:if test="${param.jobDeleted == 'true'}">
        <div class="msg">Job deleted successfully.</div>
    </c:if>
    <c:if test="${param.updated == 'true'}">
        <div class="msg">Profile updated successfully.</div>
    </c:if>

    <div class="card">
        <h3>Welcome, ${employer.name}</h3>
        <p><strong>Company:</strong> ${employer.companyName}</p>
        <p><strong>Email:</strong> ${employer.email}</p>
        <p><strong>Phone:</strong> ${employer.phone}</p>
        <p><strong>Location:</strong> ${employer.location}</p>
    </div>

    <div class="card">
        <h3>Your Posted Jobs</h3>

        <p>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/jobs/post">+ Post New Job</a>
        </p>

        <c:choose>
            <c:when test="${empty jobs}">
                <p>No jobs posted yet.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Salary</th>
                        <th>Location</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="job" items="${jobs}">
                        <tr>
                            <td>${job.title}</td>
                            <td>${job.description}</td>
                            <td>${job.salary}</td>
                            <td>${job.location}</td>
                            <td>
                                <a class="btn btn-warning" href="${pageContext.request.contextPath}/jobs/edit/${job.jobId}">Edit</a>
                                <form class="inline" action="${pageContext.request.contextPath}/jobs/delete/${job.jobId}" method="post">
                                    <button class="btn btn-danger" type="submit">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>