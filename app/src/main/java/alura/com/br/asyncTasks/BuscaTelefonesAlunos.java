package alura.com.br.asyncTasks;

import android.os.AsyncTask;

import java.util.List;

import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.model.Aluno;
import alura.com.br.model.Telefone;

public class BuscaTelefonesAlunos extends AsyncTask<Void, Void, List<Telefone>> {

    private final TelefoneDAO telefoneDAO;
    private final Aluno aluno;
    private quandoBuscaTelefones listener;

    public BuscaTelefonesAlunos(TelefoneDAO telefoneDAO, Aluno aluno, quandoBuscaTelefones listener) {
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO.todos(aluno.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.buscouTelefones(telefones);
    }

    public interface quandoBuscaTelefones{
        void buscouTelefones(List<Telefone> telefones);
    }
}
