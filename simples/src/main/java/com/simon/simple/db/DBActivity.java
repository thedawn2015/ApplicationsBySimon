package com.simon.simple.db;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.simon.baseandroid.util.BaseActivity;
import com.simon.baseandroid.util.LogUtil;
import com.simon.simple.R;
import com.simon.simple.db.helper.SQLiteHelper;
import com.simon.simple.db.helper.SQLiteOperator;
import com.simon.simple.db.model.MyMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class DBActivity extends BaseActivity {
    public static String TAG = DBActivity.class.getSimpleName();

    @BindView (R.id.db_btn_insert)
    Button dbBtnInsert;
    @BindView (R.id.db_btn_update)
    Button dbBtnUpdate;
    @BindView (R.id.db_btn_delete)
    Button dbBtnDelete;
    @BindView (R.id.db_btn_search)
    Button dbBtnSearch;
    @BindView (R.id.db_btn_realm_insert)
    Button dbBtnRealmInsert;
    @BindView (R.id.db_btn_realm_update)
    Button dbBtnRealmUpdate;
    @BindView (R.id.db_btn_realm_delete)
    Button dbBtnRealmDelete;
    @BindView (R.id.db_btn_realm_search)
    Button dbBtnRealmSearch;

    private SQLiteHelper dbHelper;
    private SQLiteOperator dbOperator;

    int id = 101;

    private Realm realm;
    private boolean flag = false;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, DBActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        //        dbHelper = new SQLiteHelper(DBActivity.this);
        //        dbOperator = new SQLiteOperator(dbHelper.getWritableDatabase());

        ButterKnife.bind(this);

        realm = Realm.getInstance(this);
    }

    @OnClick ({R.id.db_btn_insert, R.id.db_btn_update, R.id.db_btn_delete, R.id.db_btn_search,
            R.id.db_btn_realm_insert, R.id.db_btn_realm_update, R.id.db_btn_realm_delete, R.id.db_btn_realm_search})
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
            case R.id.db_btn_realm_insert:
                add();
                break;
            case R.id.db_btn_realm_update:
                RealmResults<MyMessage> mmResults = search();
                realm.beginTransaction();
                for (int i = mmResults.size() - 1; i >= 0; i--) {
                    mmResults.get(i).setFlag(true);
                }
                realm.commitTransaction();
                break;
            case R.id.db_btn_realm_delete:
                realm.beginTransaction();
                realm.clear(MyMessage.class);
                realm.commitTransaction();
                break;
            case R.id.db_btn_realm_search:
                searchAll();
                break;
        }
    }

    /**
     * search
     *
     * @return
     */
    private RealmResults<MyMessage> search() {
        RealmResults<MyMessage> mmResults = realm.where(MyMessage.class)
                .equalTo("id", 103)
                .or()
                .equalTo("title", "这是消息标题102")
                .findAll();
        for (MyMessage mmResult : mmResults) {
            LogUtil.i(TAG, "onClick: mmResult.getId()=" + mmResult.getId() + ",mmResult.getTitle()=" + mmResult.getTitle()
                    + ",mmResult.isFlag()=" + mmResult.isFlag());
        }
        return mmResults;
    }

    /**
     * search all
     *
     * @return
     */
    private RealmResults<MyMessage> searchAll() {
        RealmResults<MyMessage> mmResults = realm.where(MyMessage.class)
                .findAll();
        for (MyMessage mmResult : mmResults) {
            LogUtil.i(TAG, "onClick: mmResult.getId()=" + mmResult.getId() + ",mmResult.getTitle()=" + mmResult.getTitle()
                    + ",mmResult.isFlag()=" + mmResult.isFlag());
        }
        return mmResults;
    }

    /**
     * add
     */
    private void add() {
        try {
            realm.beginTransaction();
            MyMessage myMessage = realm.createObject(MyMessage.class);
            myMessage.setId(id);
            myMessage.setTitle("这是消息标题" + id);
            myMessage.setFlag(flag);
            flag = !flag;
            id++;
            realm.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
        if (dbOperator != null) {
            dbOperator.close();
        }
        if (realm != null) {
            realm.close();
        }
    }
}
