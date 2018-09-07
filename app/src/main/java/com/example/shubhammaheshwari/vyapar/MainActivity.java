package com.example.shubhammaheshwari.vyapar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListAdapter;

import com.android.volley.VolleyError;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RequestResponseInterface{

    private RecyclerView mRvPlayers;
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
        getDataFromServer();
    }
    private void setUpWidgets()
    {
        mRvPlayers = findViewById(R.id.rv_players);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvPlayers.setLayoutManager(linearLayoutManager);
    }
    private void setUpAdapter(List<Player> players)
    {
        mDataAdapter.setData(players);
        mDataAdapter.notifyDataSetChanged();
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
