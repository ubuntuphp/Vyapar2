package com.example.shubhammaheshwari.vyapar;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.List;

public interface RequestResponseInterface {
    public void success(List<Player> players);
    public void fail(VolleyError error);
}
