Al momento del Run dell'applicazione vengono richiamate una serie di chiamate CommandLineRunner,
nella Classe Run package it.epicode.
Esse creano la struttura del database ( Tutte le tabelle e le relative associzioni per foreignKey ) , 
da prestare particolare attenzione al fatto che  il database deve essere già creato all'interno di 
Postgress e deve avere come nome beservice.
Nel caso in cui si volesso cambiare le configurazioni del database , nel file applicationConfig , occorre
modificare i  valori predefiniti.

ULTERIORI CREAZIO al momento del run :
Popolamento delle tabelle comune e provincia attraverso l'importazione dei file csv contenuti all'interno
della cartella IT.EPICODE.BESERVICE\filecsv. I file hanno diverse anomalie di contenuto di conseguenza ,
alcuni comuni non avranno la giusta associazione con la provincia ma ho valorizzato le eventuali discordanze
con il valore null.
La tabella cliente viene popolata con un unico cliente ( collegata tramite foreignKey alla tabella indirizzo )
La tabella fattura viene popolata con una unica fattura ( collegata tramite foreignKei alla tabella Cliente e alla tabella Stato_fattura )
La tabella indirizzo viene popolata con due indirizzi ( collegata tramite foreignKey alla tabella Comune)
La tabella role viene popolata con i due ruoli a cui possono assegnati gli User.
La tabella stato fattura viene popolata con un unico stato.
La tabella user viene popolata con un solo elemento ( mail e password  crittografata ).
La tabella user roles viene popolata automaticamente con una sola riga è rappresenta la tabella di associazione (ManyToMany)
tra id user e id role.

*******************************************************************************************************************
Per tutti gli eventuali test , si consiglia poi di commentare la classe Run onde evitare il flusso iniziale
di caricamento in quanto relativamente lungo ( 30 secondi ). 

Relativamente alla Collection PostMan si suggerisce di fare prima tutte le chiamate di insertPost in quanto i metodi UpdatePost sono stati impostati sugli inserimentiPost.

*******************************************************************************************************************

Preciso che nel codice ( soprattutto nella parte controller ) non ho seguito una sola linea di ragionamento ma ho varie linee 
al fine di evidenziare le varie nozioni ricevute dal corso. 
Ho preferito concentrarmi sulla parte logica di un gestionale , ad esempio rendere univoci i dati ad esempio , "ragione sociale" e "partita iva" , piuttosto che l'inserimento di un cliente presente in fattura qualora esso non 
sia stato inserito prima in database , l'impossibilità di inserire una fattura cliente con uno stesso numero , il fine era quello di poter rendere più reale possibile l'applicazione. 



