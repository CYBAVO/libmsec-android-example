package com.cybavo.example.msec;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cybavo.security.mobile.DeviceSecurity;

public class MainActivity extends AppCompatActivity implements DeviceSecurityTask.Listener {

    private static final String TAG = "DeviceSecurity";

    private DeviceSecurity.State mState;

    private TextView mJailBroken;
    private TextView mHook;
    private TextView mEmulator;
    private TextView mVirtualApp;
    private TextView mMockLocation;
    private TextView mExtStorage;
    private TextView mDevSettings;
    private TextView mDebugger;
    private TextView mAdb;

    private Button mDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJailBroken = findViewById(R.id.sec_item_jail_broken);
        mHook = findViewById(R.id.sec_item_hook);
        mEmulator = findViewById(R.id.sec_item_emulator);
        mVirtualApp = findViewById(R.id.sec_item_virtual_app);
        mMockLocation = findViewById(R.id.sec_item_mock_location);
        mExtStorage = findViewById(R.id.sec_item_ext_storage);
        mDevSettings = findViewById(R.id.sec_item_dev_settings);
        mDebugger = findViewById(R.id.sec_item_debugger);
        mAdb = findViewById(R.id.sec_item_adb);

        // show init state
        showSecurityState();

        // fetch security state
        fetchDeviceSecurityState();

        // manual refresh state
        mDetect = findViewById(R.id.detect);
        mDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDeviceSecurityState();
            }
        });
    }

    @Override
    public void onCompleted(DeviceSecurity.State state) {
        Log.d(TAG, "onCompleted:" + state);
        mState = state;
        // refresh UI
        showSecurityState();
    }

    private void fetchDeviceSecurityState() {
        new DeviceSecurityTask(this).execute(getApplicationContext());
    }

    private void showSecurityState() {
        if (mState == null) {
            showState(mJailBroken, R.string.label_security_jail_broken, null);
            showState(mHook, R.string.label_security_hook, null);
            showState(mEmulator, R.string.label_security_emulator, null);
            showState(mVirtualApp, R.string.label_security_virtual_app, null);
            showState(mMockLocation, R.string.label_security_mock_location, null);
            showState(mExtStorage, R.string.label_security_ext_storage, null);
            showState(mDevSettings, R.string.label_security_dev_settings, null);
            showState(mDebugger, R.string.label_security_debugger, null);
            showState(mAdb, R.string.label_security_adb, null);
            return;
        }
        showState(mJailBroken, R.string.label_security_jail_broken, mState.isJailBroken);
        showState(mHook, R.string.label_security_hook, mState.isHooked);
        showState(mEmulator, R.string.label_security_emulator, mState.isEmulator);
        showState(mVirtualApp, R.string.label_security_virtual_app, mState.isVirtualApp);
        showState(mMockLocation, R.string.label_security_mock_location, mState.isMockLocationEnabled);
        showState(mExtStorage, R.string.label_security_ext_storage, mState.isOnExternalStorage);
        showState(mDevSettings, R.string.label_security_dev_settings, mState.isDevelopmentSettingsEnabled);
        showState(mDebugger, R.string.label_security_debugger, mState.isDebuggingEnabled);
        showState(mAdb, R.string.label_security_adb, mState.isAdbEnabled);
    }

    private void showState(TextView view, @StringRes int labelRes, Boolean detected) {
        view.setText(getString(labelRes, getString(
                detected == null ? R.string.label_unknown :
                detected ? R.string.label_detected :
                        R.string.label_undetected)));
        view.setTextColor(getResources().getColor(detected == null ? android.R.color.darker_gray :
                detected ? android.R.color.holo_red_light :
                        android.R.color.holo_green_light));
    }
}
