package com.softdesign.school;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.softdesign.school.utils.Lg;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CheckBox mCheckBox;
    EditText mEditText;
    EditText mEditText2;
    android.support.v7.widget.Toolbar mToolBar;
    /*
     кнопки меняющие цвет тулбара и статус бара
     */
    Button mGreen;
    Button mBlue;
    Button mRed;

 /*
 Ключи для сохранения цвета
  */
    public static final String VISIBLE_KEY = "visible";
    public static final String TOOLBAR_COLOR_KEY = "toolBarColorKey";
    public static final String STATUSBAR_COLOR_KEY = "statusBarColorKey";

    public static int newToolBarColor =R.color.colorPrimary;
    public static int newStatusBarColor =R.color.colorPrimaryDark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(this.getLocalClassName(), "on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("School Home Task 2");

        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mCheckBox.setOnClickListener(this);

        mEditText = (EditText)findViewById(R.id.editText);
        mEditText2 = (EditText)findViewById(R.id.editText2);
        mToolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setupToolbar();
        mBlue = (Button) findViewById(R.id.btn_blue);
        mRed = (Button) findViewById(R.id.btn_red);
        mGreen = (Button) findViewById(R.id.btn_green);
        mRed.setOnClickListener(this);
        mGreen.setOnClickListener(this);
        mBlue.setOnClickListener(this);
// устанавливаем
        setupToolbar();
    }

    private void setupToolbar(){
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() ==R.id.home){
            Toast.makeText(this, "Menu click", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.checkBox:
                Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show();
                if (mCheckBox.isChecked()) {
                    mEditText2.setVisibility(View.INVISIBLE);
                } else {
                    mEditText2.setVisibility(View.VISIBLE);
                }
                break;
/*
     изменение цвета при нажатии
 */
            case R.id.btn_blue:
                newToolBarColor = R.color.color_blue;
                newStatusBarColor = R.color.color_dark_blue;
                break;
            case R.id.btn_green:
                newToolBarColor = R.color.color_green;
                newStatusBarColor = R.color.color_dark_green;
                break;
            case R.id.btn_red:
                newToolBarColor = R.color.color_red;
                newStatusBarColor = R.color.color_dark_red;
                break;

        }
        mToolBar.setBackgroundResource(newToolBarColor);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(newStatusBarColor));
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(this.getLocalClassName(), "on start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(this.getLocalClassName(), "on resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(this.getLocalClassName(), "on Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(this.getLocalClassName(), "on stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(this.getLocalClassName(), "on restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(this.getLocalClassName(), "on destroy");
    }

/*
      Сохраняем статус поля и цвета панели
 */
        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            Lg.e(this.getLocalClassName(), "on save instance state");
            outState.putBoolean(VISIBLE_KEY, mEditText2.getVisibility() == View.VISIBLE);
            outState.putInt(TOOLBAR_COLOR_KEY, mToolBar.getResources().getColor(newToolBarColor));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = this.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                outState.putInt(STATUSBAR_COLOR_KEY, window.getStatusBarColor());
            }
            }

        /**
         * Восттанавливаем статус и цвета
         * @param savedInstanceState
         */
        @Override
        protected void onRestoreInstanceState(Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);
            Lg.e(this.getLocalClassName(), "on restore instance state");
            int visibleState = savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE;
            mEditText.setVisibility(visibleState);
            mToolBar.setBackgroundColor(savedInstanceState.getInt(TOOLBAR_COLOR_KEY, R.color.colorPrimary));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = this.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(savedInstanceState.getInt(STATUSBAR_COLOR_KEY, R.color.colorPrimaryDark));
            }

    }
}

