package raj.mharo.mharorajasthan;

import org.json.JSONException;
import org.json.JSONObject;

import static raj.mharo.mharorajasthan.AppConfig.URL_CUSTOMER_VEHICLE;
import static raj.mharo.mharorajasthan.AppConfig.URL_ACCOUNT_INFO;
import static raj.mharo.mharorajasthan.AppConfig.URL_ACCOUNT_INFO;

/**
 * Created by prakash on 10-07-2016.
 */
public class UserFunctions {
    private JSONParser jsonParser;

   /* public UserFunctions(){
        jsonParser = new JSONParser();
    }*/
    public JSONObject loginUser(String email, String password) throws JSONException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userid",email);
        jsonObject.put("password",password);
        JSONObject json = jsonParser.getJSONFromUrl(URL_LOGIN,jsonObject);
        return json;
    }
    public JSONObject userVehicleReg(String SSoid, String PlateNumber, String VehicleType) throws JSONException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("ssoid",SSoid);
        jsonObject.put("plate_number",PlateNumber);
        jsonObject.put("vehicle_type",VehicleType);
        JSONObject json = jsonParser.getJSONFromUrl(URL_CUSTOMER_VEHICLE,jsonObject);
        return json;
    }
    public JSONObject getTransactionByDate(String sessionid, String accountid, String accounttype,String extend, String sDate, String eDate) throws JSONException{
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("SessionId",sessionid);
        jsonObject.put("AccountID",accountid);
        jsonObject.put("AccountType",accounttype);
        jsonObject.put("ExtendedAccountType",extend);
        jsonObject.put("StartDate",sDate);
        jsonObject.put("EndDate",eDate);
        JSONObject json=jsonParser.getJSONFromUrl(URL_GET_TRANSACTION_BY_DATE,jsonObject);
        return json;

    }

}
