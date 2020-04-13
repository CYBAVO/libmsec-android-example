package com.cybavo.example.msec;

import android.content.Context;
import android.os.AsyncTask;

import com.cybavo.security.mobile.DeviceSecurity;

import java.lang.ref.WeakReference;

public class DeviceSecurityTask extends AsyncTask<Context, Void, DeviceSecurity.State> {

    public interface Listener {
        void onCompleted(DeviceSecurity.State state);
    }

    private final WeakReference<Listener> mListener;

    DeviceSecurityTask(Listener listener) {
        mListener = new WeakReference<>(listener);
    }

    @Override
    protected DeviceSecurity.State doInBackground(Context... params) {
        return DeviceSecurity.getSecurityState(params[0]);
    }

    @Override
    protected void onPostExecute(DeviceSecurity.State state) {
        Listener listener = mListener.get();
        if (listener != null) {
            listener.onCompleted(state);
        }
    }
}
