package alura.com.br.asyncTasks;

import alura.com.br.database.dao.AlunoDAO;
import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.model.Aluno;
import alura.com.br.model.Telefone;

public class SalvaAluno extends BaseAlunoComTelefone {
    private Aluno aluno;
    private AlunoDAO alunoDAO;
    private Telefone telefoneFixo;
    private Telefone telefoneCelular;
    private TelefoneDAO telefoneDAO;

    public SalvaAluno(Aluno aluno, AlunoDAO alunoDAO, Telefone telefoneFixo,
                      Telefone telefoneCelular, TelefoneDAO telefoneDAO,
                      FinalizadaListener listener) {
        super(listener);
        this.aluno = aluno;
        this.alunoDAO = alunoDAO;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDAO = telefoneDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int alunoId = alunoDAO.salva(aluno).intValue();
        vinculaAlunoComTelefone(alunoId, telefoneFixo, telefoneCelular);
        telefoneDAO.salva(telefoneFixo, telefoneCelular);
        return null;
    }
}
