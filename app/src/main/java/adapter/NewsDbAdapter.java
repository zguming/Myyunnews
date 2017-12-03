package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yunnews.R;

import java.util.List;

import db.NewsDb;


/**
 * Created by Administrator on 2017/8/9.
 */

public class NewsDbAdapter extends ArrayAdapter<NewsDb> {
    private int resourceId;
    public NewsDbAdapter(Context context, int textViewResourceId, List<NewsDb> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        NewsDb news=getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.title= (TextView) view.findViewById(R.id.news_title);
            viewHolder.time= (TextView) view.findViewById(R.id.news_time);
            viewHolder.pic= (ImageView) view.findViewById(R.id.news_pic);
            viewHolder.src= (TextView) view.findViewById(R.id.news_src);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(news.getTitle());
        viewHolder.time.setText(news.getTime());
        viewHolder.src.setText(news.getSrc());
        Glide.with(getContext()).load(news.getPicurl())
                .override(160, 110)
                .centerCrop()
                .into(viewHolder.pic);
        return view;
    }
    class ViewHolder{
        TextView title;
        TextView time;
        ImageView pic;
        TextView src;
    }
}