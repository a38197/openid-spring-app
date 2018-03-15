<html>
    <body>
        <div>
            <span>Logged in with account ${account}</span>
        </div>
        <div>
            <form method="post" action="${milestoneAction}">
                <div>
                    <label>
                        User:
                        <input name="user" type="text">
                    </label>
                </div>
                <div>
                    <label>
                        Repository:
                        <input name="repository" type="text">
                    </label>
                </div>
                <div>
                    <input type="submit" value="Milestones">
                </div>
            </form>
        </div>
    </body>
</html>