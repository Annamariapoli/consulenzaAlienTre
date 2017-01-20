package it.polito.tdp.alien;

import java.util.LinkedList;
import java.util.List;

public class Word {
		private String alienWord;      //parola
		private String translation;   //traduzione
		
		public Word(String alienWord, String translation) {
			super();
			this.alienWord = alienWord;
			this.translation = translation;
		}
		
		public String getAlienWord() {
			return alienWord;
		}

		public void setAlienWord(String alienWord) {
			this.alienWord = alienWord;
		}

		public String getTranslation() {
			return translation;
		}

		public void setTranslation(String translation) {
			this.translation = translation;
		}

		public boolean similar(String alienWord) {
			if(alienWord.length()!=this.getAlienWord().length())
				return false ;
			
            int posizionePuntoInterrogativo = alienWord.indexOf('?')  ;        //restituisce la posizione dove c'è ? 

    		String	primaParteDiParola = alienWord.substring(0,posizionePuntoInterrogativo);
    		String secondaParteDiParola = alienWord.substring(posizionePuntoInterrogativo+1);
    		
    		String primaParteP = this.getAlienWord().substring(0,posizionePuntoInterrogativo);
    		String secondaParteP = this.getAlienWord().substring(posizionePuntoInterrogativo+1);
    		
    		if( primaParteDiParola.equals(primaParteP) && secondaParteDiParola.equals(secondaParteP) )
    			return true ;

			return false;
		}
}
