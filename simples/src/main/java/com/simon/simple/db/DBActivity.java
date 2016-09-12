package com.simon.simple.db;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.simon.simple.R;
import com.simon.simple.base.util.LogUtil;
import com.simon.simple.db.helper.SQLiteHelper;
import com.simon.simple.db.helper.SQLiteOperator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DBActivity extends AppCompatActivity {
    public static String TAG = DBActivity.class.getSimpleName();

    @BindView (R.id.db_btn_insert)
    Button dbBtnInsert;
    @BindView (R.id.db_btn_update)
    Button dbBtnUpdate;
    @BindView (R.id.db_btn_delete)
    Button dbBtnDelete;
    @BindView (R.id.db_btn_search)
    Button dbBtnSearch;

    private SQLiteHelper dbHelper;
    private SQLiteOperator dbOperator;

    int id = 101;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, DBActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        dbHelper = new SQLiteHelper(DBActivity.this);
        dbOperator = new SQLiteOperator(dbHelper.getWritableDatabase());

        ButterKnife.bind(this);
    }

    @OnClick ({R.id.db_btn_insert, R.id.db_btn_update, R.id.db_btn_delete, R.id.db_btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.db_btn_insert:
                dbOperator.insert(id, (int) (Math.random() * 1000));
                id++;
                break;
            case R.id.db_btn_update:
                dbOperator.update(103, 100);
                break;
            case R.id.db_btn_delete:
                dbOperator.delete(104);
                break;
            case R.id.db_btn_search:
                List<String> list = dbOperator.find();
                LogUtil.i(TAG, "onClick: list=" + list.toString());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
        dbOperator.close();
    }
}
