package crop.computer.askey.androidlib.http.request;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ExecutorRequestManager implements RequestManager {
	
	private ExecutorService executor;

	private boolean isStarted = false;

    @Override
    public void start() {
        if(!isStarted) {
            executor = Executors.newSingleThreadExecutor();
            isStarted = true;
        }
    }

    @Override
	public void execute(Request request) {
		executor.execute(request);
	}

    @Override
    public void cancelRequests() {
        if(!executor.isShutdown()) {
            executor.shutdownNow();
            isStarted = false;
        }
    }

    @Override
	public void finish() {
        cancelRequests();
	}

}
