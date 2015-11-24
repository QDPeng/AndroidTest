package com.lsp.shortvideo;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

/**
 * Created by xian on 2015/11/23.
 * 菜单，跳转到recoderActivity 进行录制
 */
public class VideoActivity extends AppCompatActivity {
    // 录制按钮
    private Button btnRecordAudio;
    // 播放按钮
    private ImageButton btnPlay;
    // 文件路径
    private String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        init();
    }

    private void init() {


        btnRecordAudio = (Button) findViewById(R.id.btn_record_audio);
        btnPlay = (ImageButton) findViewById(R.id.play);
//        File filePathFile = new File("/storage/emulated/0/im/video/");
//        if (filePathFile != null && filePathFile.listFiles() != null) {
//            if (filePathFile.listFiles().length > 0) {
//                path = filePathFile.listFiles()[0].getPath();
//                Bitmap bitmap = Utils.createVideoThumbnail(path);
//                BitmapDrawable drawable = new BitmapDrawable(bitmap);
//                drawable.setTileModeXY(Shader.TileMode.REPEAT,
//                        Shader.TileMode.REPEAT);
//                drawable.setDither(true);
//                btnPlay.setBackgroundDrawable(drawable);
//            }
//        }
        btnRecordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 启动拍摄的Activity
                Intent intent = new Intent(VideoActivity.this, RecorderActivity.class);
                startActivityForResult(intent, 200);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                // 显示播放页面
                if (path != null && !path.equalsIgnoreCase("")) {
                    VideoFragment bigPic = VideoFragment.newInstance(path);
                    android.app.FragmentManager mFragmentManager = getFragmentManager();
                    FragmentTransaction transaction = mFragmentManager.beginTransaction();
                    transaction.replace(R.id.main_menu, bigPic);
                    transaction.commit();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 200:
                if (resultCode == RESULT_OK) {
                    // 成功
                    path = data.getStringExtra("path");
                    Toast.makeText(VideoActivity.this, "存储路径为:" + path, Toast.LENGTH_SHORT).show();
                    // 通过路径获取第一帧的缩略图并显示
                    Bitmap bitmap = Utils.createVideoThumbnail(path);
                    BitmapDrawable drawable = new BitmapDrawable(bitmap);
                    drawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
                    drawable.setDither(true);
                    btnPlay.setBackgroundDrawable(drawable);
                } else {
                    // 失败
                }
                break;

        }
    }
}
