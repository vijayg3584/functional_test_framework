/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.functestfrmwrk.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author vijay
 */
public class SimpleWebServiceCaller {

	public String callWebService(String xml) throws Exception {
		return StringUtils.trim(callWebservice(xml, StringUtils.EMPTY));
	}

	public static String callWebservice(String xml, String application) throws Exception {
		String webserviceaddress = "http://192.168.72.53:8080/Service/receiver";
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, new X509TrustManager[] { new X509TrustManager() {

			public void checkClientTrusted(X509Certificate[] chain, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		} }, null);
		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

			public boolean verify(String hostname, SSLSession session) {
				// ignore FQDN not matched with certificate common name
				return true;
			}
		});

		URL url = new URL(webserviceaddress);
		URLConnection conn = null;
		if (url.getProtocol().equalsIgnoreCase("https")) {
			conn = (HttpsURLConnection) url.openConnection();
		} else {
			conn = (HttpURLConnection) url.openConnection();
		}

		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("Content-Type", "xml/text");
		conn.connect();
		OutputStreamWriter wr = new java.io.OutputStreamWriter(conn.getOutputStream(), java.nio.charset.Charset.defaultCharset());
		wr.write(xml);
		wr.flush();
		wr.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		String result = "";

		while ((inputLine = in.readLine()) != null) {
			result += inputLine;
		}
		in.close();
		return result;
	}
}
