package com.example.shubhammaheshwari.vyapar;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class NetworkRequest {
    public static String API = "https://en.wikipedia.org//w/api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpssearch=ronaldo&gpslimit=10";

    public NetworkRequest() {

    }
    public void getData(final Context context) {

        JSONObject data = new JSONObject();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<Player> players = abstractData(response);
                ((RequestResponseInterface)context).success(players);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ((RequestResponseInterface)context).fail(error);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
        Log.d("Tag", "it worked");
    }
    private List<Player> abstractData(JSONObject data)
    {
        List<Player> players = new ArrayList<>();
        try {
            JSONArray values = data.getJSONObject("query").getJSONArray("pages");
            for(int i = 0 ; i < values.length(); i++)
            {
                JSONObject value = values.getJSONObject(i);
                Player player = new Player();
                player.playerName = value.getString("title");
                if(value.has("terms"))
                {
                    player.playerDesc = value.getJSONObject("terms").getJSONArray("description").getString(0);
                }
                if(value.has("thumbnail")) {
                    player.playerImg = value.getJSONObject("thumbnail").getString("source");
                }
                players.add(player);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return players;
    }
}
