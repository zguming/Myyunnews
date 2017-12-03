package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.yunnews.R;

import activity.HistoryActivity;
import activity.MyCollectionActivity;
import activity.PersonalMessageActivity;


public class MineFragment extends Fragment implements OnClickListener {

    private View mView;
    private Context context;
    private RelativeLayout pLayout;
    private RelativeLayout cLayout;
    private RelativeLayout hLayout;
    private RelativeLayout aLayout;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            context = getActivity();
            mView = inflater.inflate(R.layout.fragment_mine, null, true);
            findViews();
        return mView;
    }

    public void findViews() {

        pLayout = (RelativeLayout) mView
                .findViewById(R.id.layout_mine_personal);
        pLayout.setOnClickListener(this);
        cLayout = (RelativeLayout) mView
                .findViewById(R.id.layout_mine_collection);
        cLayout.setOnClickListener(this);
        hLayout = (RelativeLayout) mView.findViewById(R.id.layout_mine_history);
        hLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            //case R.id.layout_mine_personal:

                //Intent pIntent = new Intent(context, PersonalMessageActivity.class);
                //startActivity(pIntent);
                //break;
            case R.id.layout_mine_collection:

                Intent cIntent = new Intent(context, MyCollectionActivity.class);
                startActivity(cIntent);
                break;
            case R.id.layout_mine_history:

                Intent hIntent = new Intent(context, HistoryActivity.class);
                startActivity(hIntent);
                break;
            default:
                break;
        }

    }

}
