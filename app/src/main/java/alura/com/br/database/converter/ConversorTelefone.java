package alura.com.br.database.converter;

import androidx.room.TypeConverter;

import alura.com.br.model.TipoTelefone;

public class ConversorTelefone {
    @TypeConverter
    public String paraString(TipoTelefone tipo) {
        if (tipo != null)
            return tipo.name();
        return null;
    }

    @TypeConverter
    public TipoTelefone paraTipoTelefone(String tipo) {
        if (tipo != null)
            return TipoTelefone.valueOf(tipo);
        return TipoTelefone.FIXO;
    }
}
