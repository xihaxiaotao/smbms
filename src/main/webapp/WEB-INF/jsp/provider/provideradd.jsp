<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>
<%-- <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面 >> 供应商添加页面</span>
        </div>
        <div class="providerAdd">
<!--            <form id="providerForm" name="providerForm" method="post" action="${pageContext.request.contextPath }/provider/addProvider"> -->
			<fm:form method="post" modelAttribute="provider" enctype="multipart/form-data">
			    <input type="hidden" name="method" value="add">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="proCode">供应商编码：</label>
<!--                     <input type="text" name="proCode" id="proCode" value="">  -->
                    <fm:input path="proCode" />
					<!-- 放置提示信息 -->
					<font color="red"><fm:errors path="proCode"/></font>
                </div>
                <div>
                    <label for="proName">供应商名称：</label>
<!--                     <input type="text" name="proName" id="proName" value="">  -->
                    <fm:input path="proName" />
					<font color="red"><fm:errors path="proName"/></font>
                </div>
                <div>
                    <label for="proContact">联系人：</label>
<!--                     <input type="text" name="proContact" id="proContact" value="">  -->
                    <fm:input path="proContact" />
					<font color="red"><fm:errors path="proContact"/></font>

                </div>
                <div>
                    <label for="proPhone">联系电话：</label>
<!--                     <input type="text" name="proPhone" id="proPhone" value="">  -->
                    <fm:input path="proPhone" />
					<font color="red"><fm:errors path="proPhone"/></font>
                </div>
                <div>
                    <label for="proAddress">联系地址：</label>
                    <input type="text" name="proAddress" id="proAddress" value=""> 

                </div>
                <div>
                    <label for="proFax">传真：</label>
                    <input type="text" name="proFax" id="proFax" value=""> 
                </div>
                <div>
                    <label for="proDesc">描述：</label>
                    <input type="text" name="proDesc" id="proDesc" value=""> 
                </div>
                <div>
                    <label for="companyLicPicPath">企业营业执照：</label>
                    <input type="file" name="a_file" id="companyLicPicPath" value="">
                </div>
                 <div>
                    <label for="orgCodePicPath">组织机构代码证：</label>
                    <input type="file" name="a_file" id="orgCodePicPath" value="">
                </div>
                <div class="providerAddBtn">
                    <input type="submit" name="add" id="add" value="保存">
					<input type="button" id="back" name="back" onclick="javascript:location.href='${pageContext.request.contextPath }/provider/getProviderList'" value="返回" >
                </div>
                </fm:form>
<!--             </form> -->
     </div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<!-- <script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/provideradd.js"></script> -->
