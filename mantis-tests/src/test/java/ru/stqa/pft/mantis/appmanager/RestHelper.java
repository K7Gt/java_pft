package ru.stqa.pft.mantis.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import ru.stqa.pft.mantis.model.Issue;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Golem on 13.05.2017.
 */
public class RestHelper {

    private final ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth(app.getProperty("bugify.username"),app.getProperty("bugify.password"));
    }

    public int getStatusOfIssueById(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json",issueId)))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");

       Set<Issue> issue =  new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
       return issue.iterator().next().getState();
    }

    public String getStatusNameOfIssueById(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json",issueId)))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        Set<Issue> issue =  new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
        return issue.iterator().next().getState_name();
    }
}
