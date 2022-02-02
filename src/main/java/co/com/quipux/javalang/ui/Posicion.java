package co.com.quipux.javalang.ui;

public enum Posicion {
    DELANTERO("Delantero"),
    DEFENSA("DEFENSA"),
    PORTERO("PORTERO"),
    MEDIO("MEDIO");

    private final String displayValue;

    private Posicion(String displayValue) {
	this.displayValue = displayValue;
    }

    public String getDisplayValue() {
	return displayValue;
    }

    public String toString() {
	return this.name();
    }
}