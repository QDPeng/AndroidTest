package com.lsp.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lsp.test.R;
import com.lsp.test.utils.LogUtil;

/**
 * Created by xian on 2015/10/12.
 */
public class HttpFragment extends BaseFragment {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button loginButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_http, container, false);
        initView(view);
        initListener();
        return view;
    }

    private void initListener() {
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.action_login || actionId == EditorInfo.IME_NULL) {
                    LogUtil.i("login", "attempt login");
                    return true;
                }
                return false;
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPasswordView.setError("id not valid");
                Snackbar.make(mEmailView,"i'm snackbar!",Snackbar.LENGTH_LONG).setAction("nnn",null).show();
            }
        });
    }

    private void initView(View view) {
        mEmailView = (AutoCompleteTextView) view.findViewById(R.id.http_fragment_email);
        mPasswordView = (EditText) view.findViewById(R.id.http_fragment_password);
        loginButton = (Button) view.findViewById(R.id.http_fragment_sign_in);

    }
}
