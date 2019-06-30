package com.catolicasc.combatcontrol;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class HistoricoFragment extends Fragment {

    ListView historico;
    SimpleCursorAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_historico, container, false);

        historico = v.findViewById(R.id.lvHistorico);

        DAL dal = new DAL(getContext());
        Cursor cursor = dal.loadAll();

        String[] fields = new String[] {CreateDatabase.ROBO1, CreateDatabase.PONTUACAO_ROBO1, CreateDatabase.PONTUACAO_ROBO2, CreateDatabase.ROBO2};
        int[] ids = {R.id.txtRobo1LV, R.id.txtP1LV, R.id.txtP2LV, R.id.txtRobo2LV};

        adapter = new SimpleCursorAdapter(getContext(), R.layout.activity_historico_adapter, cursor, fields, ids, 0);
        historico.setAdapter(adapter);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        DAL dal = new DAL(getContext());
        Cursor cursor = dal.loadAll();

        String[] fields = new String[] {CreateDatabase.ROBO1, CreateDatabase.PONTUACAO_ROBO1, CreateDatabase.PONTUACAO_ROBO2, CreateDatabase.ROBO2};
        int[] ids = {R.id.txtRobo1LV, R.id.txtP1LV, R.id.txtP2LV, R.id.txtRobo2LV};

        adapter = new SimpleCursorAdapter(getContext(), R.layout.activity_historico_adapter, cursor, fields, ids, 0);

        historico.setAdapter(adapter);
    }
}
