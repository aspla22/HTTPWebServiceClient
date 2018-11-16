package httpwebserviceclient;

import org.json.JSONObject;

public class HTTPWebServiceClient {
    public static void main(String[] args) {
        HTTPClient hTTPClient = new HTTPClient();
        System.out.println("Mengambil data pengguna");
        System.out.println(hTTPClient.doGET("http://localhost:8000/pengguna"));
        
        System.out.println("Mengambil data pengguna dengan id 78");
        System.out.println(hTTPClient.doGET("http://localhost:8000/pengguna/78"));
        
        System.out.println("Tambah data pengguna");
        JSONObject jsono = new JSONObject();
        jsono.append("NAMA", "Asthok");
        jsono.append("EMAIL", "arpasola@a.com");
        jsono.append("KATA_SANDI", "1233212");
        jsono.append("WARNA", "#3322");
        System.out.println(hTTPClient.doPOST("http://localhost:8000/pengguna", jsono.toString()));
        
        System.out.println("Update Data pengguna");
        JSONObject jsono2 = new JSONObject();
        jsono.append("NAMA", "Asthok update");
        jsono.append("EMAIL", "arpasola@a.com");
        jsono.append("KATA_SANDI", "1233212");
        jsono.append("WARNA", "#3322");
        System.out.println(hTTPClient.doPUT("http://localhost:8000/pengguna/78", jsono.toString()));
        
    }
}
