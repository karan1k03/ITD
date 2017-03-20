package raj.mharo.mharorajasthan;

import org.json.JSONException;
import org.json.JSONObject;

import static raj.mharo.mharorajasthan.AppConfig.URL_CUSTOMER_VEHICLE;

/**
 * Created by prakash on 10-07-2016.
 */
public class UserFunctions {
    JSONParser jsonParser= new JSONParser();

   /* public UserFunctions(){
        jsonParser = new JSONParser();
    }*/
    public JSONObject merchant(String vehicleNumber, String merchantId) throws JSONException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("mt_id",merchantId);
        jsonObject.put("plate_number",vehicleNumber);
        JSONObject json = jsonParser.getJSONFromUrl("http://www.itsohkay.in/mharo_rajasthan_api/merchant_toll.php",jsonObject);
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
   /* public JSONObject getTransactionByDate(String sessionid, String accountid, String accounttype,String extend, String sDate, String eDate) throws JSONException{
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("SessionId",sessionid);
        jsonObject.put("AccountID",accountid);
        jsonObject.put("AccountType",accounttype);
        jsonObject.put("ExtendedAccountType",extend);
        jsonObject.put("StartDate",sDate);
        jsonObject.put("EndDate",eDate);
        JSONObject json=jsonParser.getJSONFromUrl(URL_GET_TRANSACTION_BY_DATE,jsonObject);
        return json;*/

    }

