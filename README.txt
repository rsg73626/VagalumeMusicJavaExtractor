 *** PARA COMPILAR ***

    No terminal, dentro da pasta do projeto, "VagalumeMusicSource/Application", digete o comando:

    javac -cp .:\* -d . Main.java Util/Util.java VagalumeAPI.java FileManager/TXTManager.java FileManager/JSONManager.java Model/Artist.java Model/Music.java

*** PARA EXECUTAR ***

    No terminal, dentro da pasta do projeto, "VagalumeMusicSource", digete o comando:

    java -cp .:\* Main

*** CALCULOS APROXIMADOS DE TEMPO PARA EXTRAÇÃO DOS DADOS ***

    27 letras
    200 artistas
    10 músicas por artista (suposição de média)
    2 minutos a cada 50 músicas

    Tempo total = (((27 * 200 * 10) / 50) * 2) minutos = 2160 minutos = 36 horas = 1,5 dia

*** REDUZINDO A QUANTIDADE DE ARTISTAS PARA 100 POR LETRA ***

    Tempo total = (((27 * 100 * 10) / 50) * 2) minutos = 1080 minutos = 18 horas
