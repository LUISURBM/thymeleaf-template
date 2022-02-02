package co.com.quipux.javalang.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cancion {
    private String titulo;
    private String artista;
    private String album;
    private int anno;
}
