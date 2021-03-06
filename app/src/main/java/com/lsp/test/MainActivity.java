package com.lsp.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lsp.shortvideo.VideoActivity;
import com.lsp.test.fabprogresscircle.FABProgressCircleActivity;
import com.lsp.test.flowlayout.FlowFragment;
import com.lsp.test.fragment.BaseFragment;
import com.lsp.test.fragment.DesFragment;
import com.lsp.test.fragment.HttpFragment;
import com.lsp.test.fragment.WaveViewFragment;
import com.lsp.test.netty.NettyActivity;
import com.lsp.test.utils.ToastUtil;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DesFragment desFragment = new DesFragment();
    private HttpFragment httpFragment = new HttpFragment();
    private WaveViewFragment waveViewFragment = new WaveViewFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtil.showShort(MainActivity.this, "hello world!!");
                            }
                        }).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        addFragment(desFragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_des_fragment:
                addFragment(desFragment);
                break;
            case R.id.nav_http_fragment:
                addFragment(httpFragment);
                break;
            case R.id.wave_view_fragment:
                addFragment(waveViewFragment);
                break;
            case R.id.flow_fragment:
                addFragment(new FlowFragment());
                break;
            case R.id.fabProgressCircle:
                Intent intent = new Intent(MainActivity.this, FABProgressCircleActivity.class);
                startActivity(intent);
                break;
            case R.id.video_activity://跳转到short-video-lib VideoActivity
                Intent intent2 = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intent2);
                break;
            case R.id.netty_activity:
                Intent intent3 = new Intent(MainActivity.this, NettyActivity.class);
                startActivity(intent3);
                break;
//            case R.id.nine_old_anim_demo:
//                Intent intent4 = new Intent(MainActivity.this, NineOldAnimDemos.class);
//                startActivity(intent4);
//                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addFragment(BaseFragment fragment) {
        FragmentManager ft = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = ft.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container, fragment);
        fragmentTransaction.commit();

    }

    @Override
    protected void onDestroy() {
        try {
            getSupportFragmentManager().getFragments().clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
