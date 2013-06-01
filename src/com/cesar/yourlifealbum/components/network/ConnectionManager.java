/*
      	Copyright 2012 Cesar Valiente Gordo
 
        This file is part of SoundCloud LiveWallpaper.

    SoundCloud LiveWallpaper is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    SoundCloud LiveWallpaper is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with SoundCloud LiveWallpaper.  If not, see <http://www.gnu.org/licenses/>.
    
    PS: SoundCloud and all trademarks and code of SoundCloud are of its property
*/

package com.cesar.yourlifealbum.components.network;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.util.Pair;

/**
 * Class to handle connections to the server
 * @author wang
 *
 */
public interface ConnectionManager {		

	/**
	 * Do HTTP request
	 * @param request
	 * @return
	 */
	public abstract HttpResponse doRequest(HttpUriRequest request);
	
	/**
	 * Do GET request and return response body as string
	 * @param url
	 * @return <code>null</code> if the server gives an invalid response, else the response body
	 */
	public abstract String doRequestGet(String url);
	
	public abstract String doRequestGet (URI uri);
	
	public abstract String doRequestGet(String url, HashMap<String, String> params);

	/**
	 * Retrieves an image from url
	 * @param url
	 * @return bitmap
	 */
	public abstract Bitmap getImage(String url);
	
	/**
	 * Opens a filestream.
	 * @param url that retrieves a file (i.e. image)
	 * @return InputStream
	 */
	public abstract InputStream getFileStream(String url);
	
	
	/**
	 * 
	 * @param response
	 * @return
	 */
	public String getResponseBody(HttpResponse response);
	
       
}
