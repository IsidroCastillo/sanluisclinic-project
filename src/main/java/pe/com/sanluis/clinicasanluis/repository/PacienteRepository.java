package pe.com.sanluis.clinicasanluis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.sanluis.clinicasanluis.entity.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

	@Query("SELECT p FROM Paciente p WHERE p.email = :email AND p.password = :password")
	Paciente login(@Param("email") String email, @Param("password") String password);
}
