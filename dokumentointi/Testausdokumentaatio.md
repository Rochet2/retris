# Testausdokumentaatio

### Mitä en testannut ja miksi
Jokin mutaatio jäi testaamatta `Board.removeAndReturnFilledRows` metodissa.
En testannut kun en keksinyt heti miten se testataan ja testikattavuus on muuten hyvä.
Myöskään käytössa (pelattu 5h) ei ole tullut esiin bugeja.

Muutin pelin perustaa vielä viimekädessä, joten Game luokkaan jäi muutama mutaatio testaamatta.
En ole ehtinyt tehdä testejä uusille metodeille mutaatioiden varalle ja mutaatiot ovat vaikeita testata, sillä ne vaativat jotakin pääsyä luokkiin jotta metodien vaikutukset voi tarkistaa.

### Miten testattu
Yleisesti peliä on testattu JUnit testien lisäksi pelaamalla.
Pelissä ei ole montaa toimintoa joten testaus pelaamalla on melko kattavaa.
Peliä pelatessa ei ole ilmennyt bugeja.
:+1:

JUnit testit on kirjoitettu metodeille eri kanteilta ja niiden kattavuus on tarkistettu vasta jälkikäteen.
Kattavuutta on sitten paranneltu raporttien avulla - yleensä lähinnä mutaatioiden takia.
