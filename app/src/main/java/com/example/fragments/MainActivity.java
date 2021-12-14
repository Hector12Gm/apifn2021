package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;


import com.example.fragments.adapter.PageAdapter;
import com.example.fragments.fragments.Perfil;
import com.example.fragments.fragments.RecyclerViewFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayaout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();
        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }




    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> lista = new ArrayList<Fragment>();

        lista.add(new RecyclerViewFragment());
        lista.add(new Perfil());
        return  lista;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Contactos");
        tabLayout.getTabAt(1).setText("Favoritos");
    }
}