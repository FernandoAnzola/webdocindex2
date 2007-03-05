/*
 * Config.java
 *
 * Created on 20 de junio de 2005, 0:53
 */

package init;


/**
 *
 * @author joliva1983
 */
public class Config
{

    protected static String servername; //especifica la ruta del servidor tomcat
    protected static String serverport; //especifica el puerto de tomcat
    protected static String application; //especifica el nombre de la aplicacion
    protected static String fileserver; //especifica la ruta para el servidor de ficheros, imagen
    protected static String dbKey;//especifica la clave para la encriptacion de la base de datos
    protected static String filePath;//especifica la clave para la encriptacion de la base de datos
    

    public Config(String serverName, String serverPort, String Aplicacion, String dbKey, String fileServer, String filePath)
    {
        this.servername=serverName;
        this.serverport=serverPort;
        this.application=Aplicacion;
        this.fileserver=fileServer;
        this.dbKey=dbKey;
        this.filePath=filePath;
    }
     
    public static String getServerName(){return servername;}
    public static String getServerPort(){return serverport;}
    public static String getApplication(){return application;}
    public static String getFileServer(){return fileserver;}
    public static String getDbKey(){return dbKey;}
    public static String getFilePath(){return filePath;}
}
