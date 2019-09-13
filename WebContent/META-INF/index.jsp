<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 
<html>
 <head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
       <title>Página de Login Customizado</title>
 </head>
  
 <body>
  
   <!-- HEADER -->
                  
   <div>
    <ul class="nav">
      
     <sec:authorize access="authenticated" var="authenticated"/>
     <c:choose>
        <c:when test="$">
         <li id="greeting">
            <div>
              Bem-vindo
              <sec:authentication property="name" />
            </div>
         </li>
 
         <li>
            <a href="/ExemploSpringSecurity/logout">Logout</a>
            
         </li>
        </c:when>
        <c:otherwise>
         <li>
         <a id="navLoginLink" href="/login.jsp">Login</a>
         </li>
        </c:otherwise>
     </c:choose>
             
    </ul>
   </div>
                  
    
    
   <!-- FORMULÁRIO -->
    
   <form name="f" action="<c:url value='/fazerLogin'/>" method="post">
    
      <c:if test="${param.error != null}">
       <div class="alert alert-error">
        Falha ao fazer Login.
        <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
              Motivo: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
        </c:if>
       </div>
      </c:if>
 
      <c:if test="${param.logout != null}">
       <div class="alert alert-success">
          Você efetuou log out.
       </div>
      </c:if>
 
      <label for="username">Username</label>
      <input type="text" id="j_username" name="j_username"/>
      <label for="password">Senha</label>
      <input type="password" id="j_password" name="j_password"/>
 
      <div class="form-actions">
       <input id="submit" class="btn" name="submit" type="submit" value="Login"/>
      </div>
           
   </form>
        
        
 </body>
</html>