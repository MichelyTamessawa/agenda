package alura.com.br.asyncTasks;

import android.os.AsyncTask;

import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.model.Telefone;

public class BuscaPrimeiroTelefoneAluno extends AsyncTask<Void, Void, Telefone> {

    private TelefoneDAO dao;
    private int alunoId;
    private PrimeiroTelefoneEncontradoListener listener;

    public BuscaPrimeiroTelefoneAluno(TelefoneDAO dao, int alunoId, PrimeiroTelefoneEncontradoListener listener) {
        this.dao = dao;
        this.alunoId = alunoId;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return dao.buscaPrimeiroTelefone(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone telefoneDevolvido) {
        super.onPostExecute(telefoneDevolvido);
        listener.quandoEncontrado(telefoneDevolvido);
    }

    public interface PrimeiroTelefoneEncontradoListener {
        void quandoEncontrado(Telefone telefoneEncontrado);
    }
}
