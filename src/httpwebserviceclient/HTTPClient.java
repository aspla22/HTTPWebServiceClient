package httpwebserviceclient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPClient {
    private String GET(String url, String method) {
        String result = "";
        try {
            URL urlTemp = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlTemp.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            int res = connection.getResponseCode();
            if (res != 200) {
                result = "{\"statusCode\":"+res+",\"error\":\"Not Found\"}";
            } else {
                InputStream is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = null;
                StringBuffer temp = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    temp.append(line);
                }
                result = temp.toString();
            }
            connection.disconnect();
            return result;
        } catch (Exception ex) {
            return "Error in method GET : " + ex.getMessage();
        }
    }

    private String POST(String url, String method, String msg) {
        String result = "";
        try {
            URL urlTemp = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlTemp.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(msg);
            out.flush();
            out.close();

            int res = connection.getResponseCode();

            if (res != 200) {
                result = "{\"statusCode\":"+res+",\"error\":\"Not Found\"}";
            } else {
                InputStream is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = null;
                StringBuffer temp = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    temp.append(line);
                }
                result = temp.toString();
            }

            connection.disconnect();
            return result;
        } catch (Exception ex) {
            return "Error in method POST : " + ex.getMessage();
        }
    }
    
    public String doGET(String url){
        return GET(url, "GET");
    }
    
    public String doDELETE(String url){
        return GET(url, "DELETE");
    }
    
    public String doDELETE(String url, String message){
        return POST(url, "DELETE", message);
    }
    
    public String doPOST(String url, String message){
        return POST(url, "POST", message);
    }
    
    public String doPUT(String url, String message){
        return POST(url, "PUT", message);
    }
}
