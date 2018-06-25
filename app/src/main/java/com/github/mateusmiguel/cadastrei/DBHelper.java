package com.github.mateusmiguel.cadastrei;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 25/06/2018.
 */
public class DBHelper {
    private static final String DATABASE_NAME = "bancodedados.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "contato";

    private Context context;
    private SQLiteDatabase db;

    private SQLiteStatement insertStnt;

    private static final String INSERT = "insert into " + TABLE_NAME + " (nome, cpf, idade, telefone, email) VALUES (?,?,?,?,?)";

    public DBHelper(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStnt = this.db.compileStatement(INSERT);
    }

    public long insert(String nome, String cpf, String idade, String telefone, String email) {
        this.insertStnt.bindString(1, nome);
        this.insertStnt.bindString(2, cpf);
        this.insertStnt.bindString(3, idade);
        this.insertStnt.bindString(4, telefone);
        this.insertStnt.bindString(5, email);
        return this.insertStnt.executeInsert();
    }

    public void deleteAll() {
        this.db.delete(TABLE_NAME, null, null);
    }

    public List<PessoaFisica> queryGetAll() {
        List<PessoaFisica> list = new ArrayList<PessoaFisica>();
        try {
            Cursor cursor = this.db.query(TABLE_NAME, new String[]{"nome", "cpf", "idade", "telefone", "email"}, null, null, null, null, null, null);
            int nregistros = cursor.getCount();
            if (nregistros != 0) {
                cursor.moveToFirst();
                do {
                    PessoaFisica pessoafisica = new PessoaFisica(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                    list.add(pessoafisica);
                } while (cursor.moveToNext());

                if (cursor != null && !cursor.isClosed())
                    cursor.close();
                return list;
            } else
                return null;
        } catch (Exception err) {
            return null;
        }
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper(Context context){
            super (context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate (SQLiteDatabase db){
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cpf TEXT, idade TEXT, telefone TEXT, email TEXT);";
            db.execSQL(sql);
        }
        public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(("DROP TABLE IF EXISTS " + TABLE_NAME));
            onCreate(db);
        }
    }
}
//    public List<PessoaFisica> queryGetAll() {
//
//        List<PessoaFisica> list = new ArrayList<PessoaFisica>();
//
//        try {
//            Cursor cursor = this.db.query(TABLE_NAME, new String[]{"nome", "cpf", "idade", "telefone", "email"}, null, null, null, null, null, null);
//            int nregistros = cursor.getCount();
//            if (nregistros != 0) {
//                cursor.moveToFirst();
//                do {
//                    PessoaFisica pessoafisica = new PessoaFisica(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
//                    list.add(pessoafisica);
//                } while (cursor.moveToNext());
//                if (cursor != null && !cursor.isClosed())
//                    cursor.close();
//                return list;
//
//            } else {
//                return null;
//            }
//
//        } catch (Exception e) {
//            return null;
//
//        }
//    }
//
//    private static class OpenHelper extends SQLiteOpenHelper {
//        OpenHelper(Context context){
//            super (context, DATABASE_NAME,null,DATABASE_VERSION);
//        }
//        public void onCreate (SQLiteDatabase db){
//            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cpf TEXT, idade TEXT, telefone TEXT, email TEXT);";
//            db.execSQL(sql);
//        }
//        public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){
//            db.execSQL(("DROP TABLE IF EXISTS " + TABLE_NAME));
//        }
//    }
