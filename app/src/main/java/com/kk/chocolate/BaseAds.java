package com.kk.chocolate;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

class BaseAds {

    protected Context context;
    protected Handler handler = new Handler(Looper.getMainLooper());
    private TextView logView;
    protected ViewGroup inviewParent;

    BaseAds(Context context, TextView logView, ViewGroup inviewParent) {
        this.context = context;
        this.logView = logView;
        this.inviewParent = inviewParent;
    }

    protected void log(final String tag, final String msg) {
        handler.post(() -> {
            logView.getEditableText().append(tag + ": " + msg + "\r\n");
        });
    }

    protected void alert(final String msg) {
        handler.post(() -> new AlertDialog.Builder(context).setMessage(msg).create().show());
    }

    protected void toast(final String msg) {
        handler.post(() -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show());
    }
}
