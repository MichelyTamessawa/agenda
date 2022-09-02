package alura.com.br.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alura.com.br.R;
import alura.com.br.asyncTasks.BuscaPrimeiroTelefoneAluno;
import alura.com.br.database.AgendaDatabase;
import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    private TelefoneDAO dao;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
        dao = AgendaDatabase.getInstance(context).getRoomTelefoneDAO();
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);

        Aluno alunoDevolvido = alunos.get(position);
        vincula(viewCriada, alunoDevolvido);

        return viewCriada;
    }

    private void vincula(View view, Aluno aluno) {
        TextView nome = view.findViewById(R.id.item_menu_nome);
        nome.setText(aluno.getNome());
        TextView telefone = view.findViewById(R.id.item_menu_telefone);
        new BuscaPrimeiroTelefoneAluno(dao, aluno.getId(),
                telefoneEncontrado -> telefone.setText(telefoneEncontrado.getNumero())).execute();
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(
                R.layout.item_aluno,
                parent,
                false);
    }

    public void atualiza(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
