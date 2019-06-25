package com.catolicasc.combatcontrol;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

public class EncerramentoActivity extends AppCompatActivity {

    TextView vencedor;
    TextView pontuacao1;
    TextView pontuacao2;
    TextView nocaute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encerramento);

        vencedor = findViewById(R.id.txtVencedor);
        pontuacao1 = findViewById(R.id.txtPontuacao1);
        pontuacao2 = findViewById(R.id.txtPontuacao2);
        nocaute = findViewById(R.id.txtNocaute);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.3));
    }

    public static void dimBehind(PopupWindow popupWindow) {
        View container = popupWindow.getContentView().getRootView();
        Context context = popupWindow.getContentView().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.3f;
        wm.updateViewLayout(container, p);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        String vencedorX = intent.getStringExtra("vencedor");
        String pontuacao1X = intent.getStringExtra("pontuacao1");
        String pontuacao2X = intent.getStringExtra("pontuacao2");
        String nocauteX = intent.getStringExtra("nocaute");

        vencedor.setText(vencedorX);
        pontuacao1.setText(pontuacao1X);
        pontuacao2.setText(pontuacao2X);
        nocaute.setText(nocauteX);
    }
}
