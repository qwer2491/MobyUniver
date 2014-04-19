package com.gh.MobyUniver;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class KursesFragment extends Fragment implements AdapterView.OnItemClickListener{
    static ListView lvMain;
    static View view;
    static final int JSON_LENGTH = 6;
    static Bitmap[] image = new Bitmap[JSON_LENGTH];
    ArrayList<CoursesItems> kurses = new ArrayList<CoursesItems>();
    ImgGet gth;
    static String img_url[] = new String[JSON_LENGTH];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.kurses_layout_fragment,
                container, false);
        lvMain = (ListView) view.findViewById(R.id.lvMain);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        JSONParse jParse = new JSONParse();
        gth = new ImgGet();
        gth.execute(img_url);
        jParse.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(getActivity(), ""+i, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), ArticleActivity.class);
        intent.putExtra("id",kurses.get(i).getId());
        intent.putExtra("img", kurses.get(i).courseIcon);
        intent.putExtra("video", kurses.get(i).getVideo());
        startActivity(intent);

    }

    private class JSONParse extends AsyncTask<Void, Void, JSONArray> {
        private static final String url = "http://gkurs.esy.es/kurses.php";
        @Override
        protected JSONArray doInBackground(Void... voids) {
            com.gh.MobyUniver.Jp jp = new com.gh.MobyUniver.Jp();
            // Getting JSON from URL
            JSONArray json = jp.getJSONFromUrl(url);
            return json;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.findViewById(R.id.progressLayout).setVisibility(View.VISIBLE);
        }
        @Override
        protected void onPostExecute(JSONArray json) {
            view.findViewById(R.id.progressLayout).setVisibility(View.GONE);

            KursesAdapter adapter;
            int items_images[] = {
                    R.drawable.android,
                    R.drawable.html5,
                    R.drawable.java,
                    R.drawable.sql,
                    R.drawable.ubuntu,
                    R.drawable.unity3d
            };

            try {
                JSONObject c = null;
                //image = new Bitmap[json.length()];
                for(int i=0; i< json.length(); i++){
                    c = json.getJSONObject(i);
                    img_url[i] = "http://gkurs.esy.es/images/"+c.getString("img");
                }

                for(int i= 0; i< json.length(); i++) {
                    c = json.getJSONObject(i);
                    kurses.add(new CoursesItems(c.getString("id_kurs"), c.getString("title"), img_url[i], c.getString("video")));
                    // Toast.makeText(getActivity(), img_url[i], Toast.LENGTH_SHORT).show();
                }
                adapter = new KursesAdapter(getActivity(), kurses);
                lvMain.setAdapter(adapter);
                lvMain.setOnItemClickListener(KursesFragment.this);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private class ImgGet extends AsyncTask<String, Void, Void> {
        public  Bitmap getImageBitmap(String url) {
            Bitmap bm = null;
            try {
                URL aURL = new URL(url);
                URLConnection conn = aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bm = BitmapFactory.decodeStream(bis);
                bis.close();
                is.close();
            } catch (IOException e) {
                Log.e("GetBitmap: ", "Error getting bitmap", e);
            }
            return bm;
        }
        @Override
        protected Void doInBackground(String... urls) {
            for (int i = 0; i< JSON_LENGTH; i++) {
                image[i] = getImageBitmap(urls[i]);
            }
            return null;
        }
    }
}
