package crop.computer.askey.androidlib.http.remoteservice;

import java.util.List;

import crop.computer.askey.androidlib.http.request.HeaderField;
import crop.computer.askey.androidlib.http.request.QueryAttribute;
import crop.computer.askey.androidlib.http.request.RequestCallback;


public interface RemoteService extends RequestCallback {
	
	void finish();
	void invoke(final String key, List<HeaderField> headers, List<QueryAttribute> queryAtts, final String requestBody, RequestCallback callback);
}
