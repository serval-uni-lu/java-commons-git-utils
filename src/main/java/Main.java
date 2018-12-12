import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("");
        HttpResponse response;

        try{
            response = client.execute(request);
            String json = EntityUtils.toString(response.getEntity());
            System.out.println(json);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
