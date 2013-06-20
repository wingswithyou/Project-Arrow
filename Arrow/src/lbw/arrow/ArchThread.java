package lbw.arrow;

import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class ArchThread implements Runnable {

	@Override
	public void run() {

		HttpClient httpclient = Prepare.genHttpClient();

		String url = Prepare.genUrl();
		GetMethod httpget = new GetMethod(url);

		// System.out.println("To excute.");

		int result = -1;
		try {
			result = httpclient.executeMethod(httpget);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		
		// Display status code
		System.out.println(Calendar.getInstance().getTime() + "¡¾" + plusCount()
				+ "¡¿ status: " + result);

		try {
			if (result == 200) {
				String str = "?";
				str = httpget.getResponseBodyAsString();
				System.out.println(str);
			}
			// Release current connection to the connection pool once you are
			// done

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			httpget.releaseConnection();
			selfReStart();
		}
	}

	public void selfReStart() {
		ArchThread archThread = new ArchThread();
		Bow.pool.execute(archThread);
	}

	public synchronized int plusCount() {
		Bow.count++;
		return Bow.count;
	}
}
