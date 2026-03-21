<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Job Seeker Dashboard</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f7fb;
            margin: 0;
        }

        .topbar {
            background: #111827;
            color: #fff;
            padding: 15px 25px;
            display: flex;
            justify-content: space-between;
        }

        .topbar a {
            color: white;
            margin-left: 15px;
            text-decoration: none;
        }

        .container {
            padding: 25px;
        }

        .card {
            background: white;
            padding: 20px;
            border-radius: 12px;
            margin-bottom: 20px;
            box-shadow: 0 8px 24px rgba(0,0,0,0.08);
        }

        .job-card {
            background: white;
            padding: 18px;
            border-radius: 12px;
            margin-bottom: 15px;
            box-shadow: 0 8px 24px rgba(0,0,0,0.08);
        }

        .btn {
            padding: 8px 12px;
            border-radius: 8px;
            border: none;
            cursor: pointer;
        }

        .btn-apply {
            background: #16a34a;
            color: white;
        }

        .btn-apply:hover {
            background: #15803d;
        }

        .muted {
            color: #6b7280;
        }

        .msg {
            padding: 10px;
            border-radius: 8px;
            margin-bottom: 15px;
        }

        .success {
            background: #dcfce7;
            color: #166534;
        }

        .error {
            background: #fee2e2;
            color: #991b1b;
        }
    </style>
</head>

<body>

<div class="topbar">
    <div><strong>Seeker Dashboard</strong></div>
    <div>
        <a href="${pageContext.request.contextPath}/jobs">Browse Jobs</a>
        <a href="${pageContext.request.contextPath}/seeker/profile">Profile</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</div>

<div class="container">

    <!-- SUCCESS MESSAGE -->
    <c:if test="${param.applied == 'true'}">
        <div class="msg success">Applied successfully!</div>
    </c:if>

    <!--  ERROR MESSAGE -->
    <c:if test="${not empty param.error}">
        <div class="msg error">${param.error}</div>
    </c:if>

    <!-- PROFILE CARD -->
    <div class="card">
        <h3>Welcome, ${seeker.name}</h3>
        <p><strong>Email:</strong> ${seeker.email}</p>
        <p><strong>Phone:</strong> ${seeker.phone}</p>
        <p><strong>Skills:</strong> ${seeker.skills}</p>
        <p><strong>Experience:</strong> ${seeker.experience} years</p>
    </div>

    <!-- JOB LIST -->
    <div class="card">
        <h3>Available Jobs</h3>

        <c:choose>
            <c:when test="${empty jobs}">
                <p>No jobs available right now.</p>
            </c:when>

            <c:otherwise>
                <c:forEach var="job" items="${jobs}">

                    <div class="job-card">
                        <h4>${job.title}</h4>

                        <p>${job.description}</p>

                        <p class="muted">
                            <strong>Salary:</strong> ${job.salary}
                        </p>

                        <p class="muted">
                            <strong>Location:</strong> ${job.location}
                        </p>

                        <p class="muted">
                            <strong>Employer:</strong>
                            <c:if test="${not empty job.employer}">
                                ${job.employer.companyName}
                            </c:if>
                        </p>

                        <!-- APPLY BUTTON -->
                        <form action="${pageContext.request.contextPath}/applications/apply/${job.jobId}" method="post">
                            <button class="btn btn-apply" type="submit">
                                Apply
                            </button>
                        </form>

                    </div>

                </c:forEach>
            </c:otherwise>
        </c:choose>

    </div>

</div>

</body>
</html>