<html>
    <body>
        <div>
            <h2>Milestone list for user ${model.owner} and repository ${model.repository}</h2>
        </div>
        <div>
            <#list model.orderedMilestones as m>
                <form method="post" action="${actionUrl}" target="_blank">
                    <div>
                        <h3>${m.title} (${m.html_url}) on ${m.created_at}</h3>
                        <p>${m.description}</p>
                        <input type="submit" value="Insert all day Event">
                        <input type="hidden" name="title" value="${m.title}">
                        <input type="hidden" name="date" value="${m.created_at}">
                        <input type="hidden" name="description" value="${m.description}">
                        <input type="hidden" name="url" value="${m.html_url}">
                    </div>
                </form>
            </#list>
        </div>
    </body>
</html>