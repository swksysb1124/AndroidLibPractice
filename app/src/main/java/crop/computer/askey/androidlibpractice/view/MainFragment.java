package crop.computer.askey.androidlibpractice.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import crop.computer.askey.androidlibpractice.R;
import crop.computer.askey.androidlibpractice.controller.MainActivity;
import crop.computer.askey.androidlibpractice.presenter.MainPresenterI;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment
    implements MainViewI {

    private MainPresenterI mPresenter;

    private TextView txtResult;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.testGetAtMain();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        setPresenter(activity.getPresenter());
        mPresenter.setMainView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        txtResult = root.findViewById(R.id.txt_result);

        root.findViewById(R.id.btn_to_sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.toSubView();
            }
        });

        return root;
    }

    @Override
    public void setPresenter(MainPresenterI presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar(Object pbInfo) {

    }

    @Override
    public void updateProgress(Object progress) {

    }

    @Override
    public void hideProgressBar(Object pbInfo) {

    }

    @Override
    public void showMessage(Object msgInfo) {

    }

    @Override
    public void hideMessage(Object msgInfo) {

    }

    @Override
    public void goToPage(Object pageInfo) {

    }

    @Override
    public void runOnUiThread(Runnable task) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.setMainView(null);
        setPresenter(null);
    }

    @Override
    public void showGetResult(String result) {
        txtResult.setText(result);
    }
}
