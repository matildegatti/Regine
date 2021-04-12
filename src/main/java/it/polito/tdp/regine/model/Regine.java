package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {
	
	// N è il numero di righe e colonne della scacchiera
		//   (righe e colonne numerate da 0 a N-1)
		// ad ogni livello posizioniamo una regina in una nuova riga
		
		// soluzione parziale: lista delle colonne in cui mettere le regine (prime righe)
		// 		List<Integer>
		// livello = quante righe sono già piene
		// livello = 0 => nessuna riga piena (devo mettere la regina nella riga 0)
		// livello = 3 => 3 righe piene (0, 1, 2), devo mettere la regina nella riga 3
		// [0]
		//     [0, 2]
		//            [0, 2, 1]
	 private int N;
	 private List<Integer> soluzione;
	 
	 
	 public List<Integer> risolvi(int N){
		 this.N=N;
		 List<Integer> parziale=new ArrayList<Integer>();  //uso array perchè faccio in seguito get più volte, nella linked aggiunge ritardo
		 this.soluzione=null;                                                                                                                                                  
		 cerca(parziale,0);
		 
		 return this.soluzione;
	 }
	
	 //cerca==true : trovato, cerca==false;cerca ancora
	 //in questo modo mi fermo alla prima soluzione, senza cercarle tutte
		private boolean cerca(List<Integer>parziale, int livello) {
			if(livello==N) {
				// caso terminale
				//System.out.println(parziale);
				this.soluzione= new ArrayList<>(parziale);  //se scrivo solo soluzione=parziale copio solo il riferimento, alla fine la lista parziale è vuota
			} else {
				for(int colonna=0; colonna<N; colonna++) {
					// if la mossa nella casella [livello][colonna] è valida
					// se sì, aggiungi a parziale e fai ricorsione
					
					if(posValida(parziale,colonna,livello)) {
						//List<Integer> parzialeNuovo=new ArrayList<Integer>(parziale);
						//parzialeNuovo.add(colonna);
						//cerca(parzialenuovo, livello+1);
						
						
						parziale.add(colonna);
						boolean trovato=cerca(parziale, livello+1);
						if(trovato)
							return true;
						//ritolgo l'ultimo elemento aggiunto perchè era solo una prova, guardo le altre possibili soluzioni
						//backtracking
						parziale.remove(parziale.size()-1);
					}
				}
			}
			return false;
		}

		private boolean posValida(List<Integer> parziale, int colonna, int livello) {
			// controlla se viene mangiata in verticale 
			if(parziale.contains(colonna))
				return false;
			
			//controllo per diagonale
			//diagonali orientate da dx verso sx hanno caratteristica riga+col=k costante
			//diagonali orientate sx a dx riga-col=k
			//confronto la posizione(livello,colonna) con (r,c) delle regine esistenti
			for(int r=0;r<livello;r++) {
				int c=parziale.get(r);
				
				if(r+c==livello+colonna || r-c==livello-colonna)
					return false;
			}
			return true;
		}
}
