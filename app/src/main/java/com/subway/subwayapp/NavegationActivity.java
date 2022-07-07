package com.subway.subwayapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.subway.subwayapp.databinding.ActivityNavegationBinding;

public class NavegationActivity extends AppCompatActivity {

    ActivityNavegationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavegationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //replaceFragment(new HomeFragment());

        binding.btnNavView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.itemCart:
                    replaceFragment(new CartFragment());
                    break;
                case R.id.itemHome:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.itemUser:
                    replaceFragment(new UserFragment());
                    break;
            }

            return true;
        });

        View home = binding.btnNavView.findViewById(R.id.itemHome);
        home.performClick();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}