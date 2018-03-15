<html>
    <body>
        <div>
            <h2>Calendar list for user ${user}</h2>
        </div>
        <div>
            <#list model as calendar>
                <div>
                    <h3>Calendar ID:${calendar.id} </h3>
                    <p>${calendar.summary}</p>
                    <a href="${selectUrl + calendar.id}">select</a>
                </div>
            </#list>
        </div>
    </body>
</html>