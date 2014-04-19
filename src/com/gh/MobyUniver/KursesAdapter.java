package com.gh.MobyUniver;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;

public class KursesAdapter extends BaseAdapter {
    private static final String TAG ="RR" ;

    Context context;
    LayoutInflater lInflater;
    ArrayList<CoursesItems> objects;
    ImageLoader imageLoader;

    KursesAdapter(Context context, ArrayList<CoursesItems> items) {
        this.context = context;
        objects = items;
        lInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       // new ImgGet().execute();
        this.imageLoader = ImageLoader.getInstance();


        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .considerExifParams(true)
                .cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY + 4)
                .threadPoolSize(5)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(displayImageOptions)
                .build();

        this.imageLoader.init(configuration);

    }


    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // товар по позиции
    CoursesItems getItemObj(int position) {
        return ((CoursesItems) getItem(position));
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view = convertView;

    //ViewHolder Pattern
        if (view == null) {
            view = lInflater.inflate(R.layout.kurses_items_adaper, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewCourseName = (TextView)view.findViewById(R.id.kursDescr);
            viewHolder.imageViewCourseIcon = (ImageView)view.findViewById(R.id.kursImage);
            view.setTag(viewHolder);
            }
        else {
            viewHolder=(ViewHolder)view.getTag();
        }


        viewHolder.textViewCourseName.setText(objects.get(position).courseName);
        imageLoader.displayImage(objects.get(position).courseIcon,viewHolder.imageViewCourseIcon );

        return view;
    }

    private class ViewHolder{
       public TextView textViewCourseName;
       public ImageView imageViewCourseIcon;


    }

}