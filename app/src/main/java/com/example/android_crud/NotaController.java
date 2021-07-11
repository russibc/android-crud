package com.example.android_crud;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class NotaController {

    private Context mContext;
    private NotaDAO notaDAO;


    public NotaController(Context c) {
        mContext = c;
        notaDAO = new NotaDAO(c);
    }

    public int atualizarNota(Nota n) {
        return this.notaDAO.atualiza_nota(n);
    }

    public Nota getNota(int id) {
        return notaDAO.getNota(id);

    }

    public Nota cadastrarNovaNota(Nota n) {
        return notaDAO.inserirNota(n);
    }

    public List<Nota> listaNotas() {
        return notaDAO.listaNotas();
    }

    public List<String> listaTitulosNotas() {
        List<Nota> result = this.listaNotas();
        List<String> titulos = new ArrayList<>();
        for (Nota nota : result) {
            titulos.add(nota.getTitulo());
        }
        return titulos;
    }
}
