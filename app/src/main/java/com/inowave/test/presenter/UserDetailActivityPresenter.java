package com.inowave.test.presenter;

import android.content.Context;

import com.inowave.test.models.Followers;
import com.inowave.test.networking.RetrofitClient;
import com.inowave.test.utils.Utility;
import com.inowave.test.view.UserDetailActivityView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailActivityPresenter {
    private UserDetailActivityView view;
    private List<Followers> followers;
    private Context mContext;

    public UserDetailActivityPresenter(UserDetailActivityView view, Context context) {
        this.view = view;
        this.mContext = context;
        followers = new ArrayList<>();
    }

    public void getFollowersList(String userName) {
        if (Utility.isNetworkAvailable(mContext)) {
            view.showLoadingDialog();
            Call<List<Followers>> followerListCall = RetrofitClient.getInstance().getApiService().getFollower(Utility.getUserHeader(), userName);
            followerListCall.enqueue(new Callback<List<Followers>>() {
                @Override
                public void onResponse(Call<List<Followers>> call, Response<List<Followers>> response) {
                    if (response.body() != null && response.isSuccessful() && response.code() == 200) {
                       view.hideLoadingDialog();
                        followers = response.body();
                        if (followers != null) {
                           view.notifyRecyclerView(followers);
                        }else {
                            view.followersNotFound();
                        }

                    } else {
                        view.hideLoadingDialog();
                        view.showErrorDialog(response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Followers>> call, Throwable t) {
                    view.hideLoadingDialog();
                    view.showErrorDialog(t.toString());

                }
            });
        } else {
            view.showNetworkErrorDialog();
        }
    }


}

