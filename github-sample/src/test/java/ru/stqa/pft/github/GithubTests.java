package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Golem on 13.05.2017.
 */
public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("a555fd7cfe429b55b79c874c486c6de699f5b898");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("K7Gt", "java_pft")).commits();
        for(RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
