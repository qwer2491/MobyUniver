package com.gh.MobyUniver;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;


public class ArticleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_layout);
        FragmentTransaction mng = getFragmentManager().beginTransaction();
        ArticleFragment fr;
    if(savedInstanceState == null)    {
        fr = new ArticleFragment();
        mng.add(R.id.articleFrame, fr).commit();
    }
        ActionBar bar = getActionBar();
        bar.setHomeButtonEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: finish();

        }   return super.onOptionsItemSelected(item);
    }
}
