<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

        <t:template>
            <jsp:attribute name="title">
                Pagina principal
            </jsp:attribute>


            <jsp:body>
                <p>Hi I'm the heart of the message</p>
                <div class="btn btn-info"><a href="./global/login.jsp">Esto es un boton</a></div>
            </jsp:body>
        </t:template>