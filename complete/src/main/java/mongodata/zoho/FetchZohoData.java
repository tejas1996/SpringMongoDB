package zoho;

import mongodata.zoho.ZohoApiCalls;
import mongodata.zoho.ZohoConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Configuration
public class FetchZohoData {

    @Autowired
    private Environment env;

    @Autowired
    private ZohoApiCalls zohoApiCalls;

    public List<JSONObject> fetchZohoAccountsData() throws IOException, JSONException {
        String apiDomain=env.getProperty("zoho.api.domain");
        String refreshToken = env.getProperty("refresh_token");
        String clientId = env.getProperty("zoho.client.id");
        String clientSecret=env.getProperty("zoho.client.secret");
        JSONObject jsonObjectForAccessToken =  zohoApiCalls.postCallGenerateAccessToken(apiDomain,refreshToken,clientId,clientSecret);
        String newAccessTokenGenerated=(String)jsonObjectForAccessToken.get("access_token");
        String moduleName = ZohoConstants.ACCOUNTS;
        List<JSONObject> jsonObjectList=zohoApiCalls.getCallForAccounts(apiDomain,newAccessTokenGenerated,moduleName);
        List<JSONArray> jsonArrays = new ArrayList<>();
        List jsonObjects = new ArrayList<>();
        for(JSONObject jsonObject : jsonObjectList) {
            jsonArrays.add(jsonObject.getJSONArray("data"));
        }
        for(int i=0;i<jsonArrays.size();i++) {
            JSONArray jsonArray = jsonArrays.get(i);
            for(int j=0;j<jsonArray.length();j++) {
                jsonObjects.add(jsonArray.get(j));
            }
        }
        return jsonObjects;
    }
}
