Juego de memoria
================

Juego de memoria para "Developers Weekend for ONGs" (https://plus.google.com/events/cbmoq89dju87lbfouc2s1kt2t0k)

##Demo online
http://martinb35.github.com/memo-game/src/

##Agregar/modificar niveles
1. Editar variable con numero de niveles en **/src/js/memogame.js**

```javascript
this.levels = N;
```

2. Las imágenes de los niveles se encuentran ordenadas en el directorio */src/levels/* del siguiente modo:

* /levels/level-1
  * /levels/level-1/object-1.jpg
  * /levels/level-1/object-2.jpg
  * /levels/level-1/object-3.jpg
  * /levels/level-1/object-4.jpg
* /levels/level-n
  * /levels/level-n/object-1.jpg
  * /levels/level-n/object-2.jpg
  * /levels/level-n/object-3.jpg
  * /levels/level-n/object-4.jpg

##Requerimientos iniciales
###Función a estimular: 
Memoria visual / permanencia de objetos.

###Recursos: 
3 ó 4 animalitos que se muestran un momento y 3 ó 4 cajitas que caen sobre ellos para que no puedan visualizarse.

###Ejecución en el niño: 
En la base aparece un objeto igual al de alguna cajita y el niño debe buscarlo.

###Detalle de la aplicación: 
Se colocarán cuatro objetos bien diferenciados en medio de la pantalla. Se agrega un botón que al presionarse hace caer cajitas sobre ellos para taparlos. Luego de ello se coloca uno de los objetos al azar debajo de la pantalla y el niño tiene que tocar la cajita que lo contiene. El juego finaliza cuando selecciona la cajita correcta. Mientras tanto y cada vez que toca una incorrecta, desaparece la cajita y queda el objeto en su lugar. Es deseable que vayan apareciendo varios objetos diversos.

Luego de varias pasadas, unas 5, se puede agregar alguna pantalla con todos los objetos iguales pero de diversos colores para incrementar la complejidad.


##Pendientes/mejoras

* Mejorar responsive design.
* Crear aplicacion con Phonegap
* Agregar niveles