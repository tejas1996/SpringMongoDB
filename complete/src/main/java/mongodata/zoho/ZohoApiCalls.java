package mongodata.zoho;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZohoApiCalls {

    public JSONObject postCallGenerateAccessToken(String apiDomain, String refreshToken, String clientId, String clientSecret) throws IOException, JSONException {

        URL url = new URL(apiDomain+"oauth/v2/token");
        Map<String,String> params = new LinkedHashMap<>();
        params.put("refresh_token", refreshToken);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("grant_type", "refresh_token");
        StringBuilder postData = new StringBuilder();
        for (Map.Entry param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey().toString(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);
        int httpResponseCode = conn.getResponseCode();
        if (httpResponseCode == HttpURLConnection.HTTP_OK) {
           return convertResponseToString(conn);
        }
        return null;
    }


    public JSONObject convertResponseToString(HttpURLConnection conn) throws IOException, JSONException {
        String readLine = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        StringBuffer response = new StringBuffer();
        while ((readLine = in.readLine()) != null) {
            response.append(readLine);
        }
        in.close();
        if (response != null) {
            JSONObject jsonObject = new JSONObject(response.toString());
            return jsonObject;
        }

    return null;
    }

    public List<JSONObject> getCallForAccounts(String apiDomain,String accessToken,String moduleName) throws IOException, JSONException {
        List<JSONObject> jsonObjectList =new ArrayList<>();
        int page=1;
        int recordsPerPage=200;
        boolean moreRecords=true;
        while(moreRecords) {
            URL url = new URL("https://www.zohoapis.in/crm/v2/" + moduleName + "?page=" + page + "&per_page=" + recordsPerPage);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            int httpResponseCode = conn.getResponseCode();
            if (httpResponseCode == HttpURLConnection.HTTP_OK) {
                JSONObject jsonObject = convertResponseToString(conn);
                JSONObject jsonInfoObject =jsonObject.getJSONObject("info");
                if(checkMoreRecords(jsonInfoObject)){
                    moreRecords=true;
                }
                else moreRecords=false;
                jsonObjectList.add(jsonObject);
            }
            page++;
        }
        return jsonObjectList;
    }

    public boolean checkMoreRecords(JSONObject jsonInfoObject) throws JSONException {
        if(jsonInfoObject.get("more_records").equals(true)) {
            return true;
        }
        return false;
    }




}
