package hr.fer.grafovi.utilities;

public interface Constants
{
	public static final int COMPLETE = 0;
	public static final int WHEEL = 1;
	public static final int CUBE = 2;
	public static final int CYCLE = 3;
	public static final int BIPARTITE = 4;
	public static final int RANDOM = 5;
	
	public static final int MATRICA_INCIDENCIJE = 1;
	public static final int MATRICA_SUSJEDSTVA = 2;
	public static final int LISTA_SUSJEDSTVA = 3;
	
	public static final String POVEZANOST_TEKST = "Povezanost:  U zadanom jednostavnom grafu nadi najkracu i najdulju " +
			"zatvorenu stazu.";
	public static final String DIJKSTRA_TEKST = "Problem najduljeg puta:  Modificiraj i impementiraj analogon Dijkstrinog algoritma " +
			"za trazenje najduljeg puta.";
	public static final String TRGOVACKI_PUTNIK_TEKST = "Problem trgovackog putnika: Usporedi pohlepni heuristicki algoritam " +
			"i iscrpnu pretragu za nalazenje najkraceg hamiltonovskog ciklusa u potpunom tezinskom grafu.";
	public static final String RAZAPINJUCA_STABLA_TEKST = "Stabla: U zadanom grafu prebroji sva razapinjuca stabla. " +
			"Ispisi barem jedno od njih.";
	public static final String MIN_RAZAPINJUCE_STABLO_TEKST = "Stabla: U zadanom potpunom tezinskom grafu nadi razapinjuce " +
			"stablo minimalne ukupne duljine, koristeci Kruskalov algoritam.";
	public static final String PLANARNOST_TEKST = "Planarnost: Ispitaj je li zadani graf planaran.";
	public static final String BOJANJE_VRHOVA_TEKST = "Bojanje vrhova grafa: Ispitaj je li zadani graf 3-obojiv, tj mogu li se njegovi " +
			"vrhovi obojati s 3 boje, tako da su susjedni vrhovi raznobojni.";
	public static final String BOJANJE_BRIDOVA_TEKST = "Bojanje bridova grafa: Ispitaj je li zadani graf bridno 3-obojiv, " +
			"tj mogu li se njegovi bridovi obojati s 3 boje, tako da su susjedni bridovi raznobojni.";
	public static final String SETNJA_TEKST = "Setnja po usmjerenim grafovima: U zadanom tezinskom usmjerenom grafu nadi kriticni put.";
	public static final String SPARIVANJE_TEKST = "Sparivanja: U zadanom bipartitnom grafu s obiljezenim vrhovima nadi i prebroji sva" +
			"potpuna sparivanja.";

}
