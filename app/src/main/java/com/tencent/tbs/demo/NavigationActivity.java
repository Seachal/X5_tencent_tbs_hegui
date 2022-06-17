package com.tencent.tbs.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.tencent.tbs.demo.viewutils.FeatureManager;
import com.tencent.tbs.demo.viewutils.FeatureManager.FeatureItem;
import com.tencent.tbs.demo.viewutils.SectionListAdapter;


public class NavigationActivity extends AppCompatActivity {

    private final String TAG = "NavigationActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        initNavList();
    }

    private void initNavList() {
        ListView listView = (ListView) findViewById(R.id.list_base_func);
        SectionListAdapter adapter = new SectionListAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                handleItem(position);
            }
        });
        for (FeatureItem item: FeatureManager.featureItems) {
            if (item.type == FeatureManager.TYPE_SEPARATOR) {
                adapter.addSectionHeaderItem(item.featureName);
            } else {
                adapter.addItem(item.featureName);
            }
        }
    }

    private void handleItem(int position) {
        Intent intent = new Intent(this, FeatureManager.getActivity(position));
        intent.putExtra("url", FeatureManager.getUrl(position));
        startActivity(intent);
    }

}
