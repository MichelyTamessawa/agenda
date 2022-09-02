package alura.com.br.asyncTasks;

import android.os.AsyncTask;

import java.util.List;

import alura.com.br.database.dao.AlunoDAO;
import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.model.Aluno;
import alura.com.br.model.Telefone;
import alura.com.br.model.TipoTelefone;

public class EditaAluno extends BaseAlunoComTelefone {
    private AlunoDAO alunoDAO;
    private Aluno aluno;
    private Telefone telefoneFixo;
    private Telefone telefoneCelular;
    private List<Telefone> telefonesDoAluno;
    private TelefoneDAO telefoneDAO;

    public EditaAluno(AlunoDAO alunoDAO, Aluno aluno, Telefone telefoneFixo,
                      Telefone telefoneCelular, List<Telefone> telefonesDoAluno,
                      TelefoneDAO telefoneDAO, FinalizadaListener listener) {
        super(listener);
        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefonesDoAluno = telefonesDoAluno;
        this.telefoneDAO = telefoneDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.edita(aluno);
        vinculaAlunoComTelefone(aluno.getId(), telefoneFixo, telefoneCelular);
        atualizaIdDosTelefones(telefoneFixo, telefoneCelular);
        return null;
    }

    private void atualizaIdDosTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {
        for (Telefone telefone : telefonesDoAluno) {
            if (telefone.getTipo() == TipoTelefone.FIXO) {
                telefoneFixo.setId(telefone.getId());
            } else {
                telefoneCelular.setId(telefone.getId());
            }
            telefoneDAO.atualiza(telefoneFixo, telefoneCelular);
        }
    }
}
