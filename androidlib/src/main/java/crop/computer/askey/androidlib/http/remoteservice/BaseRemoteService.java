package crop.computer.askey.androidlib.http.remoteservice;

import java.util.List;

import crop.computer.askey.androidlib.http.request.HeaderField;
import crop.computer.askey.androidlib.http.request.HttpRequest;
import crop.computer.askey.androidlib.http.request.HttpsRequest;
import crop.computer.askey.androidlib.http.request.QueryAttribute;
import crop.computer.askey.androidlib.http.request.Request;
import crop.computer.askey.androidlib.http.request.RequestCallback;
import crop.computer.askey.androidlib.http.request.RequestManager;
import crop.computer.askey.androidlib.http.response.Response;
import crop.computer.askey.androidlib.http.url.URLConfigManager;
import crop.computer.askey.androidlib.http.url.URLInfo;


public abstract class BaseRemoteService
	implements RemoteService {
	
	private RequestManager mRequestManager;
	
	private URLConfigManager mURLConfigManager;
	
	private ServiceDataReceiver dataListener;
	
	public void registerDataReceiver(ServiceDataReceiver dataListener) {
		this.dataListener = dataListener;
	}

	public void unregisterDataReceiver(){
		this.dataListener = null;
	}
	
	protected void invoke(final String key, List<HeaderField> rqProperties, List<QueryAttribute> rqParams,
						  final String requestBody) {
		invoke(key, rqProperties, rqParams, requestBody, this);
	}
	
	@Override
	public void invoke(final String key, List<HeaderField> rqProperties, List<QueryAttribute> rqParams, 
			final String requestBody, RequestCallback callback) {
		
		final URLInfo urlInfo = getURLConfigManager().findURL(key);
		if(urlInfo == null) {
			callback.onFail(key, null, 5, "Cannot find the URLInfo for key: " + key);
			return;
		}
		String url = generateDefaultURLString(urlInfo);
		if(interceptURLString(urlInfo)!=null){
			url = interceptURLString(urlInfo);
		}
		Request request;
		if(url.startsWith("https")){
			request = new HttpsRequest(url);
		}else {
			request = new HttpRequest(url);
		}
		request.setKey(key);
		request.setMethod(urlInfo.getMethod());
		request.setRqProperties(rqProperties);
		request.setRqParams(rqParams);
		request.setBody(requestBody);
		request.setCallback(callback);

		getRequestManager().start();
		getRequestManager().execute(request);
	} 

	public void cancelRequst() {
	    if(getRequestManager() != null) {
	        getRequestManager().cancelRequests();
        }
    }

	private String generateDefaultURLString(URLInfo urlInfo) {
		StringBuilder builder = new StringBuilder();
		if(urlInfo == null) {
			System.out.println("urlInfo == null");
		}
		builder.append(urlInfo.getScheme());
		builder.append(urlInfo.getHost());
		builder.append(urlInfo.getPath());
		return builder.toString();
	} 
	
	protected abstract RequestManager injectRequestManager();
	protected abstract URLConfigManager injectURLConfigManager();
	protected abstract String interceptURLString(URLInfo urlInfo);


	@Override
	public void onSuccess(String key, Response response, String content) {
		if(dataListener != null) {
			dataListener.onSuccess(key, content);
		}
	}

	@Override
	public void onFail(String key, Response response, int errorType, String errorMessage) {
		if(dataListener != null) {
			dataListener.onFail(key, errorType, errorMessage);
		}
	}
	
	public RequestManager getRequestManager() {
		if(mRequestManager == null) {
			mRequestManager = injectRequestManager();
		}
		return mRequestManager;
	}
	
	public URLConfigManager getURLConfigManager() {
		if(mURLConfigManager == null) {
			mURLConfigManager = injectURLConfigManager();
		}
		return mURLConfigManager;
	}
	
	@Override
	public void finish() {
		if(mRequestManager != null){
			mRequestManager.finish();
            mRequestManager = null;
		}
		if(mURLConfigManager != null) {
			mURLConfigManager = null;
		}
		if(dataListener != null) {
            dataListener = null;
        }
	}
}
