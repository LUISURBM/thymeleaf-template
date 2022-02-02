package co.com.quipux.javalang.ui;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jugador {
    private Long id;
    private String nombres;
    private Posicion posicion;
    private int edad;
    private Calendar created = Calendar.getInstance();

    public String toString() {
	return id.toString();
    }
}
