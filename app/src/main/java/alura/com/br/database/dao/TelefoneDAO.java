package alura.com.br.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import alura.com.br.model.Telefone;

@Dao
public interface TelefoneDAO {
    @Query("SELECT * FROM telefone " +
            "WHERE telefone.alunoId = :alunoId AND telefone.numero != '' " +
            "LIMIT 1")
    Telefone buscaPrimeiroTelefone(int alunoId);

    @Insert
    void salva(Telefone... telefones);

    @Query("SELECT * FROM telefone WHERE telefone.alunoId = :alunoId")
    List<Telefone> todos(int alunoId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void atualiza(Telefone... telefones);
}
