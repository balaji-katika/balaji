private void checkSSLConnection(HttpURLConnection connection) {
		// SSL settings
		try {
			if (connection instanceof HttpsURLConnection) {
				HttpsURLConnection secureConnection = (HttpsURLConnection) connection;
				SSLContext sslContext = SSLContext.getInstance("SSL");
				final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
					
					@Override
					public void checkClientTrusted(final X509Certificate[] chain, final String authType) {
					}
					
					@Override
					public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
					}
					
					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}
				} };
				sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
				// Create an ssl socket factory with the above manager
				final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
				// Create all-trusting host name verifier
				HostnameVerifier hostnameVerifier = new HostnameVerifier() {
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				};
				if (hostnameVerifier != null) {
					secureConnection.setHostnameVerifier(hostnameVerifier);
				}
				if (sslSocketFactory != null) {
					secureConnection.setSSLSocketFactory(sslSocketFactory);
				}
			}
		} catch (NoSuchAlgorithmException e) {
			throw new CustomException(e);
		} catch (KeyManagementException e) {
			throw new CustomException(e);
		}
		
	}
