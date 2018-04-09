<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>

<c:set var="sessionInfo" value="${sessionScope.sessionInfo}"/>

<!--七牛文章图片存储外链域名-->
<c:set var="qiNiuArticleUrl" value="http://otffjxbtr.bkt.clouddn.com"/>
<!--七牛相册图片存储外链域名-->
<c:set var="qiNiuPhotosUrl" value="http://p6wo7c9c2.bkt.clouddn.com"/>

<!--前端后缀.html 伪静态-->
<c:set var="urlSuffix" value=".html"/>
