/**  
 * Copyright (C) 2015 The LianLianYT PAY SDK Source Project
 * All right reserved.
 * @Title:  CustomHttpURLConnection.java   
 * @Package com.yintong.secure.conn   
 * @author: Marco Jin     
 * @date:   2016年3月22日 下午3:14:26       
 */
package com.bb.taold.lianlian.utils;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;


public class CustomURLConnection {

	// private static CustomHttpURLConnection customHttpURLConnection;
	private static final int TIME_OUT = 1000 * 40;

	private CustomURLConnection() {

	}
	
	public static synchronized String post(String urlPath, JSONObject requestObj) {
		if(null != urlPath && urlPath.length() > 0) {
			if(urlPath.contains("https")) {
				return httpPost(urlPath, requestObj, true);
			}else {
				return httpPost(urlPath, requestObj, false);
			}
		}
		
		return null;
	}
	
//	public static synchronized String post(String urlPath, Map<String, String> params) {
//		if(null != urlPath && urlPath.length() > 0) {
//			if(urlPath.contains("https")) {
//				return httpPost(urlPath, params, true);
//			}else {
//				return httpPost(urlPath, params, false);
//			}
//		}
//
//		return null;
//	}
	
//	private static String httpspost(String urlPath, JSONObject requestObj) {
//		if (null == requestObj || requestObj.length() <= 0) {
//			return null;
//		}
//
//		OutputStream os = null;
//		InputStream is = null;
//		HttpsURLConnection connection = null;
//		String response = null;
//		
//		byte[] data = requestObj.toString().getBytes();
//		
//		try {
//			// 获得URL对象
//			URL url = new URL(urlPath);
//			// 获得HttpURLConnection对象
//			connection = (HttpsURLConnection) url.openConnection();
//			
//			//设置HttpURLConnection
//			setHttp(connection, String.valueOf(data.length), true);
//			
//			os = connection.getOutputStream();
//			// 写入参数
//			os.write(data);
//			
//			// 相应码是否为200
//			int code =  connection.getResponseCode();
//			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//				// 获得输入流
//				is = connection.getInputStream();
//				
//				// 包装字节流为字符流
//				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//				
//				StringBuilder responseSB = new StringBuilder();
//				String line;
//				while ((line = reader.readLine()) != null) {
//					responseSB.append(line);
//				}
//				return responseSB.toString();
//			}
//		} catch (SocketTimeoutException cte) {
//			response = "{'ret_code':'" + HttpTransRetCode.NETWORK_CONN_TIMEOUT.code + "','ret_msg':'"
//					+ HttpTransRetCode.NETWORK_CONN_TIMEOUT.api_msg + "'}";
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			response = "{'ret_code':'" + HttpTransRetCode.NETWORK_ERROR.code + "','ret_msg':'"
//					+ HttpTransRetCode.NETWORK_ERROR.api_msg + "'}";
//		} finally {
//			// 关闭
//			if ( null!= os) {
//				try {
//					os.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (null != is) {
//				try {
//					is.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (null != connection) {
//				connection.disconnect();
//				connection = null;
//			}
//		}
//
//		return response;
//	}
//
//	private static String httpspost(String urlPath, Map<String, String> params) {
//		if (null == params || params.size() <= 0) {
//			return null;
//		}
//
//		String response = null;
//		
//		JSONObject json = new JSONObject(params);
//		if(null != json) {
//			response = post(urlPath, json);
//		}
//	
//		return response;
//	}
	
	private static String httpPost(String urlPath, JSONObject requestObj, boolean isHttps) {
		if (null == requestObj || requestObj.length() <= 0) {
			return null;
		}

		OutputStream os = null;
		InputStream is = null;
		HttpURLConnection connection = null;
		String response = null;
		
		byte[] data = requestObj.toString().getBytes();
		
		try {
			// 获得URL对象
			URL url = new URL(urlPath);
			// 获得HttpURLConnection对象
			connection = (HttpURLConnection) url.openConnection();
			
			//设置HttpURLConnection
			setHttp(connection, String.valueOf(data.length), isHttps);
			
			os = connection.getOutputStream();
			// 写入参数
			os.write(data);
			
			// 相应码是否为200
			int code =  connection.getResponseCode();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// 获得输入流
				is = connection.getInputStream();
				
				// 包装字节流为字符流
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				
				StringBuilder responseSB = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					responseSB.append(line);
				}
				return responseSB.toString();
			}
		} catch (SocketTimeoutException cte) {
			response = "{'ret_code':'" + "9998" + "','ret_msg':'"
					+ "网络异常" + "'}";
		} catch (MalformedURLException e) {
			response = "{'ret_code':'" + "9998" + "','ret_msg':'"
					+ "网络异常" + "'}";
		} catch (IOException e) {
			response = "{'ret_code':'" + "9998" + "','ret_msg':'"
					+ "网络异常" + "'}";
		} catch (Exception e) {
			response = "{'ret_code':'" + "9998" + "','ret_msg':'"
					+ "网络异常" + "'}";
		} finally {
			// 关闭
			if ( null!= os) {
				try {
					os.close();
				} catch (IOException e) {
					response = "{'ret_code':'" + "9998" + "','ret_msg':'"
							+ "网络异常" + "'}";
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					response = "{'ret_code':'" + "9998" + "','ret_msg':'"
							+ "网络异常" + "'}";
				}
			}
			if (null != connection) {
				connection.disconnect();
				connection = null;
			}
		}

		return response;
	}

	private static String httpPost(String urlPath, Map<String, String> params, boolean isHttps) {
		if (null == params || params.size() <= 0) {
			return null;
		}

		String response = null;
		
		JSONObject json = new JSONObject(params);
		if(null != json) {
			response = httpPost(urlPath, json, isHttps);
		}
	
		return response;
	}
	
	private static void setHttp(HttpURLConnection connection, String dataLength, boolean isHttps) {
		if(null != connection) {
			// 设置请求方法为post
			try {
				connection.setRequestMethod("POST");
				
				if(isHttps) {
					//设置套接工厂 
					KeyStore trustStore;
					SSLSocketFactory sf = null;

					try {
						trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
						trustStore.load(null, null);
						sf = new LLSSLSocketFactory();
						((HttpsURLConnection)connection).setSSLSocketFactory(sf);
					} catch (NoSuchAlgorithmException e) {
						Log.e("CONN", e.getMessage(), e);
					} catch (IOException e) {
						Log.e("CONN", e.getMessage(), e);
					} catch (CertificateException e) {
						Log.e("CONN", e.getMessage(), e);
					} catch (KeyStoreException e) {
						Log.e("CONN", e.getMessage(), e);
					} catch (UnrecoverableKeyException e) {
						e.printStackTrace();
					} catch (KeyManagementException e) {
						e.printStackTrace();
					}
				}
				
				// 不使用缓存
				connection.setUseCaches(false);
				// 设置超时时间
				connection.setConnectTimeout(TIME_OUT);
				// 设置读取超时时间
				connection.setReadTimeout(TIME_OUT);
				// 设置是否从httpUrlConnection读入，默认情况下是true;
				connection.setDoInput(true);
				// 设置为true后才能写入参数
				connection.setDoOutput(true);

				connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
				connection.setRequestProperty("Content-Length", dataLength);
			} catch (ProtocolException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}

class LLSSLSocketFactory extends SSLSocketFactory {
	SSLContext sslContext = SSLContext.getInstance("TLS");

	public LLSSLSocketFactory()
			throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, CertificateException, IOException {

		super();

		sslContext.init(null, new TrustManager[] { new LLX509TrustManager() }, null);
	}

	@Override
	public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
			throws IOException, UnknownHostException {
		return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
	}

	@Override
	public Socket createSocket() throws IOException {
		SSLSocket socket = (SSLSocket) sslContext.getSocketFactory().createSocket();
		// socket.setEnabledCipherSuites(new String[] {
		// "SSL_RSA_WITH_RC4_128_SHA" });
		return socket;
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException,
            UnknownHostException {
		return sslContext.getSocketFactory().createSocket(host, port);
	}

	@Override
	public Socket createSocket(InetAddress host, int port) throws IOException {
		return sslContext.getSocketFactory().createSocket(host, port);
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localHost,
                               int localPort) throws IOException, UnknownHostException {
		return sslContext.getSocketFactory().createSocket(host, port, localHost, localPort);
	}

	@Override
	public Socket createSocket(InetAddress address, int port,
                               InetAddress localAddress, int localPort) throws IOException {
		return sslContext.getSocketFactory().createSocket(address, port, localAddress, localPort);
	}

	static class LLX509TrustManager implements X509TrustManager {
		// lianlianpay域名hashcode为242518566 有效期为2018年4月10日
		// yintong.com.cn域名hashcode为1373588620 有效期为2017年5月11日
		private static final int[] hashCodes = new int[] { 1373588620, 242518566 };

		private X509TrustManager standardTrustManager = null;

		public LLX509TrustManager() throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null, null);

			TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			factory.init(trustStore);
			TrustManager[] trustmanagers = factory.getTrustManagers();
			if (trustmanagers.length == 0) {
				throw new NoSuchAlgorithmException("no trust manager found");
			}
			standardTrustManager = (X509TrustManager) trustmanagers[0];
		}

		public void checkClientTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
			standardTrustManager.checkClientTrusted(certificates, authType);
		}

		public void checkServerTrusted(X509Certificate[] certificates, String authType) throws CertificateException {

			if ((certificates != null) && (certificates.length > 0)) {
				boolean checkComplete = false;
				X509Certificate certificate = certificates[0];
				X500Principal issuerPrincipal = certificate.getIssuerX500Principal();
				X500Principal subjectPrincipal = certificate.getSubjectX500Principal();
				int name = (issuerPrincipal.getName() + subjectPrincipal.getName()).hashCode();
				for (int hashcode : hashCodes) {
					if (hashcode == name) {
						checkComplete = true;
						break;
					}
				}
				if (!checkComplete) {
					throw new CertificateException();
				}
			} else {
				throw new CertificateException();
			}
		}

		public X509Certificate[] getAcceptedIssuers() {
			return standardTrustManager.getAcceptedIssuers();
		}
	}

	@Override
	public String[] getDefaultCipherSuites() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getSupportedCipherSuites() {
		// TODO Auto-generated method stub
		return null;
	}
}
