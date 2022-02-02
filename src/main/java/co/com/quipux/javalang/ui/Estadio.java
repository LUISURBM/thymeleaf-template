package co.com.quipux.javalang.ui;

public enum Estadio {
    PASCUAL("Pascual Guerrero"),
    ATANASIO("Atanasio Girardot"),
    CAMPIN("El Campín"),
    TECHO("Bogotá Techo");

    private final String displayValue;

    private Estadio(String displayValue) {
	this.displayValue = displayValue;
    }

    public String getDisplayValue() {
	return displayValue;
    }

    public String toString() {
	return this.name();
    }
}