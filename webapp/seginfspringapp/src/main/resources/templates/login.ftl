<html>
    <body>
        <div>
            <span>Login status</span>
            <ul>
                <li>
                    <span>Google:</span>
                    <#if model.googleLoggedIn >
                        <span>Logged in as ${model.googleUser}</span>
                    <#else>
                        <a href=".${model.googleUrl}">Login</a>
                    </#if>
                </li>
                <#if model.googleLoggedIn >
                    <li>
                        <a href="${model.calendarSelectUrl}">Select google calendar</a>
                    </li>
                </#if>
                <li>
                    <span>Github:</span>
                    <#if model.githubLoggedIn >
                        <span>Logged in to github</span>
                    <#else>
                        <a href=".${model.githubUrl}">Login</a>
                    </#if>
                </li>
            </ul>
        </div>
        <div>
            <span>Milestones:</span>
            <#if model.googleLoggedIn && model.githubLoggedIn >
                <a href=".${model.milestonesUrl}">Github milestones</a>
            <#else>
                <span>must be logged in to both providers</span>
            </#if>
        </div>
    </body>
</html>