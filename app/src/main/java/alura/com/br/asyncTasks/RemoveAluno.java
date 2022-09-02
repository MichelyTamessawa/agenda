package alura.com.br.asyncTasks;

import android.os.AsyncTask;

import alura.com.br.database.dao.AlunoDAO;
import alura.com.br.model.Aluno;
import alura.com.br.ui.adapter.ListaAlunosAdapter;

public class RemoveAluno extends AsyncTask<Void, Void, Aluno> {
    private Aluno alunoEscolhido;
    private AlunoDAO alunoDAO;
    private ListaAlunosAdapter adapter;

    public RemoveAluno(Aluno alunoEscolhido, AlunoDAO alunoDAO, ListaAlunosAdapter adapter) {
        this.alunoEscolhido = alunoEscolhido;
        this.alunoDAO = alunoDAO;
        this.adapter = adapter;
    }

    @Override
    protected Aluno doInBackground(Void... voids) {
        alunoDAO.remove(alunoEscolhido);
        return null;
    }

    @Override
    protected void onPostExecute(Aluno aluno) {
        super.onPostExecute(aluno);
        adapter.remove(alunoEscolhido);
    }
}
