package fr.diginamic.jdbc;

import java.util.List;

import fr.diginamic.jdbc.dao.FournisseurDao;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDaoFournisseurs {

	public static void main(String[] args) {
		FournisseurDao f = new FournisseurDaoJdbc();
		
		
		List<Fournisseur> listeFournisseurs = f.extraire();
		
		
		Fournisseur fAAjouter = new Fournisseur(0, "La Maison De La Peinture");
		f.insert(fAAjouter);
		
		
		int lignesModifiees = f.update("La Maison De La Peinture", "La Maison Des Peintures");
		
		fAAjouter.setNom("La Maison Des Peintures");
		boolean resultatSuppressionLigne = f.delete(fAAjouter);
		System.out.println(resultatSuppressionLigne);
	}

}
