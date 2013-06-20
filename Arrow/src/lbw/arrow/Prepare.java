package lbw.arrow;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.cookie.CookiePolicy;

public class Prepare {

	static HttpState initialState = null;
	static Cookie mycookie = null;

	public static ExecutorService genPool() {
		return Executors.newCachedThreadPool();
		// return Executors.newFixedThreadPool(Config.poolSize);
	}

	public static HttpClient genHttpClient() {
		initialState = (initialState != null) ? initialState
				: genInitialState();

		// Get HTTP client instance
		HttpClient httpclient = new HttpClient();
		httpclient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(30000);
		httpclient.setState(initialState);

		// RFC 2101 cookie management spec is used per default
		// to parse, validate, format & match cookies
		httpclient.getParams().setCookiePolicy(CookiePolicy.RFC_2109);
		// A different cookie management spec can be selected
		// when desired

		return httpclient;
	}

	public static HttpState genInitialState() {
		mycookie = (mycookie != null) ? mycookie : genCookie();

		// Get initial state object
		HttpState initialState = new HttpState();
		// Initial set of cookies can be retrieved from persistent storage and
		// re-created, using a persistence mechanism of choice,

		// and then added to your HTTP state instance
		initialState.addCookie(mycookie);
		return initialState;
	}

	public static Cookie genCookie() {
		return new Cookie(Config.host, "SiRuiUser", Config.cookieContent, "/",
				null, false);
	}

	public static String genUrl() {
		// Get target URL
		String idstr = "id=" + Config.targetId;

		String strURL = "http://" + Config.host + "/job/Apply_Ajax.aspx?"
				+ idstr;
		System.out.println("Target URL: " + strURL);
		return strURL;
	}

}
