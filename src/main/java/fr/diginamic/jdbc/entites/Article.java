package fr.diginamic.jdbc.entites;

public class Article {
	private int id;
	private String ref;
	private String designation;
	private double prix;
	private Fournisseur fournisseur;
	
	
	
	public Article(int id, String ref, String designation, Fournisseur fournisseur) {
		this.id = id;
		this.ref = ref;
		this.designation = designation;
		this.fournisseur = fournisseur;
	}



	public int getId() {
		return id;
	}
	//f
	
	
	public String getRef() {
		return ref;
	}



	public String getDesignation() {
		return designation;
	}



	public double getPrix() {
		return prix;
	}



	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	
	
	
}

