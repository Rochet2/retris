#Ohjelman rakenne
Retriksen main metodi retrisMain luokassa luo käyttöliittymän, peliolion (Game), pelin päivittäjän (Updater) ja GameState olion.
Tämän jälkeen oliot jatkavat itsenäisesti elämäänsä Updater olion päivittämänä.

Updater kutsuu GameWindow ja Game luokkien Update metodia jonka Updateable rajapinta tarjoaa.
Update metodi päivittää sitten käyttöliittymän tarvittaessa ja Game luokassa pelin tilanteen.
Game olio pitää sisällään pelin kulun kannalta oleelliset oliot ja hoitaa niiden kommunikoinnin. Game olio päivittelee GameState oliota jota UI sitten lukee ja näyttää pelaajalle.
Kaikki toiminta käyttöliittymän ja pelilogiikan välillä kulkee GameState olion kautta synkronoidusti jotta moniajo ongelmilta vältytään.

Käyttöliittymä siis tarkistelee GameState oliosta pelin tilannetta ja Game olio päivittelee tilannetta GameState olioon. Updater olio aiheuttaa nämä toiminnot.

Board on pelilauta joka pitää kirjaa vain pelilaudalla kiinteästi olevista paloista. Piece on tippuva pala ja Shape on sen muoto.
Game olio omaa listan eri muodoista jotka main metodi luo alussa ja joita sitten jaellaan Piece oliolle satunnaisesti.

![JAVALABRA luokkakaavio 3.png](JAVALABRA luokkakaavio 3.png)