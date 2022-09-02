package alura.com.br.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConversorCalendar {
    @TypeConverter
    public Long paraLong(Calendar data) {
        if (data != null)
            return data.getTimeInMillis();
        else return null;
    }

    @TypeConverter
    public Calendar paraCalendar(Long valor) {
        Calendar momentoAtual = Calendar.getInstance();
        if (valor != null)
            momentoAtual.setTimeInMillis(valor);
        return momentoAtual;
    }
}
