package pe.com.sanluis.clinicasanluis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.sanluis.clinicasanluis.entity.Medico;

@Repository
public interface MedicoRepocitory extends JpaRepository<Medico, Integer>{

}
