# hexagonal_game_of_life

The well-known Conway's Game of Life implemented on a hexagonal grid.

(2021)

The original description in hungarian:

A Game of Life, magyarul életjáték egy sejtautomata, amit John Horton Conway matematikus 
talált ki. A klasszikus életjátékot általában egy négyzetrácson valósítják meg, de ebben a 
programban ehelyett egy hexagonális háló a játék színtere. Minden cellában egy sejt élhet, és 
ezeket a cellákat 6 szomszédos cella veszi körül. Az eredeti szabály szerint, ha egy sejtnek két 
vagy három szomszédja van, akkor túléli a következő generációig, ha kettőnél kevesebb, vagy 
pedig háromnál több szomszédja van, akkor pedig elpusztul. Emellett minden üres cellában új 
sejt születik, melynek szomszédságában pontosan 3 élő sejt van. A program arra is lehetőséget 
nyújt, hogy ezt a szabályt szabadon megváltoztathassuk.
A játék menete tehát a következő: A játékos megadhat egy szabályt ami a sejtek születésére és 
elpusztulására vonatkozik, és egy alakzatot, amely a szimuláció kezdőpontjaként szolgál, 
majd a többi lépést a számítógép végzi. Ezután a játékos hátra dőlhet és megfigyelheti a 
játéktáblán kirajzolódó érdekes alakzatokat. Emiatt ezt tulajdonképpen egy nulla személyes 
játéknak is lehet nevezni.

Class diagram:

![class_diagram](https://user-images.githubusercontent.com/100372543/220443176-22b53cd4-e689-49f4-8a3c-16fe01516ab2.png)


![image](https://user-images.githubusercontent.com/100372543/220445819-fc36bfea-d6e5-429d-970d-b5a2d74a4b51.png)

