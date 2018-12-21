package crop.computer.askey.androidlib.http.request;

public class ThreadRequestManager
	implements RequestManager{

	@Override
	public void start() {

	}

	@Override
	public void execute(Request request) {
		new Thread(request).start();
	}

	@Override
	public void cancelRequests() {

	}

	@Override
	public void finish() {
		System.out.println("RequestManager finish");
	}

}
