package it.polito.tdp.regine.model;

import java.util.List;

public class TestRegine {

	public static void main(String[] args) {
		Regine r=new Regine();
		List<Integer> soluzione = r.risolvi(6);
		System.out.println(soluzione);

	}

}
