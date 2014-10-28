package com.example.broilr;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by anniedevine on 10/28/14.
 */

public class GifWebView extends WebView {

    public GifWebView(Context context, String path) {
        super(context);

        loadUrl(path);
    }
}
