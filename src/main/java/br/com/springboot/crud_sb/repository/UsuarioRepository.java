package br.com.springboot.crud_sb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.crud_sb.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("select u from Usuario u where upper(trim(u.nome)) like %?1%")
	List<Usuario> buscarUsuarioPorNome(String nome);
	
}

