package isel.mpd.jsonzai;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonParserTest {

    private String request1 = "{\n" +
            "  \"login\": \"achiu\",\n" +
            "  \"id\": 24772,\n" +
            "  \"avatar_url\": \"https://avatars.githubusercontent.com/u/24772?v=3\",\n" +
            "  \"gravatar_id\": \"\",\n" +
            "  \"url\": \"https://api.github.com/users/achiu\",\n" +
            "  \"html_url\": \"https://github.com/achiu\",\n" +
            "  \"followers_url\": \"https://api.github.com/users/achiu/followers\",\n" +
            "  \"following_url\": \"https://api.github.com/users/achiu/following{/other_user}\",\n" +
            "  \"gists_url\": \"https://api.github.com/users/achiu/gists{/gist_id}\",\n" +
            "  \"starred_url\": \"https://api.github.com/users/achiu/starred{/owner}{/repo}\",\n" +
            "  \"subscriptions_url\": \"https://api.github.com/users/achiu/subscriptions\",\n" +
            "  \"organizations_url\": \"https://api.github.com/users/achiu/orgs\",\n" +
            "  \"repos_url\": \"https://api.github.com/users/achiu/repos\",\n" +
            "  \"events_url\": \"https://api.github.com/users/achiu/events{/privacy}\",\n" +
            "  \"received_events_url\": \"https://api.github.com/users/achiu/received_events\",\n" +
            "  \"type\": \"User\",\n" +
            "  \"site_admin\": true,\n" +
            "  \"name\": \"Arthur Chiu\",\n" +
            "  \"company\": \"GitHub\",\n" +
            "  \"blog\": \"\",\n" +
            "  \"location\": \"San Francisco, CA\",\n" +
            "  \"email\": \"achiu@github.com\",\n" +
            "  \"hireable\": false,\n" +
            "  \"bio\": null,\n" +
            "  \"public_repos\": 51,\n" +
            "  \"public_gists\": 37,\n" +
            "  \"followers\": 200,\n" +
            "  \"following\": 38,\n" +
            "  \"created_at\": \"2008-09-16T03:24:44Z\",\n" +
            "  \"updated_at\": \"2015-04-09T13:59:15Z\"\n" +
            "}";

    @Test
    public void testToObject() throws Exception {
        JsonParser<GithubUser> parser = new JsonParser<>();

        GithubUser user = parser.toObject(request1, GithubUser.class);

        assertNotNull(user);
        assertEquals(user.login, "achiu");
        assertEquals(user.email, "achiu@github.com");
        assertEquals(user.location, "San Francisco, CA");
        assertEquals(user.id, 24772);
    }
}