package com.asicosilomu.assigned;

import android.util.Base64;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowserController extends WebViewClient {
    Boolean canNavigateInWebview;

    {
        canNavigateInWebview = true;
    }

    Boolean FirstTime = true;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (canNavigateInWebview) {
            view.loadUrl(request.getUrl().toString());
            return true;
        } else {
            if (FirstTime) {
                view.loadUrl(request.getUrl().toString());
                FirstTime = false;
                return true;
            } else {
                String unencodedHtml =
                        "<html><body style='background-color: black;'><p style='color: red'>The web browser is restricted to a single page.</p></body></html>";
                String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(),
                        Base64.NO_PADDING);
                view.loadData(encodedHtml, "text/html", "base64");
                return false;
            }
        }
    }

    public void setWebviewNavigation(Boolean val) {
        canNavigateInWebview = val;
    }
}
