package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.yunnews.DataCleanManager;
import com.example.yunnews.R;

public class SetFragment extends Fragment implements View.OnClickListener{
	//private RelativeLayout nLayout;
	//private RelativeLayout pLayout;
	private RelativeLayout cLayout;
	View mView;
	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_set, container,false);
		findViews();
		return mView;
	}
	public void findViews() {

		/*nLayout = (RelativeLayout) mView
				.findViewById(R.id.layout_mine_name);
		nLayout.setOnClickListener(this);
		pLayout = (RelativeLayout) mView
				.findViewById(R.id.layout_mine_photo);
		pLayout.setOnClickListener(this);*/
		cLayout = (RelativeLayout) mView.findViewById(R.id.layout_clean);
		cLayout.setOnClickListener(this);
	}
	public void toast() {
		Toast.makeText(getContext(), "缓存已清除",
				Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onClick(View view) {

		switch (view.getId()) {
			case R.id.layout_clean:
				DataCleanManager.cleanInternalCache(getContext());
				toast();
				break;
			default:
				break;
		}

	}
}
