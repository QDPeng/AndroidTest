package com.matinallight.dial;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String TAG = DialView.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DialView dialView = (DialView) findViewById(R.id.dialView);
        DialViewAdapter adapter=new DialViewAdapter(this);
        dialView.setAdapter(adapter);
        dialView.setOnItemClickListener(new DialView.OnDialViewItemClickListener() {
            @Override
            public void onItemClickListenr(View view, int position) {
                Toast.makeText(MainActivity.this,view.getClass().getSimpleName()+"==="+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    class DialViewAdapter extends BaseAdapter {
        private Context mContext;

        public DialViewAdapter(Context context){
            mContext=context;
        }

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(mContext);
            textView.setText("TextView" + position);
            textView.setBackgroundColor(Color.YELLOW);
            return textView;
        }
    }
}
