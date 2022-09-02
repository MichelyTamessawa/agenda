package alura.com.br.database;

import static alura.com.br.database.AgendaMigrations.TODAS_MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import alura.com.br.database.converter.ConversorCalendar;
import alura.com.br.database.converter.ConversorTelefone;
import alura.com.br.database.dao.AlunoDAO;
import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.model.Aluno;
import alura.com.br.model.Telefone;

@Database(entities = {Aluno.class, Telefone.class}, version = 8, exportSchema = false)
@TypeConverters({ConversorCalendar.class, ConversorTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {

    public static final String NOME_DATABASE = "agenda.db";

    public abstract AlunoDAO getRoomAlunoDAO();
    public abstract TelefoneDAO getRoomTelefoneDAO();

    /* Create an instance of database */
    public static AgendaDatabase getInstance(Context context) {
        return Room
                .databaseBuilder(context, AgendaDatabase.class, NOME_DATABASE)
                .fallbackToDestructiveMigration()
                .build();
    }
}
