package sample.model;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import sample.Main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

/**
 * Created by MADMAX on 20/06/2017.
 */
public class Rank {
    private static final String BASEURL = "http://mocnodeserv.hopto.org:3000/";
    private String _id;
    private String created_at;
    private String updated_at;
    private LocalDate builded_created_at;
    private LocalDate builded_updated_at;
    private int nb;
    private String title;
    private int scoreToAccess;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setScoreToAcces(int scoreToAccess) {
        this.scoreToAccess = scoreToAccess;
    }

    public String get_id() {
        return _id;
    }

    public LocalDate getCreated_at() {
        return builded_created_at;
    }

    public LocalDate getUpdated_at() {
        return builded_updated_at;
    }

    public void setCreated_at() {
        this.builded_created_at = Main.mongoDateToLocalDate(this.created_at);
    }

    public void setUpdated_at() {
        this.builded_updated_at = Main.mongoDateToLocalDate(this.updated_at);
    }

    public int getNb() {
        return nb;
    }

    public String getTitle() {
        return title;
    }

    public int getScoreToAccess() {
        return scoreToAccess;
    }

    public static Rank postNewRank(String ptitle, int pscoreToAcces) {
        Rank ra = new Rank();
        ra.setTitle(ptitle);
        ra.setScoreToAcces(pscoreToAcces);
        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(BASEURL+"/rank/");
        StringEntity postingString = null;
        try {
            postingString = new StringEntity(gson.toJson(ra));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response;
        try {
            response = httpClient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            JSONObject js = new JSONObject(res);
            ra = gson.fromJson(js.toString(), Rank.class);
            return ra;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateRank(Rank ra, String ptitle, int pscoreToAcces) {
        ra.setScoreToAcces(pscoreToAcces);
        ra.setTitle(ptitle);
        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut put = new HttpPut(BASEURL+"/rank/"+ra.get_id());
        StringEntity puttingString = null;
        try {
            puttingString = new StringEntity(gson.toJson(ra));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        put.setEntity(puttingString);
        put.setHeader("Content-type", "application/json");
        HttpResponse response;
        try {
            response = httpClient.execute(put);
            return response.getStatusLine().getStatusCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteRank(String id) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpDelete del = new HttpDelete(BASEURL+"/rank/"+id);
        del.setHeader("Content-type", "application/json");
        HttpResponse response;
        try {
            response = httpClient.execute(del);
            return response.getStatusLine().getStatusCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "_id='" + _id + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", nb=" + nb +
                ", title='" + title + '\'' +
                ", scoreToAccess=" + scoreToAccess +
                '}';
    }
}
