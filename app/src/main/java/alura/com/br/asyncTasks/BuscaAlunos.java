package alura.com.br.asyncTasks;

import android.os.AsyncTask;

import java.util.List;

import alura.com.br.database.dao.AlunoDAO;
import alura.com.br.model.Aluno;
import alura.com.br.ui.adapter.ListaAlunosAdapter;

public class BuscaAlunos extends AsyncTask <Void, Void, List<Aluno>>{
    private AlunoDAO alunoDAO;
    private ListaAlunosAdapter adapter;

    public BuscaAlunos(AlunoDAO alunoDAO, ListaAlunosAdapter adapter) {
        this.alunoDAO = alunoDAO;
        this.adapter = adapter;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        return alunoDAO.todos();
    }

    @Override
    protected void onPostExecute(List<Aluno> alunos) {
        super.onPostExecute(alunos);
        adapter.atualiza(alunos);
    }
}
