package it.polito.tdp.alien;

import java.util.LinkedList;
import java.util.List;

public class AlienDizionario {

	private List<Word> parole = new LinkedList<Word>();


	public void addWord(String alienWord, String translation) { 
		for (Word p : parole) { 
			if (p.getAlienWord().equals(alienWord)) { 
				p.setTranslation(translation);                       //AGGIORNO LA TRADUZIONE (come la prima versione)
				return;
			}
		}                                                         
		Word parola = new Word(alienWord, translation);            // se non c'è la creo
		parole.add(parola);                                       // e la aggiungo alla lista
	}
	
	
	
	public String translateWord(String alienWord) {                
		for (Word p : parole) {                                       
			if (p.getAlienWord().equals(alienWord)) {         
				return p.getTranslation();
			}                                                     
		}
		return null;
	}



	public List<String> translateWordApprox(String alienWord) {
		List<String> lista = new LinkedList<String>() ;
		for (Word p : parole) {                                       
			if (p.similar(alienWord)) {     
				lista.add(p.getTranslation()) ;
				                                 // return p.getTranslation(); NON DEVO USCIRE perché mi servono anche le altre parole
			}                                                     
		}
		return lista;
	}
	
}
