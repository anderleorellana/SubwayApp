package com.subway.subwayapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.subway.subwayapp.databinding.ActivityNavegationBinding;

public class NavegationActivity extends AppCompatActivity {

    ActivityNavegationBinding binding;
    CartFragment cartFragment;
    HomeFragment homeFragment;
    UserFragment userFragment;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavegationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initFragments();
        int ID_Usuario = intent.getIntExtra("UsuLog",0);
        Bundle bundle = new Bundle();
        bundle.putInt("ID_USU",ID_Usuario);

        binding.btnNavView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.itemCart:
                    cartFragment.setArguments(bundle);
                    replaceFragment(cartFragment);
                    break;
                case R.id.itemHome:
                    homeFragment.setArguments(bundle);
                    replaceFragment(homeFragment);
                    break;
                case R.id.itemUser:
                    userFragment.setArguments(bundle);
                    replaceFragment(userFragment);
                    break;
            }

            return true;
        });

        View home = binding.btnNavView.findViewById(R.id.itemHome);
        home.performClick();
    }

    private void initFragments() {
        cartFragment = new CartFragment();
        homeFragment = new HomeFragment();
        userFragment = new UserFragment();
        intent = getIntent();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}