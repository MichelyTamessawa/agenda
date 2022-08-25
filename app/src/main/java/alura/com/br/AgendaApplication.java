package alura.com.br;

import android.app.Application;

import alura.com.br.dao.AlunoDAO;
import alura.com.br.model.Aluno;

@SuppressWarnings("WeakerAccess")
public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunos();
    }

    private void criaAlunos() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Ana", "123456", "ana@email.com"));
        dao.salva(new Aluno("Barbara", "123456", "barb@email.com"));
    }
}
