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

import com.hedgehog.ratingbar.RatingBar;
import com.lsp.test.R;
import com.lsp.test.utils.LogUtil;
import com.lsp.test.utils.ToastUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.techery.properratingbar.ProperRatingBar;
import io.techery.properratingbar.RatingListener;

/**
 * Created by xian on 2015/10/12.
 */
public class HttpFragment extends BaseFragment {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button loginButton;
    ProperRatingBar ratingBar;
    public static final String regex = "^[\\u4eac|\\u6d25|\\u6caa|\\u6e1d|\\u5180|\\u8c6b|\\u4e91|\\u8fbd|\\u9ed1|\\u6e58|\\u7696|\\u9c81|\\u65b0|\\u82cf|\\u6d59|\\u8d63|\\u9102|\\u6842|\\u7518|\\u664b|\\u8499|\\u9655|\\u5409|\\u95fd|\\u8d35|\\u7ca4|\\u9752|\\u85cf|\\u5ddd|\\u5b81|\\u743c]{1}[A-Z]{1}[\\s]{1}[A-Z_0-9]{5}$";//粤A 88888
    public static final String regex2 = "^[\\u7ca4]{1}[A-Z]{1}[\\s]{1}[A-Z_0-9]{4}[\\u6e2f|\\u6fb3]{1}$";//粤Z J789港，粤Z J789澳

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
//                mPasswordView.setError("id not valid");
//                Snackbar.make(mEmailView, "i'm snackbar!", Snackbar.LENGTH_LONG).setAction("nnn", null).show();
                String content = mEmailView.getText().toString();
                isMatch(content);
            }
        });

        ratingBar.setListener(new RatingListener() {
            @Override
            public void onRatePicked(ProperRatingBar ratingBar) {
                Snackbar.make(mEmailView, "" + ratingBar.getRating(), Snackbar.LENGTH_LONG).setAction("nnn", null).show();
            }
        });
    }

    private void initView(View view) {
        mEmailView = (AutoCompleteTextView) view.findViewById(R.id.http_fragment_email);
        mPasswordView = (EditText) view.findViewById(R.id.http_fragment_password);
        loginButton = (Button) view.findViewById(R.id.http_fragment_sign_in);
        ratingBar = (ProperRatingBar) view.findViewById(R.id.http_fragment_ratingBar);
        RatingBar mRatingBar = (RatingBar) view.findViewById(R.id.http_fragment_ratingbar2);

    }

    public boolean isMatch(String content) {
        Pattern pattern = Pattern.compile(regex2);
        Matcher matcher = pattern.matcher(content);
        if (matcher.matches()) {
            ToastUtil.showShort(getActivity(), "true");
            return true;
        }
        ToastUtil.showShort(getActivity(), "false");
        return false;
    }

}
