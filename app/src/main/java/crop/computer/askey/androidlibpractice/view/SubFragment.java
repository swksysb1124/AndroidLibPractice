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
import crop.computer.askey.androidlibpractice.presenter.MainPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubFragment extends Fragment
    implements SubView{

    private MainPresenter mPresenter;

    private TextView txtResult;

    public SubFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.tesPostAtSub();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        setPresenter(activity.getPresenter());
        mPresenter.setSubView(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sub, container, false);
        txtResult = root.findViewById(R.id.txt_result);

        root.findViewById(R.id.btn_to_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.toMainView();
            }
        });

        return root;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.setSubView(this);
        setPresenter(null);
    }

    @Override
    public void setPresenter(MainPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showPostResult(String result) {
        txtResult.setText(result);
    }
}
