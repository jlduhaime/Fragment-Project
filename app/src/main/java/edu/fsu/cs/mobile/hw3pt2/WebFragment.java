package edu.fsu.cs.mobile.hw3pt2;

import android.app.*;
import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class WebFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    WebView wv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);

        wv = (WebView) view.findViewById(R.id.web_view);
        wv.getSettings().setJavaScriptEnabled(true);
        String url = ((MainActivity)getActivity()).getUrl();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        wv.setWebViewClient(new WebViewClient() {
           @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
               return false;
           }
        });
        wv.loadUrl(url);
        return view;
    }
}
