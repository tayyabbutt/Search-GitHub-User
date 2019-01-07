package com.inowave.test.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.inowave.test.R;
import com.inowave.test.adapter.FollowersAdapter;
import com.inowave.test.models.Followers;
import com.inowave.test.models.Users;
import com.inowave.test.presenter.UserDetailActivityPresenter;
import com.inowave.test.utils.LoadingDialog;
import com.inowave.test.utils.MyDialog;
import com.inowave.test.utils.Utility;
import com.inowave.test.view.UserDetailActivityView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserMainDetailActivity extends AppCompatActivity implements UserDetailActivityView {
    private Users user;
    private RecyclerView follwersRecyclerView;
    private FollowersAdapter adapter;
    private TextView userNameTv, userEmailTv, followersTextMsg;
    private ImageView userImage;
    private LoadingDialog dialog;
    private UserDetailActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("User Details");
        presenter = new UserDetailActivityPresenter(this, UserMainDetailActivity.this);
        user = (Users) getIntent().getSerializableExtra(Utility.OBJECT_EXTRAS);
        dialog = new LoadingDialog(UserMainDetailActivity.this);
        userEmailTv = findViewById(R.id.userEmail);
        userImage = findViewById(R.id.userImage);
        userNameTv = findViewById(R.id.userName);
        followersTextMsg = findViewById(R.id.followers_txt_msg);
        follwersRecyclerView = findViewById(R.id.followersRecyclerView);
        follwersRecyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(UserMainDetailActivity.this, 3);
        follwersRecyclerView.setLayoutManager(layoutManager);
        if (user.getName() != null) {
            userNameTv.setText(user.getName());
        } else {
            userNameTv.setText("User name not found");
        }
        Picasso.get().load(user.getAvatarUrl()).into(userImage);
        if (user.getEmail() != null) {
            userEmailTv.setText(user.getEmail().toString());
        } else {
            userEmailTv.setText("User email does not exist");
        }
        presenter.getFollowersList(user.getLogin());

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void finish() {
        super.finish();

    }


    @Override
    public void showLoadingDialog() {
        dialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        dialog.dismiss();
    }

    @Override
    public void notifyRecyclerView(List<Followers> followers) {
        adapter = new FollowersAdapter(followers);
        follwersRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showImageLoadingProgress() {
    }

    @Override
    public void showErrorDialog(String error) {
        MyDialog.showDialog(UserMainDetailActivity.this, "Error", error, null);
    }

    @Override
    public void showNetworkErrorDialog() {
        MyDialog.showDialog(UserMainDetailActivity.this, "Network Error", "Connect to other network to get start", null);
    }

    @Override
    public void followersNotFound() {
        followersTextMsg.setText("Not Found");
    }
}
