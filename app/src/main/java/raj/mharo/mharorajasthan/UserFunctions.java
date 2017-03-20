package raj.mharo.mharorajasthan;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static raj.mharo.mharorajasthan.AppConfig.URL_CUSTOMER_VEHICLE;
import static raj.mharo.mharorajasthan.AppConfig.URL_MERCHANT;

/**
 * Created by prakash on 10-07-2016.
 */
public class UserFunctions {
    HttpURLConnection connection;
    BufferedReader reader=null;
    JSONObject jObj = null;
    String json = "";
    StringBuffer buffer=null;
    public JSONObject merchant(String vehicleNumber, String merchantId) throws JSONException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("mt_id",merchantId);
        jsonObject.put("plate_number",vehicleNumber);
      //  JSONObject json = getJSONFromUrl(,jsonObject);
        try {
            URL loginUrl=new URL("https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesDemoItem.txt");
            connection=(HttpURLConnection)loginUrl.openConnection();
        //    connection.setRequestMethod("POST");
            connection.connect();

          //  OutputStreamWriter details=new OutputStreamWriter(connection.getOutputStream());
            //details.write(jsonObject.toString());
            //details.flush();

            reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            buffer=new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            json=buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(connection!=null)
            {
                connection.disconnect();
            }
        }
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {

        }
         return jObj;
    }
 /*   public JSONObject userVehicleReg(String SSoid, String PlateNumber, String VehicleType) throws JSONException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("ssoid",SSoid);
        jsonObject.put("plate_number",PlateNumber);
        jsonObject.put("vehicle_type",VehicleType);
        JSONObject json = jsonParser.getJSONFromUrl(URL_CUSTOMER_VEHICLE,jsonObject);
        return json;
    }*/
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

