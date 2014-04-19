package com.gh.MobyUniver;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class ArticleFragment extends Fragment {
    static View view;
    String id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.article_layout_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        id = getActivity().getIntent().getExtras().getString("id");
        Drawable d = new BitmapDrawable(getResources(),(Bitmap) getActivity().getIntent().getParcelableExtra("img"));
        VideoView vid = (VideoView) view.findViewById(R.id.videoLess);
        getActivity().getActionBar().setIcon(d);
        new JSONParseArticle().execute();
        String videoSourse = "http://gkurs.esy.es/Video/"+getActivity().getIntent().getExtras().getString("video");
        vid.setVideoURI(Uri.parse(videoSourse));
        vid.setMediaController(new MediaController(getActivity()));
        vid.requestFocus(0);
        vid.start();
    }


    private class JSONParseArticle extends AsyncTask<Void, Void, String> {
        private static final String url = "http://gkurs.esy.es/article.php";
        @Override
        protected String doInBackground(Void... voids) {
            JSONParser jParser = new JSONParser();
            String json = jParser.getJSONFromUrl(url, id);
            return json;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //view.findViewById(R.id.progressLayout).setVisibility(View.VISIBLE);
        }
        @Override
        protected void onPostExecute(String json) {
            //view.findViewById(R.id.progressLayout).setVisibility(View.GONE);

            ((EditText) view.findViewById(R.id.editText)).setText(json);//("content"));
            /*try {
               // JSONObject c = json.getJSONObject(0);
               // byte[] tmp = (c.getString("content")).getBytes();
                try {
                    //((TextView) view.findViewById(R.id.textView)).setText(new String(tmp,"UTF-8"));


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        }
    }
}
