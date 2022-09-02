package alura.com.br.asyncTasks;

import android.os.AsyncTask;

import alura.com.br.model.Telefone;

public abstract class BaseAlunoComTelefone extends AsyncTask<Void, Void, Void> {
    private final FinalizadaListener listener;

    public BaseAlunoComTelefone(FinalizadaListener listener) {
        this.listener = listener;
    }

    protected void vinculaAlunoComTelefone(int alunoId, Telefone... telefones) {
        for (Telefone telefone: telefones) {
            telefone.setAlunoId(alunoId);
        }
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.quandoFinalizada();
    }

    public interface FinalizadaListener {
        void quandoFinalizada();
    }
}
