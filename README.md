**Fiscalia - Programación Avanzada**

Link de pre-revisiones en:
> https://github.com/Arakito14/Fiscalia/releases
> 

Uso de documento zip:
> Archivos de trabajo en -> "...\proyectoFiscalia\src\main\java\com\manzanitacreations\proyectofiscalia\"
> 
> Archivos de lectura y reporte (.csv, .txt) en -> "...\proyectoFiscalia\src\main\resources\"
> 
> Reporte, Diagrama UML y Mapa Conceptual en -> "...\reporte\"
> 
> changelog, codelog y TODO en "...\logs\"
> 

Ubicación de archivos:
> Proyecto general de java se encuentra en la carpeta "...\proyectoFiscalia\"
> 
> Archivos csv, "Fiscales.csv" y "causas.csv" de lectura se encuentran en la carpeta "...\resources\"
> 
> Archivo texto de reporte "reporte.txt" generado despues de cerrar el programa, se encuentran en la carpeta "...\resources\"
> 
> Package folder es llamado "com.manzanitacreations.proyectofiscalia/"
> 
> Archivo Main es el archivo "ProyectoFiscalia.java" dentro de nuestro package folder
> 
> Resto de clases de proyecto se encuentrar en nuestro package folder

Pequeña Introducción a diagrama y código:

> Clase Main -> ProyectoFiscalia.java
> 
> Padre -> Fiscalia.java, implementa interfaces Formato.java & Especialidad.java para correcta escritura y confirmación de datos
> 
> Hijos -> Fiscal.java & Causa.java, la ultima es clase abstracta de las siguientes
> 
> Hijos de Causa.java -> Procedimientos.java, CausaAbierta.java, CausaCerrada.java y CausaArchivada.java

*Programa fue testeado y ejecutado con "Netbeans IDE 12.3"*
