package com.lsp.test.fabprogresscircle;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.github.jorgecastilloprz.FABProgressCircle;
import com.github.jorgecastilloprz.listeners.FABProgressListener;
import com.lsp.test.R;
import com.lsp.test.fabprogresscircle.executor.ThreadExecutor;
import com.lsp.test.fabprogresscircle.interactor.MockAction;
import com.lsp.test.fabprogresscircle.interactor.MockActionCallback;
import com.lsp.test.fabprogresscircle.picasso.GrayscaleCircleTransform;
import com.squareup.picasso.Picasso;

/**
 * Created by xian on 2015/11/7.
 */
public class FABProgressCircleActivity extends AppCompatActivity implements MockActionCallback, FABProgressListener {
    private FABProgressCircle fabProgressCircle;
    private boolean taskRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_progress_circle);
        initViews();
        loadAvatar();
        attachListeners();
    }

    private void initViews() {
        fabProgressCircle = (FABProgressCircle) findViewById(R.id.fabProgressCircle);
    }

    private void loadAvatar() {
        ImageView avatarView = (ImageView) findViewById(R.id.avatar);
        Picasso.with(this)
                .load(R.drawable.avatar)
                .transform(new GrayscaleCircleTransform())
                .into(avatarView);
    }

    private void attachListeners() {
        fabProgressCircle.attachListener(this);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!taskRunning) {
                    fabProgressCircle.show();
                    runMockInteractor();
                }
            }
        });
    }

    private void runMockInteractor() {
        ThreadExecutor executor = new ThreadExecutor();
        executor.run(new MockAction(this));
        taskRunning = true;
    }

    @Override
    public void onMockActionComplete() {
        taskRunning = false;
        fabProgressCircle.beginFinalAnimation();
        //fabProgressCircle.hide();
    }

    @Override
    public void onFABProgressAnimationEnd() {
        Snackbar.make(fabProgressCircle, R.string.cloud_upload_complete, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
    }
}
