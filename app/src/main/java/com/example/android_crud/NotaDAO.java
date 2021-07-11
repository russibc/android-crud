package com.example.android_crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NotaDAO {

    private SQLiteDatabase database;

    public NotaDAO(Context c) {
        database = c.openOrCreateDatabase("dbNotas", c.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS notas (id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "titulo varchar,"
                + "texto varchar);");
    }

    public Nota getNota (Integer i){
        Cursor cursor=this.database.query("notas", new String[]{"id","titulo","texto"} ,"id=?",new String[]{i.toString()},null,null,null);
        cursor.moveToFirst();
        return new Nota(cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("titulo")),
                cursor.getString(cursor.getColumnIndex("texto"))
        );
    }

    public int atualiza_nota(Nota n){
        if(n!=null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("titulo", n.getTitulo());
            contentValues.put("texto", n.getTexto());
            return this.database.update("notas", contentValues, "id=?", new String[]{n.getId().toString()});
        }
        return  -1 ;
    }

    public Nota inserirNota(Nota n) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("titulo", n.getTitulo());
        contentValues.put("texto", n.getTexto());
        int i = (int) database.insert("notas", null, contentValues);
        n.setId(i);
        return n;
    }

    public List<Nota> listaNotas() {
        List<Nota> lista = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM  notas", null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) {
            lista.add(
                    new Nota(cursor.getInt(cursor.getColumnIndex("id")),
                            cursor.getString(cursor.getColumnIndex("titulo")),
                            cursor.getString(cursor.getColumnIndex("texto"))
                    )
            );
        }
        return lista;
    }
}
