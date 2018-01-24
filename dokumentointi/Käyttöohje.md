# Käyttöohje
Retris on hyvin yksinkertainen.
Peli aloitetaan käynnistämällä jar tiedosto:
- Windowsilla voi jar tiedostoa kaksoisklikata.
- Linuxilla tulee käyttää komentoriviltä komentoa `java -jar retris.jar`

Pelissä satunnaisesti valittu palikka liikkuu kohti ikkunan pohjaa.  
Pelin tavoitteena on estää palikoita täyttämästä ikkunaa tekemällä palikoista täysiä vaakarivejä jolloin rivi tuhoutuu vapauttaen tilaa.  
Palan valuessa pohjalle tai osuessa toiseen palaan asettuu se paikalleen ja uusi pala tippuu.  
Pisteitä saa kaavan `2^tuhotutRivit * 50` mukaan - enemmän pisteitä saa siis tuhoamalla kerralla enemmän rivejä. Palojen väreillä ei ole merkitystä.  
Pelissä pisteet näkyvät pelin ylälaidassa. Pelin loputtua ruutuun ilmestyy ikkuna joka kertoo pelin loppuneen ja näyttää lopulliset pisteet.  
Alas valuvaa palaa voi ohjata nuolinäppäimillä sivuille ja alas. Ylös nuolinäppäin kääntää palaa vastapäivään jos mahdollista.  
Pala pysähtyy vain osuessaan johonkin allaolevaan - pala ei siis jää kiinni sivuseiniin tai palasiin sivuistaan.  
Pala liikkuu puolensekunnin välein alaspäin yhden neliön verran.  
