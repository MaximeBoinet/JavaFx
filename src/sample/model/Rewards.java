package sample.model;

import com.google.gson.Gson;
import javafx.scene.control.Button;
import org.apache.http.Header;
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
 * Created by MADMAX on 21/06/2017.
 */
public class Rewards {
    private static final String BASEURL = "http://mocnodeserv.hopto.org:3000/";
    private String _id;
    private String created_at;
    private String updated_at;
    private LocalDate builded_created_at;
    private LocalDate builded_updated_at;
    private String title;
    private String description;
    private String type;
    private int goldToAccess;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public LocalDate getCreated_at() {
        return builded_created_at;
    }

    public LocalDate getUpdated_at() {
        return builded_updated_at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGoldToAcces() {
        return goldToAccess;
    }

    public void setGoldToAcces(int goldToAcces) {
        this.goldToAccess = goldToAcces;
    }

    public void setCreated_at() {
        this.builded_created_at = Main.mongoDateToLocalDate(created_at);
    }

    public void setUpdated_at() {
        this.builded_updated_at = Main.mongoDateToLocalDate(updated_at);
    }

    public static Rewards postNewReward(String ptitle, String ptype, String pgold, String pdesc) {
        Rewards re = new Rewards();
        re.setTitle(ptitle);
        re.setType(ptype);
        re.setGoldToAcces(Integer.parseInt(pgold));
        re.setDescription(pdesc);
        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(BASEURL+"/reward/");
        StringEntity postingString = null;
        try {
            postingString = new StringEntity(gson.toJson(re));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        post.setHeader("authorization", Main.token);
        HttpResponse response;
        try {
            response = httpClient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            JSONObject js = new JSONObject(res);
            re = gson.fromJson(js.toString(), Rewards.class);
            return re;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateReward(Rewards re, String ptitle, String ptype, String pgold, String pdesc) {
        re.setTitle(ptitle);
        re.setType(ptype);
        re.setGoldToAcces(Integer.parseInt(pgold));
        re.setDescription(pdesc);
        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPut put = new HttpPut(BASEURL+"/reward/"+re.get_id());
        StringEntity puttingString = null;
        try {
            puttingString = new StringEntity(gson.toJson(re));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        put.setEntity(puttingString);
        put.setHeader("Content-type", "application/json");
        put.setHeader("authorization", Main.token);
        HttpResponse response;
        try {
            response = httpClient.execute(put);
            return response.getStatusLine().getStatusCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteReward(String id) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpDelete del = new HttpDelete(BASEURL+"/reward/"+id);
        del.setHeader("Content-type", "application/json");
        del.setHeader("authorization", Main.token);
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
        return "Rewards{" +
                "_id='" + _id + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", goldToAcces=" + goldToAccess +
                '}';
    }
}
