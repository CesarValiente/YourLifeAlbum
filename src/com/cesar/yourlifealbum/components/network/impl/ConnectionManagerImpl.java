package com.cesar.yourlifealbum.components.network.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cesar.yourlifealbum.application.AppConstants;
import com.cesar.yourlifealbum.components.network.ConnectionManager;
import com.cesar.yourlifealbum.utils.Log;

public class ConnectionManagerImpl implements ConnectionManager {

    private final String CLASS_NAME = getClass().getName();

    private DefaultHttpClient httpClient;

    /**
     * Constructor
     */
    public ConnectionManagerImpl() {

        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 10000);
        HttpConnectionParams.setSoTimeout(params, 15000);
        httpClient = new DefaultHttpClient(params);
    }

    @Override
    public synchronized HttpResponse doRequest(final HttpUriRequest request) {

        // Log the outgoing request
        Log.d(CLASS_NAME, "--> RequestUrl: " + request.getURI());

        try {
            return httpClient.execute(request);

        } catch (SocketTimeoutException e) {
            Log.e(CLASS_NAME, "Timeout!");
        } catch (IOException se) {
            Log.e(CLASS_NAME, "Could not connect to server!");
        } catch (Exception e) {
            Log.e(CLASS_NAME, "Exception: " + e.getMessage());
        }
        return null;
    }

    @Override
    public synchronized String doRequestGet(final String url,
            final HashMap<String, String> params) {
        if (url == null) {
            return null;
        }

        HttpGet get = new HttpGet(url);
        for (String paramName : params.keySet()) {
            get.setHeader(paramName, params.get(paramName));
        }

        HttpResponse response = doRequest(get);
        return getResponseBody(response);
    }

    @Override
    public synchronized String doRequestGet(final URI uri) {

        HttpGet get = new HttpGet(uri);

        HttpResponse response = doRequest(get);
        return getResponseBody(response);
    }

    @Override
    public synchronized String doRequestGet(final String url) {
        if (url == null) {
            return null;
        }
        return doRequestGet(url, new HashMap<String, String>());
    }

    @Override
    public Bitmap getImage(final String url) {

        Log.d(CLASS_NAME, "Downloading image: " + url);

        InputStream stream = getFileStream(url);
        Bitmap result = BitmapFactory.decodeStream(stream);

        return result;
    }

    @Override
    public InputStream getFileStream(final String url) {
        try {
            URL fileUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();

            return conn.getInputStream();

        } catch (MalformedURLException e) {
            Log.e(CLASS_NAME,
                    "No correct URL has been supplied: " + url + e.toString());
        } catch (IOException e) {
            Log.e(CLASS_NAME,
                    "Something wen wrong trying to connect: " + e.toString());
        }
        return null;
    }

    /**
     * I've changed the process of parse the body of the response because,
     * before we get and use the inputstream if the response was ok (status code
     * 200), and not if we got any other response, as we can see in:
     * 
     * @link 
     *       http://stackoverflow.com/questions/5959751/android-get-image-from-url
     *       -singleclientconnmanager-problem
     * @link 
     *       http://stackoverflow.com/questions/4612573/exception-using-httprequest
     *       -execute-invalid-use-of-singleclientconnmanager-co
     * 
     *       We need to get the content after any request, nevermind if the
     *       response is an ok or not. If we don't do this, then soon or later
     *       we'll get the error regarding
     *       "Invalid use of SingleClientConnManager: connection still allocated"
     *       , and after the "famous" 401 error.
     * 
     */
    @Override
    public synchronized String getResponseBody(final HttpResponse response) {
        if (response == null) {
            Log.e(CLASS_NAME, "Response is null");
            return null;
        }

        HttpEntity httpResponseEntity = response.getEntity();
        if (httpResponseEntity != null) {
            int statusCode = response.getStatusLine().getStatusCode();
            Log.d(CLASS_NAME, "StatusCode: " + statusCode);
            try {
                InputStream contentInputStream = httpResponseEntity
                        .getContent();
                // Read the response content, if we have any
                if (contentInputStream != null) {

                    StringBuilder content = new StringBuilder();
                    // Get the responseBody
                    byte[] b = new byte[256];

                    for (int n; (n = contentInputStream.read(b)) != -1;) {
                        content.append(new String(b, 0, n,
                                AppConstants.Network.TEXT_ENCODING));
                    }
                    String body = content.toString();

                    /*
                     * Only sends the body if we have the good response, we
                     * don't need to send always the body because in several
                     * functions we check only if the response is null or has
                     * content.
                     */
                    if (statusCode == HttpStatus.SC_OK) {
                        return body;
                    } else {
                        return null;
                    }
                }
                return null;

            } catch (IOException ioe) {
                ioe.printStackTrace();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public synchronized String doRequestPost(final String url) {
        if (url == null) {
            return null;
        }

        // Do POST request
        HttpPost post = new HttpPost(url);

        return getResponseBody(doRequest(post));
    }

}
