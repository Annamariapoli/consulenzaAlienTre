package it.polito.tdp.alien;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AlienController {

	AlienDizionario dizionario = new AlienDizionario();                     // lista di parole

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtTesto;

	@FXML
	private Button btnTraduci;

	@FXML
	private TextArea textResult;

	@FXML
	private Button btnPulisci;

	@FXML
	void doPulisci(ActionEvent event) {
		textResult.clear();
		txtTesto.clear();
	}

	@FXML
	void doTraduci(ActionEvent event) {
		String s = txtTesto.getText();
		txtTesto.clear();
		textResult.clear();
		s = s.toLowerCase();

		if (s.isEmpty()) {                                          // altro modo x vedere se è vuota
			textResult.appendText("Il testo non è corretto \n");
			return;
		}
	                 	// se nn è vuota:
		int spazio = 0;
		int puntoInterrogativo = 0;

		for (int i = 0; i < s.length(); i++) {
			if (Character.isSpaceChar(s.charAt(i))) {
				spazio++;
				if (spazio > 1) {
					textResult.appendText("Il testo non è corretto \n");
					return;
				}
			} else {                                      // non è uno spazio
				if (Character.isLetter(s.charAt(i))) {   // ritorna true se è  una lettera
				} else {                                // se non è una lettera:
					if (s.charAt(i) != '?') {          // se non è ? non va bene
						textResult.appendText("il testo non è corretto \n");
						return;
					} else {                       // se è ? :
						puntoInterrogativo++;
						if (puntoInterrogativo > 1) {
							textResult.appendText("Il testo non è corretto \n");
							return;
						}
					}
				}
			}
		}
		if (spazio == 0) {                   // se non c'è lo spazio vuol dire ke devo tradurre la parola  
							                // in questo caso posso avere il ? nella parola
			if (puntoInterrogativo == 1) { // se c'è ? devo assegnargli una lettera

				List<String> lista = dizionario.translateWordApprox(s);
				if (!lista.isEmpty()) {
					textResult.appendText("parola " + s + " e traduzione " + lista + "\n");
				} else {
					textResult.appendText("La parola non è presente nel dizionario");
				}
			} else {                                    // se il ? non c'è:
				String s1 = null;                     // mi da parola italiana
				s1 = dizionario.translateWord(s);    //e la traduzione
				if (s1 != null) {
					textResult.appendText("parola " + s + " e traduzione " + s1);
				} else {
					textResult.appendText("La parola non è presente nel dizionario");
				}
			}
		} else {              // se c'è lo spazio devo inserire la parola e non posso avere ?
					         // se c'è ? --> c'è un errore:
			if (puntoInterrogativo == 1) {
				textResult.appendText("Il testo non è corretto \n ");
				return;
			} else {                       // se non c'è il ?--> inserisco la parola
				String s2 = "";           // ho 2 parole

				String alienWord = null;
				String translation = null;

				int pos = s.indexOf(" ");   // num spazio

				alienWord = s.substring(0, pos);
				translation = s.substring(pos + 1);

				dizionario.addWord(alienWord, translation);
				textResult.appendText("E' stata aggiunta la nuova parola " + s2 + "\n");
			}
		}
	}

	@FXML
	void initialize() {
		assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'SampleAlien.fxml'.";
		assert btnTraduci != null : "fx:id=\"btnTraduci\" was not injected: check your FXML file 'SampleAlien.fxml'.";
		assert textResult != null : "fx:id=\"textResult\" was not injected: check your FXML file 'SampleAlien.fxml'.";
		assert btnPulisci != null : "fx:id=\"btnPulisci\" was not injected: check your FXML file 'SampleAlien.fxml'.";

	}
}
