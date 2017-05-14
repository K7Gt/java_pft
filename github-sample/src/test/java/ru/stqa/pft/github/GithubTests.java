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
        Github github = new RtGithub("7c8063bf36602330a0fa53ac3142e9c89b4f1fcf");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("K7Gt", "java_pft")).commits();
        for(RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
