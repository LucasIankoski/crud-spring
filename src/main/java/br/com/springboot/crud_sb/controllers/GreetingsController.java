package br.com.springboot.crud_sb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.crud_sb.model.Usuario;
import br.com.springboot.crud_sb.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */

    
    @GetMapping(value = "listaUsuarios")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    	
    }
    
    @PostMapping(value = "cadastrarUsuario")
    @ResponseBody
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {

    	Usuario user = usuarioRepository.save(usuario);
    	
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
    @DeleteMapping(value = "deletarUsuario")
    @ResponseBody
    public ResponseEntity<String> deletarUsuario(@RequestParam Long id){
    	
    	usuarioRepository.deleteById(id);
    	
		return new ResponseEntity<String>("O usuário foi deletado.", HttpStatus.OK);
    	
    }
    
    @RequestMapping(value = "buscarUsuarioPorId")
    @ResponseBody
    public ResponseEntity<Usuario> buscarUsuarioPorId(@RequestParam(name = "idUsuario") Long idUsuario){
    	
    	Usuario usuario = usuarioRepository.findById(idUsuario).get();
    	
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	
    }
    
    @PutMapping(value = "atualizarUsuario")
    @ResponseBody
    public ResponseEntity<?> atualizarUsuario(@RequestBody Usuario usuario){
    	
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("O Id é obrigatório", HttpStatus.OK);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }

    @GetMapping(value = "buscarUsuarioPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarUsuarioPorNome(@RequestParam(name = "nome") String nome){
    	
    	List<Usuario> usuario = usuarioRepository.buscarUsuarioPorNome(nome.trim().toUpperCase());
    	
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    	
    }
    
    
    
}
