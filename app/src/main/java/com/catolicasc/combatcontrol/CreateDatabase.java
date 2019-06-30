package com.catolicasc.combatcontrol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "historico.db";
    public static final String ID = "_id";
    public static final String TABLE = "historico";
    public static final String ROBO1 = "robo1";
    public static final String ROBO2 = "robo2";
    public static final String PONTUACAO_ROBO1 = "p_robo1";
    public static final String PONTUACAO_ROBO2 = "p_robo2";

    private static final int VERSION = 1;

    public CreateDatabase(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " ( "
                    + "_id integer primary key autoincrement, " +
                    "robo1 text, " +
                    "robo2 text, " +
                    "p_robo1 text," +
                    "p_robo2 text " +
                    ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}

