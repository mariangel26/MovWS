/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Dominio.Usuario;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 * Clase que emula los servicios web de Movistar.
 * @author mariangelperez
 */
@Path("/servicios")
public class ServicesResource {

    private Usuario _usuarioPrueba, _usuarioPrueba2;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicesResource
     */
    public ServicesResource() {
        //String _nombre, String _apellido, Long _cedula, String _numero, String _numero2
       _usuarioPrueba = new Usuario("usuario","prueba","12345678","04241234567","04141234567");
       _usuarioPrueba2 = new Usuario("usuario2","prueba2","26040153","04242038635","04243468644");
        
    }

    /**
     * metodo de prueba que obtiene un JSON
     * @return un json con datos de una persona
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/prueba")
    public String getPruebaJson() {
        //http://localhost:8080/MovistarWS/webresources/servicios/prueba
        JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
        usuarioBuilder.add("Nombre", "Jose");
        usuarioBuilder.add("Apellido", "Rodriguez");
        usuarioBuilder.add("Usuario", "jose123");
        JsonObject usuarioJsonObject = usuarioBuilder.build();
        return usuarioJsonObject.toString();
    }

    /**
     * Metodo que dado unos datos devuelve un OTP
     * @param telefono numero del telefono del usuario
     * @param id numero de identificacion de la aplicacion
     * @param cedula la cedula del usuario
     * @return un OTP
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/GenerateOTP")
    public String getOTP(@QueryParam("userID") String telefono,
            @QueryParam("applicationID") Integer id, @QueryParam("cedulaUsuario") String cedula) {
        //http://localhost:8080/MovistarWS/webresources/servicios/GenerateOTP?userID=04241234567&applicationID=77&cedulaUsuario=12345678
        String _OTP ="";
        if((cedula.equals(_usuarioPrueba.getCedula())) && 
           (telefono.equals(_usuarioPrueba.getNumero())) && (id == 77)){
            
           _OTP = ServicesResource.toHash(cedula).toString();
           return _OTP;
            
        }else{
           return "0";
        }
    }
    
   /**
    * Metodo que genera un token dado un OTP
    * @param telefono numero de telefono del usuario
    * @param id identificador de la aplicacion
    * @param cedula numero de cedula del usuario
    * @param otp One-Time-Password generado en el metodo GenerateOTP
    * @return un token
    */ 
   @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/GenerateTokenByOTP")
    public String getTokenByOTP(@QueryParam("userID") String telefono,
            @QueryParam("applicationID") Integer id, @QueryParam("cedulaUsuario") String cedula,
            @QueryParam("otp") String otp) {
        //http://localhost:8080/MovistarWS/webresources/servicios/GenerateTokenByOTP?userID=04241234567&applicationID=77&cedulaUsuario=12345678&otp=3340
        String _token ="";
        if((cedula.equals(_usuarioPrueba.getCedula())) && 
           (telefono.equals(_usuarioPrueba.getNumero())) && (id == 77)
           && (otp!=null)){
            
           _token = ServicesResource.toHash(otp).toString();
           return _token;
            
        }else{
           return "0";
        }
    }
    
    /**
     * Metodo que genera un token dada una IP
     * @param ip ip del usuario
     * @param id identificador de la aplicacion
     * @param cedula numero de cedula del usuario
     * @return un token
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/GenerateTokenByDPI")
    public String getTokenByDPI(@QueryParam("userID") String ip,
            @QueryParam("applicationID") Integer id, @QueryParam("cedulaUsuario") String cedula) {
        //http://localhost:8080/MovistarWS/webresources/servicios/GenerateTokenByDPI?userID=172.18.4.41&applicationID=77&cedulaUsuario=12345678
        String _token ="", _respuesta="";
        if((cedula.equals(_usuarioPrueba.getCedula())) && 
           (ip.equals("172.18.4.41")) && (id == 77)){
            
           _token = ServicesResource.toHash(ip).toString();
           
           JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
                usuarioBuilder.add("telefono", _usuarioPrueba.getNumero());
                usuarioBuilder.add("token", _token);
                JsonObject usuarioJsonObject = usuarioBuilder.build();
                _respuesta = usuarioJsonObject.toString();
                
           return _respuesta;
            
        }else{
           return "0";
        }
    }
    /**
     * POST method for creating an instance of ServiceResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postXml(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public ServiceResource getServiceResource(@PathParam("id") String id) {
        return ServiceResource.getInstance(id);
    }
    
  static Integer toHash(String str){
   int strHashCode = Math.abs(str.hashCode() % 10000);
   return strHashCode;
}
}
