package com.inowave.test.networking;

import com.inowave.test.models.Followers;
import com.inowave.test.models.Users;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;

public interface ApiService {

    @GET("{username}?access_token=c9ec7e3c6416eeb33f24052c99f2bfe79861152c ")
    Call<Users> getUser(@HeaderMap Map<String, String> userHeaders, @Path("username") String username);

    @GET("{username}/followers?access_token=c9ec7e3c6416eeb33f24052c99f2bfe79861152c ")
    Call<List<Followers>> getFollower(@HeaderMap Map<String, String> userHeaders, @Path("username") String username);
}
