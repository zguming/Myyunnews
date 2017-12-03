package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yunnews.R;
import com.example.yunnews.TabDb;

public class VedioFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_vedio, container,false);
		((TextView) view.findViewById(R.id.fm_text)).setText(TabDb.getTabsTxt()[1]);
		return view;
	}
}
