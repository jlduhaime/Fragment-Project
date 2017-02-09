package edu.fsu.cs.mobile.hw3pt2;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;

import static edu.fsu.cs.mobile.hw3pt2.R.styleable.View;

public class MainActivity extends AppCompatActivity {

    MyReceiver receiver;

    String[] siteList = {"http://google.com", "http://yahoo.com",
            "http://ww2.cs.fsu.edu/~yannes/index.html", "..."};

    String url = siteList[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent i = getIntent();

        if (i.getStringExtra("link") != null)
        {
            url = i.getStringExtra("link");
            setNew(i.getStringExtra("link"));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setUrl(String u)
    {
        url = u;
    }

    public void setNew(String n) {
        siteList[3] = n;

        FragmentManager manager = getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        ListFragment lf = new ListFragment();
        trans.replace(R.id.list, lf);
        trans.commit();
    }

    public String getUrl() {
        return url;
    }

    public String[] getStrings() { return siteList; }

    public void refreshSite() {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        WebFragment neww = new WebFragment();
        trans.replace(R.id.web, neww);
        trans.commit();
    }
}
