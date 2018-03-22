<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<c:set var="pageNum" value="${pageList.pageNum}" />  <!--当前页码-->
<c:set var="totalPage" value="${pageList.pages}" /><!--总记录数 -->

<c:if test="${(totalPage > 0) && (pageNum <= totalPage)}">
  <c:set var="startPage" value="${pageNum - 2}" />
  <c:if test="${startPage < 1}" >
    <c:set var="startPage" value="1" />
  </c:if>
  <c:set var="endPage" value="${pageNum + 2}" />
  <c:if test="${endPage > totalPage}" >
    <c:set var="endPage" value="totalPage" />
  </c:if>
</c:if>

<div style="border-top: 1px dotted #e2e2e2; text-align: center;">
  <div class="laypage-main">
    <c:if test="${pageNum <= 4}">
      <c:set var="startPage" value="1" />
    </c:if>

    <c:if test="${(totalPage - pageNum) < 4}">
      <c:set var="endPage" value="${totalPage}" />
    </c:if>

      <c:choose>
        <c:when test="${pageNum == 1}">
          <span>上一页</span>
        </c:when>
        <c:otherwise>
          <a href="${ctx}/${navigation}/${type}/p${pageNum - 1}${urlSuffix}" class="laypage-prev">上一页</a>
        </c:otherwise>
      </c:choose>
      <c:if test="${pageNum > 4}">
        <a href="${ctx}/${navigation}/${type}/p${1}${urlSuffix}" class="laypage-prev">${1}</a>
        <span>...</span>
      </c:if>
      <c:forEach begin="${startPage}" end="${endPage}" var="i">
        <c:choose>
          <c:when test="${pageNum == i}">
            <span class="laypage-curr">${pageNum}</span>
          </c:when>
          <c:otherwise>
            <a href="${ctx}/${navigation}/${type}/p${i}${urlSuffix}">${i}</a>
          </c:otherwise>
        </c:choose>
      </c:forEach>
      <c:if test="${(totalPage - pageNum) >= 4}">
        <span>...</span>
        <a href="${ctx}/${navigation}/${type}/p${totalPage}${urlSuffix}">${totalPage}</a>
      </c:if>
      <c:choose>
        <c:when test="${pageNum == totalPage}">
          <span>下一页</span>
        </c:when>
        <c:otherwise>
            <a href="${ctx}/${navigation}/${type}/p${pageNum + 1}${urlSuffix}" class="laypage-next">下一页</a>
        </c:otherwise>
      </c:choose>
    </div>
</div>






