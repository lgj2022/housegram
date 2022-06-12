<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/head.jsp" %>

  <%--   <script type="text/javascript">
        $(document).ready(function () {
            $("#mvIndex").click(function () {
                location.href = "<%= root %>";
            });
        });
    </script> --%>

    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
            <div class="jumbotron">
                <h2 class="text-danger"><%= request.getAttribute("msg") %></h2>
                <p class="mt-4"><a type="button" href="../initPage.do" id="mvIndex" class="btn btn-outline-dark">메인으로 이동</a></p>
            </div>
        </div>
    </div>

<%@ include file="/templates/footer.jsp" %>