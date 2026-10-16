package tn.esprit.autoloc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final VehiculeRepository vehiculeRepository;

    public DataInitializer(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public void run(String... args) {
        if (vehiculeRepository.count() == 0) {

            Vehicule v1 = new Vehicule();
            v1.setImmatriculation("123-TUN-4567");
            v1.setMarque("Renault");
            v1.setModele("Clio");
            v1.setCategorie(CategorieVehicule.CITADINE);
            v1.setTarifJournalier(new BigDecimal("80.00"));
            v1.setStatut(StatutVehicule.DISPONIBLE);

            Vehicule v2 = new Vehicule();
            v2.setImmatriculation("456-TUN-7890");
            v2.setMarque("Peugeot");
            v2.setModele("3008");
            v2.setCategorie(CategorieVehicule.SUV);
            v2.setTarifJournalier(new BigDecimal("150.00"));
            v2.setStatut(StatutVehicule.DISPONIBLE);

            Vehicule v3 = new Vehicule();
            v3.setImmatriculation("789-TUN-1234");
            v3.setMarque("Mercedes");
            v3.setModele("Classe C");
            v3.setCategorie(CategorieVehicule.BERLINE);
            v3.setTarifJournalier(new BigDecimal("200.00"));
            v3.setStatut(StatutVehicule.LOUE);

            vehiculeRepository.save(v1);
            vehiculeRepository.save(v2);
            vehiculeRepository.save(v3);

            System.out.println("✅ 3 véhicules de démonstration insérés en base !");
        }
    }
}