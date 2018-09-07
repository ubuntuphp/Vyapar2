package com.example.shubhammaheshwari.vyapar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.android.volley.VolleyError;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RequestResponseInterface{

    private RecyclerView mRvPlayers;
    private EditText mEditSearch;
    private Button mBtnSearch;
    private LinearLayout mLLSearchResult;
    private TextView mTxtSearchResult;
    private ImageView mImgSearchCancel;
    private DataAdapter mDataAdapter;
    private NetworkRequest mNetworkRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init()
    {
        mNetworkRequest = new NetworkRequest();
        mDataAdapter = new DataAdapter(this);
        setUpWidgets();
        setUpSearchBar();
        getDataFromServer();
    }
    private void setUpWidgets()
    {
        mRvPlayers = findViewById(R.id.rv_players);
        mTxtSearchResult = findViewById(R.id.txt_search_result);
        mImgSearchCancel = findViewById(R.id.img_search_cancel);
        mLLSearchResult = findViewById(R.id.ll_search_result);
        mBtnSearch = findViewById(R.id.btn_search);
        mEditSearch = findViewById(R.id.edit_search);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvPlayers.setLayoutManager(linearLayoutManager);
    }
    private void setUpAdapter(List<Player> players)
    {
        mDataAdapter.setData(players);
        mDataAdapter.notifyDataSetChanged();
    }
    private void setUpSearchBar()
    {
        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLLSearchResult.setVisibility(View.VISIBLE);
                mTxtSearchResult.setText(mDataAdapter.getResult(mEditSearch.getText().toString()));
            }
        });
        mImgSearchCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLLSearchResult.setVisibility(View.GONE);
            }
        });
    }
    private void getDataFromServer()
    {
        mRvPlayers.setAdapter(mDataAdapter);
        mNetworkRequest.getData(this);
    }

    @Override
    public void success(List<Player> players) {
        setUpAdapter(players);
        Log.i("succes" , Integer.toString(players.size()));
    }

    @Override
    public void fail(VolleyError error) {
        Log.i("error" , error.toString());

    }
}
