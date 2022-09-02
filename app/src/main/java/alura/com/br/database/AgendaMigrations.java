package alura.com.br.database;

import static alura.com.br.model.TipoTelefone.FIXO;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import alura.com.br.model.TipoTelefone;

class AgendaMigrations {
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
        }
    };
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Criar nova tabela com as informações desejadas;
            database.execSQL("CREATE TABLE IF NOT EXISTS aluno_novo " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, `telefone` TEXT, `email` TEXT)");

            // Copiar dados da tabela antiga para a nova;
            database.execSQL("INSERT INTO aluno_novo (id, nome, telefone, email)" +
                    " SELECT id, nome, telefone, email FROM aluno");

            // Remove tabela antiga;
            database.execSQL("DROP TABLE aluno");

            // Renomear a tabela nova com o nome da tabela antiga;
            database.execSQL("ALTER TABLE aluno_novo RENAME TO aluno");
        }
    };
    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN momentoCadastro INTEGER");
        }
    };
    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS aluno_novo" +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "nome TEXT, telefoneFixo TEXT, email TEXT, momentoCadastro INTEGER, " +
                    "telefoneCelular TEXT)");

            database.execSQL("INSERT INTO aluno_novo (id, nome, telefoneFixo, " +
                    "momentoCadastro, email)" +
                    "SELECT id, nome, telefone, momentoCadastro, email FROM aluno");

            database.execSQL("DROP TABLE aluno");
            database.execSQL("ALTER TABLE aluno_novo RENAME TO aluno");
        }
    };
    private static final Migration MIGRATION_5_6 = new Migration(5,6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS aluno_novo" +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "nome TEXT, email TEXT, momentoCadastro INTEGER)");

            database.execSQL("INSERT INTO aluno_novo (id, nome, momentoCadastro, email)" +
                    "SELECT id, nome, momentoCadastro, email FROM aluno");

            database.execSQL("CREATE TABLE IF NOT EXISTS telefone" +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "alunoId INTEGER NOT NULL, numero TEXT, tipo TEXT," +
                    "FOREIGN KEY (alunoId) REFERENCES aluno_novo(id) " +
                    "ON DELETE CASCADE ON UPDATE CASCADE)");

            database.execSQL("INSERT INTO telefone (alunoId, numero) SELECT id, telefoneFixo " +
                    "FROM aluno");

            database.execSQL("UPDATE telefone SET tipo=?", new TipoTelefone[] {FIXO});

            database.execSQL("DROP TABLE aluno");
            database.execSQL("ALTER TABLE aluno_novo RENAME TO aluno");

        }
    };
    private static final Migration MIGRATION_6_7 = new Migration(6,7) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS aluno" +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "nome TEXT, email TEXT, momentoCadastro INTEGER)");
            database.execSQL("CREATE TABLE IF NOT EXISTS telefone" +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "alunoId INTEGER NOT NULL, numero TEXT, tipo TEXT," +
                    "FOREIGN KEY (alunoId) REFERENCES aluno_novo(id) " +
                    "ON DELETE CASCADE ON UPDATE CASCADE)");
        }
    };
    private static final Migration MIGRATION_7_8 = new Migration(7,8) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS aluno" +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "nome TEXT, email TEXT, momentoCadastro INTEGER)");
            database.execSQL("CREATE TABLE IF NOT EXISTS telefone" +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "alunoId INTEGER NOT NULL, numero TEXT, tipo TEXT," +
                    "FOREIGN KEY (alunoId) REFERENCES aluno_novo(id) " +
                    "ON DELETE CASCADE ON UPDATE CASCADE)");
        }
    };
    static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4,
            MIGRATION_4_5, MIGRATION_5_6, MIGRATION_6_7, MIGRATION_7_8};
}
