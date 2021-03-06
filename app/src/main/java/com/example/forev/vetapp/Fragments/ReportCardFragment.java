package com.example.forev.vetapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.forev.vetapp.Adapters.ReportCardAdapter;
import com.example.forev.vetapp.Models.PetModel;
import com.example.forev.vetapp.R;
import com.example.forev.vetapp.RestApi.ManagerAll;
import com.example.forev.vetapp.Utils.ChangeFragments;
import com.example.forev.vetapp.Utils.GetSharedPreferences;
import com.example.forev.vetapp.Utils.Warning;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportCardFragment extends Fragment {

    private RecyclerView reportCardRecyclerView;
    private View view;
    private ReportCardAdapter reportCardAdapter;
    private List<PetModel> petList;
    private ChangeFragments changeFragments;
    private String cust_id;
    private GetSharedPreferences getSharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_report_card, container, false);
        defineLayout();
        getPets(cust_id);

        return view;
    }

    public void defineLayout()
    {
        petList = new ArrayList<>();
        reportCardRecyclerView = view.findViewById(R.id.reportcardrecyclerview);
        RecyclerView.LayoutManager mng = new GridLayoutManager(getContext(),1);
        reportCardRecyclerView.setLayoutManager(mng);
        changeFragments = new ChangeFragments(getContext());
        getSharedPreferences = new GetSharedPreferences(getActivity());
        cust_id = getSharedPreferences.getSession().getString("cust_id",null);
    }

    public void getPets(String cust_id)
    {
        Call<List<PetModel>> req = ManagerAll.getInstance().getPets(cust_id);
        req.enqueue(new Callback<List<PetModel>>() {
            @Override
            public void onResponse(Call<List<PetModel>> call, Response<List<PetModel>> response) {
                if(response.body().get(0).isTf())
                {
                    petList = response.body();
                    reportCardAdapter = new ReportCardAdapter(petList,getContext());
                    reportCardRecyclerView.setAdapter(reportCardAdapter);
                }
                else
                {
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<PetModel>> call, Throwable t) {
                Toast.makeText(getContext(),Warning.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }
}
